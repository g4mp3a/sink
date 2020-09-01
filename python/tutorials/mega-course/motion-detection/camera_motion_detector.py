import cv2
from datetime import datetime
import time
import pandas

first_frame = None
# Initialized to [None, None] to avoid access issues with accessing [-2]
status_list = [None, None]
times = []
video = cv2.VideoCapture(0)
time.sleep(4)
df = pandas.DataFrame(columns=["Start", "End"])

while video.isOpened():
    check, frame = video.read()
    # Used to indicate an object entering the frame
    status = 0

    gray_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    blurred_gray_frame = cv2.GaussianBlur(gray_frame, (5,5), 0)
    #blurred_gray_frame = cv2.medianBlur(gray_frame, 5) This worked as well as gaussian
    #blurred_gray_frame = cv2.bilateralFilter(gray_frame,9,75,75) This was OK, need to research this more

    if first_frame is None:
        first_frame = blurred_gray_frame
        continue

    delta_frame = cv2.absdiff(first_frame, blurred_gray_frame)
    threshold_frame = cv2.threshold(delta_frame, 50, 255, cv2.THRESH_BINARY)[1]
    dilated_threshold_frame = cv2.dilate(threshold_frame, None, iterations=2)

    (contours,_) = cv2.findContours(dilated_threshold_frame.copy(), cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE)

    for contour in contours:
        # 100 x 1000 pixels is being considered big enough to be a real object
        if cv2.contourArea(contour) < 100 * 1000:
            continue
        status = 1
        (x, y, w, h) = cv2.boundingRect(contour)
        cv2.rectangle(frame, (x,y), (x+w,y+h), (0,255,0), 3)

    status_list.append(status)
    status_list = status_list[-2:]

    if status_list[-1] == 1 and status_list[-2] == 0:
        times.append(datetime.now())
    if status_list[-1] == 0 and status_list[-2] == 1:
        times.append(datetime.now())

    cv2.imshow("Video", frame)

    #cv2.imshow("Video", gray_frame)
    #cv2.imshow("Blurred", blurred_gray_frame)
    #cv2.imshow("Delta", delta_frame)
    #print(gray_frame)
    #print(blurred_gray_frame)
    #print(threshold_frame)

    key = cv2.waitKey(1)
    if key == ord('q'):
        if status == 1:
            times.append(datetime.now())
        break

for i in range(0, len(times), 2):
    df = df.append({"Start":times[i], "End":times[i+1]}, ignore_index=True)

df.to_csv("times.csv")

video.release()
cv2.destroyAllWindows()
