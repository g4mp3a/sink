import cv2

face_classifier = cv2.CascadeClassifier("haarcascade_frontalface_default.xml")

img = cv2.imread("photo.jpg")
gray_img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

faces = face_classifier.detectMultiScale(gray_img, scaleFactor=1.05, minNeighbors=5)

print(type(faces))
print(faces)

for x, y, w, h in faces:
    img = cv2.rectangle(img, (x,y), (x+w, y+h), (0, 255, 0), 3)

resized_img = cv2.resize(img, (int(img.shape[0]/3), int(img.shape[1]/3)))

cv2.imshow("Faces Resized", resized_img)
cv2.imshow("Faces", img)
cv2.waitKey()
cv2.destroyAllWindows()

