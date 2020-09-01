import numpy as np
import matplotlib.pyplot as plt
from sklearn.tree import DecisionTreeClassifier

# To get the same results consistently, set seed for random number generator
np.random.seed(10)
# Create data
N = 500
D = 2
X = np.random.randn(N, D)

# Noisy XOR
sep = 2
X[:125] += np.array([sep, sep]) # center at (2,2)
X[125:250] += np.array([sep, -sep]) # (2,-2)
X[250:375] += np.array([-sep, -sep]) # (-2-2)
X[375:] += np.array([-sep, sep]) # (-2,2)
Y = np.array([0]*125 + [1]*125 + [0]*125 + [1]*125)

def plot_decision_boundary(X, model):
    h = .02  # step size in the mesh
    # create a mesh to plot in
    x_min, x_max = X[:, 0].min() - 1, X[:, 0].max() + 1
    y_min, y_max = X[:, 1].min() - 1, X[:, 1].max() + 1
    xx, yy = np.meshgrid(np.arange(x_min, x_max, h),
                         np.arange(y_min, y_max, h))

    # Plot the decision boundary. For that, we will assign a color to each
    # point in the mesh [x_min, m_max]x[y_min, y_max].
    Z = model.predict(np.c_[xx.ravel(), yy.ravel()])

    # Put the result into a color plot
    Z = Z.reshape(xx.shape)
    plt.contour(xx, yy, Z, cmap=plt.cm.Paired)

class BaggingTreeClassifier:
    def __init__(self, n_estimators, max_depth=None):
        self.B = n_estimators
        self.max_depth = max_depth

    def fit(self, X, Y):
        N = len(X)
        self.models = []
        for b in range(self.B):
            idx = np.random.choice(N, size=N, replace=True)
            Xb = X[idx]
            Yb = Y[idx]
            model = DecisionTreeClassifier(max_depth=self.max_depth)
            model.fit(Xb, Yb)
            self.models.append(model)

    def predict(self, X):
        # Performing binary classifications
        predictions = np.zeros(len(X))
        for model in self.models:
            predictions += model.predict(X)
        return np.round(predictions / self.B)

    def score(self, X, Y):
        P = self.predict(X)
        return np.mean(Y == P)

# Compare bagging with single DT
if __name__ == "__main__":
    # Plot the data
    plt.scatter(X[:, 0], X[:, 1], s=100, c=Y, alpha=0.5)
    plt.title("The Data")
    plt.show()

    # Single tree
    model = DecisionTreeClassifier()
    model.fit(X, Y)
    print("Score for 1 tree: %f" % model.score(X, Y))
    # Plot data with boundary
    # We see that the boundary is noisy, but score=1
    plt.scatter(X[:,0], X[:,1], s=100, c=Y, alpha=0.5)
    plot_decision_boundary(X, model)
    plt.title("Predictions by 1 DT Classifier")
    plt.show()

    # Bagging
    # Setting max_depth=2 gave a score=0.968
    # Setting max_depth=3 gave a score=0.978
    # Setting max_depth=4 gave a score=0.98, smooth boundary
    # Setting max_depth=5 gave a score=0.986, still a good smooth boundary
    # Setting max_depth=7 gave a score=0.998
    # Setting max_depth=8 gave a score=1.0
    model = BaggingTreeClassifier(n_estimators=200, max_depth=4)
    model.fit(X, Y)
    print("Score for bagging decision tree:", model.score(X, Y))

    # Plot data with boundary
    # We see the boundary is much smoother when averaged over 200 DTs
    # but the score is slightly lower
    plt.scatter(X[:,0], X[:,1], s=100, c=Y, alpha=0.5)
    plot_decision_boundary(X, model)
    plt.title("Predictions by BaggingTreeClassifier")
    plt.show()
