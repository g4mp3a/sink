import time
from datetime import datetime as dt

hosts_file = "hosts"
redirect = "127.0.0.1"
website_list=["www.cnn.com", "cnn.com", "www.facebook.com", "facebook.com"]


while True:
    if dt(dt.now().year, dt.now().month, dt.now().day, 8) < dt.now() < dt(dt.now().year, dt.now().month, dt.now().day, 13):
        print("Working hours ...")
        with open(hosts_file, "r+") as f:
            content = f.read()
            for website in website_list:
                if website not in content:
                    f.write("\n" + redirect + " " + website)
    else:
        print("Fun hours ...")
        with open(hosts_file, "r+") as f:
            content_lines = f.readlines()
            f.seek(0)
            for line in content_lines:
                if not any(website in line for website in website_list):
                    f.write(line)
            f.truncate()

    time.sleep(5)


