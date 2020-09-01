from pathlib import Path
import cv2
import glob

path = "sample-images"
basepath = Path(path)

"""
files_in_basepath = basepath.iterdir()
for item in files_in_basepath:
    if item.is_file():
        print(item.name)
"""

basepath = Path('sample-images')
files_in_basepath = (entry for entry in basepath.iterdir() if entry.is_file())
for item in files_in_basepath:
    img = cv2.imread(str(item.absolute()), 1)
    resized_img = cv2.resize(img, (100, 100))
    cv2.imwrite("resized-sample-images/"+item.name, resized_img)
    cv2.imshow("resized-image", resized_img)
    cv2.waitKey(2000)
    cv2.destroyAllWindows()


images = glob.glob(path+"/*.jpg")
for image in images:
    img = cv2.imread(image, 0)
    re = cv2.resize(img, (100, 100))
    cv2.imshow("resized", re)
    cv2.imwrite("resized-greyscale-" + image, re)
    cv2.waitKey(2000)
    cv2.destroyAllWindows()

