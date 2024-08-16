document.addEventListener("DOMContentLoaded", function () {
  const regionMap = {
    seoul: "서울",
    ulsan: "울산",
    sejong: "세종",
    gyeonggi: "경기도",
    gangwon: "강원도",
    chungbuk: "충북",
    chungnam: "충남",
    busan: "부산",
    daegu: "대구",
    incheon: "인천",
    gwangju: "광주",
    daejeon: "대전",
    jeonbuk: "전북",
    jeonnam: "전남",
    gyeongbuk: "경북",
    gyeongnam: "경남",
    jeju: "제주도",
  };

  function getQueryParameter(name) {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get(name);
  }

  const region = getQueryParameter("region");
  const regionTitle = document.getElementById("region-title");
  const regionContent = document.getElementById("region-content");

  if (region && regionMap[region]) {
    regionTitle.textContent = `${regionMap[region]} 페이지`;

    const apiUrl = `http://localhost:8080/api/events/${region}`;
    fetch(apiUrl)
      .then((response) => {
        if (!response.ok) {
          throw new Error("네트워크 응답이 좋지 않습니다.");
        }
        return response.json();
      })
      .then((data) => {
        if (data.data && data.data.length > 0) {
          regionContent.innerHTML = data.data
            .map(
              (event) => `
              <a href="./Detail.html?eventId=${event.eventId}">
                <div class="event">
                  <h2>${event.title}</h2>
                  <img src="${event.imgSrc}" alt="${event.title} 이미지"></img>
                  <p><strong>기간:</strong> ${event.period}</p>
                  <p><strong>장소:</strong> ${event.place}</p>
                  <p><strong>연락처:</strong> ${event.contact}</p>
                  <p>${event.description}</p>
                </div>
              </a>
              `
            )
            .join("");
        } else {
          regionContent.textContent =
            "해당 지역에 대한 이벤트 정보가 없습니다.";
        }
      })
      .catch((error) => {
        console.error("API 요청 중 오류 발생:", error);
        regionContent.textContent = "데이터를 가져오는 데 오류가 발생했습니다.";
      });
  } else {
    if (regionTitle) {
      regionTitle.textContent = "정보 없음";
    }
    if (regionContent) {
      regionContent.textContent = "선택된 지역이 없습니다.";
    }
  }
});
