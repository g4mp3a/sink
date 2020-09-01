from email.mime.text import MIMEText
import smtplib

def send_email(email_address, height, avg_ht):
    from_email = "gautam10priya@gmail.com"
    from_password = "@KhulJ@J@sim-sim1"
    to_email = email_address

    subject = "Thank you for participating in Height Survey"
    message = "Your height is <strong>%s</strong>.<br>National average is %s." % (height, avg_ht)

    msg = MIMEText(message, 'html')
    msg['Subject'] = subject
    msg['To'] = to_email
    msg['From'] = from_email

    gmail = smtplib.SMTP('smtp.gmail.com', 587)
    gmail.ehlo()
    gmail.starttls()
    gmail.login(from_email, from_password)
    gmail.send_message(msg)
