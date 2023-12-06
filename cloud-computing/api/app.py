from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from datetime import datetime

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'mysql://root:nisa12345@localhost/hadir_ai'
db = SQLAlchemy(app)

class User(db.Model):
    id_user = db.Column(db.Integer, primary_key=True)
    password = db.Column(db.String(255))
    nama = db.Column(db.String(255))
    role = db.Column(db.String(50))
    jatah_cuti = db.Column(db.Integer)
    jumlah_cuti = db.Column(db.Integer)

class Presensi(db.Model):
    id_presensi = db.Column(db.Integer, primary_key=True)
    id_user = db.Column(db.Integer, db.ForeignKey('user.id_user'))
    date = db.Column(db.Date)
    waktu_masuk = db.Column(db.Time)
    waktu_keluar = db.Column(db.Time)

# Menambahkan baris ini
with app.app_context():
    db.create_all()

@app.route('/login', methods=['POST'])
def login():
    try:
        data = request.get_json(force=True)  # Menambahkan force=True untuk memaksa membaca data JSON
        username = data.get('username')
        password = data.get('password')

        user = User.query.filter_by(id_user=username, password=password).first()

        if user:
            response = {
                'status': 'success',
                'message': 'Login successful',
                'user': {
                    'id_user': user.id_user,
                    'nama': user.nama,
                    'role': user.role,
                    'jatah_cuti': user.jatah_cuti,
                    'jumlah_cuti': user.jumlah_cuti
                }
            }
        else:
            response = {
                'status': 'fail',
                'message': 'Login failed. Invalid username or password.'
            }

        return jsonify(response)
    except Exception as e:
        return jsonify({'status': 'error', 'message': str(e)})


@app.route('/presensi', methods=['POST'])
def presensi():
    try:
        data = request.get_json(force=True)
        id_user = data.get('id_user')
        date = datetime.now().date()  # Peroleh tanggal saat ini
        waktu_masuk = datetime.now().time()  # Peroleh waktu saat ini

        # Cek apakah presensi awal atau akhir
        presensi_awal = Presensi.query.filter_by(id_user=id_user, date=date, waktu_keluar=None).first()

        if presensi_awal:
            # Jika presensi awal sudah ada, update waktu_keluar
            presensi_awal.waktu_keluar = waktu_masuk
            db.session.commit()

            response = {
                'status': 'success',
                'message': 'Presensi akhir berhasil disimpan.'
            }
        else:
            # Jika presensi awal belum ada, simpan presensi awal
            presensi = Presensi(id_user=id_user, date=date, waktu_masuk=waktu_masuk)
            db.session.add(presensi)
            db.session.commit()

            response = {
                'status': 'success',
                'message': 'Presensi awal berhasil disimpan.'
            }

        return jsonify(response)
    except Exception as e:
        return jsonify({'status': 'error', 'message': str(e)})

if __name__ == '__main__':
    app.run(debug=True)
