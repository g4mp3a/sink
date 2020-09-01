from flask import Flask, render_template, request, send_file
from flask_sqlalchemy import SQLAlchemy
from email_sender import send_email
from sqlalchemy.sql import func
#from werkzeug.utils import secure_filename

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://gautampriya:@Simple1!@localhost/survey_db'
db = SQLAlchemy(app)


class Height(db.Model):
    _tablename = "height"
    _id = db.Column(db.Integer, primary_key=True)
    _email = db.Column(db.String(225), unique=True)
    _height = db.Column(db.Integer)

    def __init__(self, _email, _height):
        self._email = _email
        self._height = _height


@app.route("/")
def home_page():
    return render_template("index.html")

@app.route("/success", methods=['POST'])
def success():
    #global file
    if request.method == 'POST':
        email = request.form["name_email"]
        height = request.form["name_height"]
        """
        file = request.files["file"]
        file.save(secure_filename("uploaded_" + file.filename))

        with open("uploaded_" + file.filename, "a") as f:
            f.write("Newly added lines...\nAnother one.")
        """
        if db.session.query(Height).filter(Height._email == email).count() == 0:
            data = Height(email, height)
            db.session.add(data)
            db.session.commit()
            avg_ht = round(db.session.query(func.avg(Height._height)).scalar())  # ,1 to round to 1 decimal place
            send_email(email, height, avg_ht)
            return render_template("success.html")
        return render_template("index.html",
                               text="We have surveyed that email address already!")
        
        #return render_template("success.html")

        #return render_template("index.html", btn="download.html")

@app.route("/download")
def download():
    return send_file("uploaded_"+file.filename, attachment_filename="downloaded_"+file.filename, as_attachment=True)

if __name__ == '__main__':
    app.debug = True
    app.run(port=5001)
