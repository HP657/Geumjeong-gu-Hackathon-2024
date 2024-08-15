from flask import Flask, jsonify
from crawler import crawl_data
from db import save_to_database

app = Flask(__name__)

@app.route('/api/crawl', methods=['POST'])
def crawl():
    try:
        data = crawl_data()

        save_to_database(data)
        return jsonify({"status": "success", "data": "성공적"}), 200
    except Exception as e:
        return jsonify({"status": "error", "message": str(e)}), 500

if __name__ == '__main__':
    app.run(debug=True)
