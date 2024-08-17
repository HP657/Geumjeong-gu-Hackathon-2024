import psycopg2

def reset_table():
    try:
        conn = psycopg2.connect(
            dbname="postgres",
            user="root",
            password="postgre",
            host="svc.sel4.cloudtype.app",
            port="32264"
        )
        cursor = conn.cursor()

        cursor.execute('delete from comments')
        cursor.execute('delete from events')

        conn.commit()
        print("이벤트 데이터가 삭제되었습니다.")
    except Exception as e:
        print(f"Error resetting table: {e}")
    finally:
        cursor.close()
        conn.close()

def save_to_database(region_data):
    reset_table()   
    try:
        conn = psycopg2.connect(
            dbname='postgres', 
            user='root',          
            password="postgre",      
            host='svc.sel4.cloudtype.app',              
            port='32264'                    
        )
        cursor = conn.cursor()

        for region, events in region_data.items():
            for event in events:
                cursor.execute('''
                    INSERT INTO events (title, description, img_src, period, place, contact, region)
                    VALUES (%s, %s, %s, %s, %s, %s, %s)
                ''', (event['title'], event['description'], event['img_src'], event['period'], event['place'], event['contact'], region))

        conn.commit()
        print("이벤트 정보가 PostgreSQL 데이터베이스에 저장되었습니다.")
    except Exception as e:
        print(f"Error: {e}")
        conn.rollback()
    finally:
        cursor.close()
        conn.close()
