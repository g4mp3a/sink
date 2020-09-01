import numpy as np
import matplotlib.pyplot as plt
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestRegressor, BaggingRegressor, RandomForestClassifier, BaggingClassifier
from bootstrapping_and_bagging import BaggingTreeClassifier
from rf_classifier import get_data

# make simple regression data
# N = 15
# D = 100
# X = (np.random.random((N, D)) - 0.5)*10
# Y = X.sum(axis=1)**2 + 0.5*np.random.randn(N)
# Ntrain = N/2
# Xtrain = X[:Ntrain]
# Ytrain = Y[:Ntrain]
# Xtest = X[Ntrain:]
# Ytest = Y[Ntrain:]

X, Y = get_data()
Ntrain = int(0.8*len(X))
Xtrain, Ytrain = X[:Ntrain], Y[:Ntrain]
Xtest, Ytest = X[Ntrain:], Y[Ntrain:]

# from rf_regression import get_data
# Xtrain, Ytrain, Xtest, Ytest = get_data()

# Implmentation of a Random Forest where trees are built using only 1 feature bag,
# as opposed to 1 feature bag per split in a tree
class NotAsRandomForest:
    def __init__(self, n_estimators):
        self.B = n_estimators

    def fit(self, X, Y, M=None):
        N, D = X.shape
        if M is None:
            M = int(np.sqrt(D))

        self.models = []
        self.features = []
        # Create trees that are trained on only 1 subset of features
        for b in range(self.B):
            tree = DecisionTreeClassifier()
            # Feature bagging
            features = np.random.choice(D, size=M, replace=False)
            # Bootstrapping samples
            idx = np.random.choice(N, size=N, replace=True)
            Xb = X[idx]
            Yb = Y[idx]
            tree.fit(Xb[:, features], Yb)
            self.features.append(features)
            self.models.append(tree)

    def predict(self, X):
        N = len(X)
        P = np.zeros(N)
        for features, tree in zip(self.features, self.models):
            P += tree.predict(X[:, features])
        return np.round(P / self.B)

    def score(self, X, Y):
        P = self.predict(X)
        return np.mean(P == Y)


T = 100
test_accuracy_prf = np.empty(T)
test_accuracy_rf = np.empty(T)
test_accuracy_bag = np.empty(T)
for num_trees in range(T):
    if num_trees == 0:
        test_accuracy_prf[num_trees] = None
        test_accuracy_rf[num_trees] = None
        test_accuracy_bag[num_trees] = None
    else:
        rf = RandomForestClassifier(n_estimators=num_trees)
        rf.fit(Xtrain, Ytrain)
        test_accuracy_rf[num_trees] = rf.score(Xtest, Ytest)

        bg = BaggingTreeClassifier(n_estimators=num_trees)
        bg.fit(Xtrain, Ytrain)
        test_accuracy_bag[num_trees] = bg.score(Xtest, Ytest)

        prf = NotAsRandomForest(n_estimators=num_trees)
        prf.fit(Xtrain, Ytrain)
        test_accuracy_prf[num_trees] = prf.score(Xtest, Ytest)

plt.plot(test_accuracy_rf, label='Random Forest')
plt.plot(test_accuracy_prf, label='Pseudo Random Forest')
plt.plot(test_accuracy_bag, label='Bagging')
plt.legend()
plt.show()
