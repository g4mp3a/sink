from keras.datasets import mnist
import numpy as np
(X_train, y_train), (X_test, y_test) = mnist.load_data()

<<<<<<< HEAD
idx = 10
=======

>>>>>>> 98c477bf28b80d359c8ef643239bbaf2502089df

digit = X_test[idx]
print(digit.shape)
str = ""
for i in range(digit.shape[0]):
    for j in range(digit.shape[1]):
        if digit[i][j] == 0:
            str += " "
        elif digit[i][j] < 128:
            str += "."
        else:
            str += "X"
    str += "\n"

print(str)
print("Label: ", y_train[idx])
