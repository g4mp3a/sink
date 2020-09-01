import numpy as np
import matplotlib.pyplot as plt
from sklearn.tree import DecisionTreeClassifier, DecisionTreeRegressor
from sklearn.neighbors import KNeighborsClassifier, KNeighborsRegressor
from sklearn.utils import shuffle

PI = np.pi

# Regression
# create data
N = 20
Ntrain = 12
X = np.linspace(0, 2*PI, N).reshape(N,1)
Y = np.sin(3*X)
X,Y = shuffle(X,Y)

Xtrain = X[:Ntrain]
Ytrain = Y[:Ntrain]

# Bias variance relationship in Decision Trees
# Low bias, High variance demo
model = DecisionTreeRegressor() # default max_depth=None implies a dt that is as deep as possible, aka low bias
model.fit(Xtrain, Ytrain)
T = 50 # to get a smooth plot
Xaxis = np.linspace(0, 2*PI, T)
Yaxis = np.sin(3*Xaxis)
# Plot
plt.title("Decision Tree Regressor: Low Bias, High Variance")
plt.scatter(Xtrain, Ytrain, s=50, alpha=0.7, c='blue')
plt.scatter(Xtrain, model.predict(Xtrain.reshape(Ntrain, 1)), s=50, alpha=0.7, c='green')
plt.plot(Xaxis, Yaxis)
plt.plot(Xaxis, model.predict(Xaxis.reshape(T,1)))
plt.show()

# High bias, Low variance demo
model = DecisionTreeRegressor(max_depth=1) # dt of depth implies high bias
model.fit(Xtrain, Ytrain)
# Plot
plt.title("Decision Tree Regressor: High Bias, Low Variance")
plt.scatter(Xtrain, Ytrain, s=50, alpha=0.7, c='blue')
plt.scatter(Xtrain, model.predict(Xtrain.reshape(Ntrain, 1)), s=50, alpha=0.7, c='green')
plt.plot(Xaxis, Yaxis)
plt.plot(Xaxis, model.predict(Xaxis.reshape(T,1)))
plt.show()

# Bias Variance relationship for K nearest neighbour
# High bias, Low variance
model = KNeighborsRegressor(n_neighbors=1)
model.fit(Xtrain, Ytrain)
# Plot
plt.title("KNN Regressor: Low bias, High variance")
plt.scatter(Xtrain, Ytrain, s=50, alpha=0.7, c='blue')
plt.scatter(Xtrain, model.predict(Xtrain.reshape(Ntrain, 1)), s=50, alpha=0.7, c='green')
plt.plot(Xaxis, Yaxis)
plt.plot(Xaxis, model.predict(Xaxis.reshape(T, 1)))
plt.show()

# High bias, Low variance
model = KNeighborsRegressor(n_neighbors=10)
model.fit(Xtrain, Ytrain)
# Plot
plt.title("KNN Regressor: High bias, Low variance")
plt.scatter(Xtrain, Ytrain, s=50, alpha=0.7, c='blue')
plt.scatter(Xtrain, model.predict(Xtrain.reshape(Ntrain, 1)), s=50, alpha=0.7, c='green')
plt.plot(Xaxis, Yaxis)
plt.plot(Xaxis, model.predict(Xaxis.reshape(T, 1)))
plt.show()

# Classification
# create data
N = 100
D = 2
# Randomly pick numbers from a gaussian distribution to build 100 records in 2 dimensions
X = np.random.randn(N, D)
X[:N//2] += np.array([1,1]) # Center half the records at (1,1)
X[N//2:] += np.array([-1,-1]) # Center other half at (-1,-1)

Y = np.array([0]*(N//2) + [1]*(N//2))

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

# Low bias, High variance in Decision Tree
model = DecisionTreeClassifier()
model.fit(X,Y)
# Plot
plt.title("Decision Tree Classifier: Low bias, High variance")
plt.scatter(X[:,0], X[:,1], s=50, alpha=0.7, c=Y)
plot_decision_boundary(X, model)
plt.show()

# High bias, Low variance in Decision Tree
model = DecisionTreeClassifier(max_depth=2)
model.fit(X,Y)
# Plot
plt.title("Decision Tree Classifier: High bias, Low variance")
plt.scatter(X[:,0], X[:,1], s=50, alpha=0.7, c=Y)
plot_decision_boundary(X, model)
plt.show()

# Low bias, High variance in KNN
model = KNeighborsClassifier(n_neighbors=1)
model.fit(X, Y)
# Plot
plt.title("KNN Classifier: Low bias, High variance")
plt.scatter(X[:,0], X[:,1], s=50, alpha=0.7, c=Y)
plot_decision_boundary(X, model)
plt.show()

# High bias, Low variance in KNN
model = KNeighborsClassifier(n_neighbors=20)
model.fit(X, Y)
# Plot
plt.title("KNN Classifier: High bias, Low variance")
plt.scatter(X[:,0], X[:,1], s=50, alpha=0.7, c=Y)
plot_decision_boundary(X, model)
plt.show()
