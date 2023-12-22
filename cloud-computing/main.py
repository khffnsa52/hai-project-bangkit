from app import app
import os
from pathlib import Path

# Mendapatkan direktori tempat file ini berada
basedir = Path(__file__).resolve(strict=True).parent

# Menetapkan direktori kerja untuk Flask
os.chdir(basedir)

if __name__ == '__main__':
    app.run(debug=True, port=8080)
