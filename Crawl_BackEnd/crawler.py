from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
import time

def get_event_details(driver):
    events = []
    for j in range(1, 6):
        base_selector = f"#content > div.contentWrap > ul.mediaWrap.color01 > li:nth-child({j})"
        try:
            title = driver.find_element(By.CSS_SELECTOR, f"{base_selector} > div.text > p").text
            description = driver.find_element(By.CSS_SELECTOR, f"{base_selector} > div.text > div").text
            img_src = driver.find_element(By.CSS_SELECTOR, f"{base_selector} > div.img > img").get_attribute("src")
            period = driver.find_element(By.CSS_SELECTOR, f"{base_selector} > div.text > ul > li:nth-child(1)").text
            place = driver.find_element(By.CSS_SELECTOR, f"{base_selector} > div.text > ul > li:nth-child(2)").text
            contact = driver.find_element(By.CSS_SELECTOR, f"{base_selector} > div.text > ul > li:nth-child(3)").text
            events.append((title, description, img_src, period, place, contact))
        except Exception as e:
            print(f"Error retrieving event details for index {j}: {e}")
    return events

def crawl_data():
    regions = ["서울시", "부산시", "대구시", "인천시", "광주시", "대전시", "울산시", "세종시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도", "경상북도", "경상남도", "제주도"]
    region_data = {region: [] for region in regions}

    driver = webdriver.Chrome()

    page_number = 1
    while True:
        driver.get(f"https://mcst.go.kr/kor/s_culture/festival/festivalList.jsp?pCurrentPage={page_number}")
        
        try:
            WebDriverWait(driver, 10).until(
                EC.presence_of_element_located((By.CSS_SELECTOR, "#content > div.contentWrap > ul.mediaWrap.color01"))
            )
        except Exception as e:
            print(f"Error waiting for page load: {e}")
            break

        event_details = get_event_details(driver)
        if not event_details:
            break

        for title, description, img_src, period, place, contact in event_details:
            if place.startswith("장소: "):
                place = place.replace("장소: ", "").strip()
                
            if contact.startswith("문의: "):
                contact = contact.replace("문의: ", "").strip()

            if period.startswith("기간: "):
                period = period[len("기간: "):].strip()

            for region in regions:
                if region in place:
                    region_data[region].append({
                        "title": title,
                        "description": description,
                        "img_src": img_src,
                        "period": period,
                        "place": place,
                        "contact": contact
                    })
                    break

        page_number += 1 
        time.sleep(2)  

    driver.quit()
    return region_data
