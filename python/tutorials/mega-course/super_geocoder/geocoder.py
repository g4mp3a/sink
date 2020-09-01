from flask import Flask, render_template, request, send_file
from werkzeug.utils import secure_filename
from geopy.geocoders import ArcGIS
import pandas
import datetime
import os

os.makedirs("result", exist_ok=True)

app = Flask(__name__)

@app.route("/")
def home_page():
    return render_template("index.html")

@app.route("/results", methods=['POST'])
def results():
    global file
    global filename

    if request.method == 'POST':
        file = request.files['file']

        try:
            df = pandas.read_csv(file)
            gc = ArcGIS(scheme='http')
            df['Coordinates'] = df['Address'].apply(gc.geocode)
            df['Latitude'] = df['Coordinates'].apply(lambda x: x.latitude if x is not None else None)
            df['Longitude'] = df['Coordinates'].apply(lambda x: x.longitude if x is not None else None)
            df = df.drop('Coordinates', 1)

            filename = datetime.datetime.now().strftime("result/%Y-%m-%d-%H-%M-%S-%f__" + file.filename)
            df.to_csv(filename, index=None)
            return render_template("index.html", results=df.to_html(), btn='download.html')
        except Exception as e:
            print(str(e))
            return render_template("index.html", notification="Issues found with uploaded file. "
                                                              "Please fix them before resubmitting.")

@app.route("/download")
def download():
    return send_file(filename, attachment_filename="geocoded_" + file.filename, as_attachment=True)

if __name__ == '__main__':
    app.debug = True
    app.run(port=5001)
