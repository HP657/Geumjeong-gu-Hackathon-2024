import psycopg2
from dotenv import load_dotenv
import os

load_dotenv()

def create_table():
    conn = psycopg2.connect(
        dbname="postgres",
        user="postgres",
        password=os.environ.get("DB_PASSWORD"),
        host="localhost",
        port="5432"
    )
    cursor = conn.cursor()
    
    cursor.execute('DROP TABLE IF EXISTS events')
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS events (
            id SERIAL PRIMARY KEY,
            title TEXT,
            description TEXT,
            img_src TEXT,
            period TEXT,
            place TEXT,
            contact TEXT,
            region TEXT
        )
    ''')
    conn.commit()
    cursor.close()
    conn.close()

def save_to_database(region_data):
    create_table()
    try:
        conn = psycopg2.connect(
            dbname='postgres', 
            user='postgres',          
            password=os.environ.get("DB_PASSWORD"),      
            host='localhost',              
            port='5432'                    
        )
        cursor = conn.cursor()

        for region, events in region_data.items():
            for event in events:
                cursor.execute('''
                INSERT INTO events (title, description, img_src, period, place, contact, region)
                VALUES (%s, %s, %s, %s, %s, %s, %s)
                ''', (event['title'], event['description'], event['img_src'], event['period'], event['place'], event['contact'], region))

        conn.commit()
        cursor.close()
        conn.close()

        print("이벤트 정보가 PostgreSQL 데이터베이스에 저장되었습니다.")
    except Exception as e:
        print(f"Error: {e}")
