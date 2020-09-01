import cv2
import time

video = cv2.VideoCapture(0)

while video.isOpened():
    check, frame = video.read()
    gray_frame = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)
    cv2.imshow("Video", gray_frame)

    key = cv2.waitKey(1)
    if key == ord('q'):
        break
#    if cv2.waitKey(1) & 0xFF == ord('q'):
#        break

video.release()
cv2.destroyAllWindows()

"""
video = cv2.VideoCapture(0)
# to allow the program to capture video for 3 seconds
time.sleep(30)

check, frame = video.read()
print(check)
print(frame)

cv2.imshow("Video", frame)
cv2.waitKey(0)

video.release()
cv2.destroyAllWindows()
"""