import numpy as np
import matplotlib.pyplot as plt
from sklearn.tree import DecisionTreeClassifier
from random_forests import get_data

class AdaBoost:
    def __init__(self, M):
        self.M = M

    def fit(self, X, Y):
        self.models = []
        self.alphas = []

        N, _ = X.shape
        W = np.ones(N) / N

        for m in range(self.M):
            tree = DecisionTreeClassifier(max_depth=1)
            tree.fit(X, Y, sample_weight=W)
            P = tree.predict(X)

            err = W.dot(P != Y)
            alpha = 0.5*(np.log(1 - err) - np.log(err))

            W = W*np.exp(-alpha*Y*P)
            W = W / W.sum() # Normalizing so it sums to 1

            self.models.append(tree)
            self.alphas.append(alpha)

    def predict(self, X):
    # Return both accuracy and exponential loss for plotting purposes
        N, _ = X.shape
        FX = np.zeros(N)
        for alpha, tree in zip(self.alphas, self.models):
            FX += alpha*tree.predict(X)
        return np.sign(FX), FX

    def score(self, X, Y):
    # Return both accuracy and exponential loss for plotting purposes
        P, FX = self.predict(X)
        L = np.exp(-Y*FX).mean()
        return np.mean(P == Y), L

if __name__ == '__main__':
    X, Y = get_data()
    Y[Y == 0] = -1  # Change the targets to -1, +1
    Ntrain = int(0.8 * len(X))
    Xtrain, Ytrain = X[:Ntrain], Y[:Ntrain]
    Xtest, Ytest = X[Ntrain:], Y[Ntrain:]

    T = 200
    train_errors = np.empty(T)
    test_losses = np.empty(T)
    test_errors = np.empty(T)
    for num_trees in range(T):
        if num_trees == 0:
            train_errors[num_trees] = None
            test_errors[num_trees] = None
            test_losses[num_trees] = None
            continue

        model = AdaBoost(num_trees)
        model.fit(Xtrain, Ytrain)
        test_acc, test_loss = model.score(Xtest, Ytest)
        train_acc, _ = model.score(Xtrain, Ytrain)
        train_errors[num_trees] = 1 - train_acc
        test_errors[num_trees] = 1 - test_acc
        test_losses[num_trees] = test_loss

        # For this data set, test and train errors zeroed out at a size of 100 stumps
        # Final test loss ~0.00116
        if num_trees % 20 == 0 or num_trees == T-1:
            print("Ensemble size: ", num_trees)
            print("Train error: ", 1 - train_acc)
            print("Test error: ", 1 - test_acc)
            print("Test Loss: ", test_loss)

    # Confirm that loss continues to decrease  or the loss function continues to be optimized,
    # even after the test errors have converged.
    plt.plot(test_errors, label='Test Errors')
    plt.plot(test_losses, label='Test Losses')
    plt.legend()
    plt.show()

    # Confirm that test errors do NOT increase when we keep increasing the size of the ensemble
    # past train convergence. This is an advantage over random forests and neural nets.
    plt.plot(train_errors, label='Train Errors')
    plt.plot(test_errors, label='Test Errors')
    plt.legend()
    plt.show()
