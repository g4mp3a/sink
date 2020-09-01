import cv2

img = cv2.imread("galaxy.jpg", 0)

print(type(img)) # numpy array
print(img)
print(img.shape)
print(img.ndim)

resized_img = cv2.resize(img, (int(img.shape[0]/2), int(img.shape[1]/2)))
cv2.imshow("Galaxy", resized_img)
cv2.imwrite("Resized_Galaxy.jpg", resized_img)
cv2.waitKey(0) # 0 waits for key press, else num of milisecs before closing
cv2.destroyAllWindows()
