from flask import request, jsonify
from google.cloud import storage
import pymysql
from datetime import datetime
from werkzeug.utils import secure_filename
import os
import random
import time
import mimetypes
from pytz import timezone

from app import app

# Konfigurasi Cloud Storage
storage_client = storage.Client.from_service_account_json('service-account-key.json')
bucket_name = 'bangkit-hai-bucket-1'

# Konfigurasi MySQL
db = pymysql.connect(
    host='34.101.230.10',
    user='root',
    password='bangkit-hai',
    database='hadirai_db',
    cursorclass=pymysql.cursors.DictCursor
)

# Konfigurasi untuk menyimpan foto sementara
app.config['UPLOAD_FOLDER'] = 'uploads'
app.config['ALLOWED_EXTENSIONS'] = {'jpg', 'jpeg', 'png', 'gif'}

def allowed_file(filename):
    return '.' in filename and filename.rsplit('.', 1)[1].lower() in app.config['ALLOWED_EXTENSIONS']

def generate_id_presensi():
    timestamp = int(time.time())  # Ambil bagian integer dari timestamp saat ini
    random_number = random.randint(1000, 9999)  # Angka acak antara 1000 dan 9999
    return f'{timestamp}-{random_number}'

def upload_to_cloud_storage(photo, filename):
    blob = storage.Blob(filename, storage_client.get_bucket(bucket_name))
    content_type, _ = mimetypes.guess_type(filename)
    blob.upload_from_file(photo, content_type=content_type or 'application/octet-stream')
    return blob.public_url


def save_presensi_to_database(id_user, nama, img_url):
    # Use the 'Asia/Jakarta' time zone
    jakarta_timezone = timezone('Asia/Jakarta')
    
    # Get the current time in the 'Asia/Jakarta' time zone
    date_time = datetime.now(jakarta_timezone)
    
    # Format the date and time
    date = date_time.strftime('%Y-%m-%d')
    current_time = date_time.strftime('%H:%M:%S')
    id_presensi = generate_id_presensi()

    cursor = db.cursor()
    query = "INSERT INTO presensi (id_presensi, id_user, nama, date, time, img_url) VALUES (%s, %s, %s, %s, %s, %s)"
    cursor.execute(query, (id_presensi, id_user, nama, date, current_time, img_url))
    db.commit()
    cursor.close()

def save_login_history_to_database(id_user, nama):
    date_time = datetime.now()
    date = date_time.strftime('%Y-%m-%d')
    time = date_time.strftime('%H:%M:%S')

    cursor = db.cursor()
    query = "INSERT INTO login_history (id_user, nama, date, time) VALUES (%s, %s, %s, %s)"
    cursor.execute(query, (id_user, nama, date, time))
    db.commit()
    cursor.close()

@app.route('/')
def welcome():
    return jsonify({'message': 'Welcome to Hadir AI'}), 200

@app.route('/login', methods=['POST'])
def login():
    id_user_input = request.form.get('id_user')
    nama_input = request.form.get('nama')

    # cek apakah id_user dan nama sesuai dengan data di tabel user
    cursor = db.cursor()
    query_check_user = "SELECT * FROM user WHERE id_user = %s AND nama = %s"
    cursor.execute(query_check_user, (id_user_input, nama_input))
    user_data = cursor.fetchone()
    cursor.close()

    if user_data:
        # Save login history to the database
        save_login_history_to_database(id_user_input, nama_input)

        return jsonify({'message': 'Login berhasil'}), 200
    else:
        return jsonify({'error': 'Login gagal'}), 401

@app.route('/presensi', methods=['POST'])
def presensi():
    id_user_input = request.form.get('id_user')
    nama_input = request.form.get('nama')

    # Memeriksa apakah id_user ditemukan
    cursor = db.cursor()
    query_check_user = "SELECT * FROM user WHERE id_user = %s"
    cursor.execute(query_check_user, (id_user_input,))
    user_data = cursor.fetchone()
    cursor.close()

    if user_data:
        # Memeriksa apakah id_user dan nama sesuai
        if user_data['nama'] == nama_input:
            try:
                # Simpan foto ke Cloud Storage langsung
                photo = request.files['photo']
                if photo and allowed_file(photo.filename):
                    filename = secure_filename(photo.filename)

                    # Upload foto ke Cloud Storage
                    img_url = upload_to_cloud_storage(photo, filename)

                    # Simpan data presensi ke database
                    save_presensi_to_database(id_user_input, nama_input, img_url)

                    return jsonify({'message': 'Presensi berhasil disimpan'}), 200
                else:
                    return jsonify({'error': 'Invalid file format'}), 400
            except Exception as e:
                return jsonify({'error': f'Error processing photo: {str(e)}'}), 500
        else:
            return jsonify({'error': 'Id user dan nama tidak sesuai'}), 400
    else:
        return jsonify({'error': 'Id user belum terdaftar'}), 400

@app.route('/history', methods=['GET'])
def presensi_history():
    id_user_input = request.args.get('id_user')
    nama_input = request.args.get('nama')

    # Check if id_user exists
    cursor = db.cursor()
    query_check_user = "SELECT * FROM user WHERE id_user = %s"
    cursor.execute(query_check_user, (id_user_input,))
    user_data = cursor.fetchone()
    cursor.close()

    if user_data:
        # Check if id_user and nama match
        if user_data['nama'] == nama_input:
            try:
                # Retrieve presensi history from the database
                cursor = db.cursor()
                query_presensi_history = "SELECT date, time, img_url FROM presensi WHERE id_user = %s AND nama = %s"
                cursor.execute(query_presensi_history, (id_user_input, nama_input))
                presensi_data = cursor.fetchall()
                cursor.close()

                # Prepare the response
                presensi_list = []
                for row in presensi_data:
                    # Convert timedelta objects to strings
                    presensi_list.append({
                        'date': str(row['date']),
                        'time': str(row['time']),
                        'img_url': row['img_url']
                    })

                return jsonify({'presensi_history': presensi_list}), 200
            except Exception as e:
                return jsonify({'error': f'Error retrieving presensi history: {str(e)}'}), 500
        else:
            return jsonify({'error': 'Id user dan nama tidak sesuai'}), 400
    else:
        return jsonify({'error': 'Id user belum terdaftar'}), 400