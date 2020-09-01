import numpy as np
import matplotlib.pyplot as plt
from sklearn.tree import DecisionTreeRegressor

PI = np.pi

# Create data
T = 100
x_axis = np.linspace(0, 2*PI, T)
y_axis = np.sin(x_axis)

# Get training data
N = 30
idx = np.random.choice(T, size=N, replace=False)
Xtrain = x_axis[idx].reshape(N, 1)
Ytrain = y_axis[idx]

class BaggingTreeRegressor:
    def __init__(self, n_estimators, max_depth=None):
        self.B = n_estimators
        self.max_depth = max_depth

    def fit(self, X, Y):
        N = len(X)
        self.models = []
        for b in range(self.B):
            idx = np.random.choice(N, size=N, replace=True) # replace=True is the default
            Xb = X[idx]
            Yb = Y[idx]
            model = DecisionTreeRegressor(max_depth=self.max_depth)
            model.fit(Xb, Yb)
            self.models.append(model)

    def predict(self, X):
        predictions = np.zeros(len(X))
        for model in self.models:
            predictions += model.predict(X)
        return predictions / self.B

    def score(self, X, Y):
        d1 = Y - self.predict(X)
        d2 = Y - Y.mean()
        return 1 - d1.dot(d1) / d2.dot(d2)

# Compare single tree with bagging
if __name__ == "__main__":
    # Plot the data
    plt.title("The Data")
    plt.plot(x_axis, y_axis)
    plt.show()

    # Score a single decision tree
    model = DecisionTreeRegressor()
    model.fit(Xtrain, Ytrain)
    prediction = model.predict(x_axis.reshape(T, 1))
    print("Score for 1 tree: %f" % model.score(x_axis.reshape(T, 1), y_axis))
    # Plot single tree's predictions
    plt.plot(x_axis, prediction)
    plt.plot(x_axis, y_axis)
    plt.title("Single Decision Tree's Predictions")
    plt.show()

    # Score bagging tree
    model = BaggingTreeRegressor(n_estimators=200)
    model.fit(Xtrain, Ytrain)
    prediction = model.predict(x_axis.reshape(T, 1))
    print("Score for Bagging: %f" %
          model.score(x_axis.reshape(T, 1), y_axis))
    # Plot the bagging regressor's predictions
    plt.plot(x_axis, prediction)
    plt.plot(x_axis, y_axis)
    plt.title("Bagging Decision Tree's Predictions")
    plt.show()
