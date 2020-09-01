import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.ensemble import RandomForestRegressor, BaggingRegressor, RandomForestClassifier, BaggingClassifier
from bootstrapping_and_bagging.regression_bagging import BaggingTreeRegressor
from bootstrapping_and_bagging.classification_bagging import BaggingTreeClassifier
from rf_classifier import get_data
# from rf_regressor import get_data

# Cook simple regression data
N = 15
D = 100
X = (np.random.random((N, D)) - 0.5)*10
Y = X.sum(axis=1)**2 + 0.5*np.random.randn(N)
Ntrain = N//2
Xtrain = X[:Ntrain]
Ytrain = Y[:Ntrain]
Xtest = X[Ntrain:]
Ytest = Y[Ntrain:]

# Use data set from rf_classifier
X, Y = get_data()
Ntrain = int(0.8*len(X))
Xtrain, Ytrain = X[:Ntrain], Y[:Ntrain]
Xtest, Ytest = X[Ntrain:], Y[Ntrain:]

# Use data set from rf_regressor
# Xtrain, Ytrain, Xtest, Ytest = get_data()

# T = 200 takes ~7-10 mins to finish
T = 200
test_accuracy_rf = np.empty(T)
test_accuracy_bag = np.empty(T)
for num_trees in range(T):
    if num_trees == 0:
        test_accuracy_rf[num_trees] = None
        test_accuracy_bag[num_trees] = None
    else:
        # rf = RandomForestRegressor(n_estimators=num_trees)
        rf = RandomForestClassifier(n_estimators=num_trees)
        rf.fit(Xtrain, Ytrain)
        test_accuracy_rf[num_trees] = rf.score(Xtest, Ytest)

        # bg = BaggingRegressor(n_estimators=num_trees)
        # bg = BaggingTreeRegressor(n_estimators=num_trees)
        bg = BaggingClassifier(n_estimators=num_trees)
        # bg = BaggingTreeClassifier(n_estimators=num_trees)
        bg.fit(Xtrain, Ytrain)
        test_accuracy_bag[num_trees] = bg.score(Xtest, Ytest)

plt.plot(test_accuracy_rf, label='Random Forest')
plt.plot(test_accuracy_bag, label='Bagging')
plt.legend()
plt.show()
