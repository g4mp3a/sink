import numpy as np
import matplotlib.pyplot as plt
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error as mse

"""
Show that model bias decreases as the degree of the polynomial increases,
while variance between the models increases.
"""

NUM_DATASETS = 50
NOISE_VARIANCE = 0.5
# Max degree of polynomial
MAX_DEGREE = 12
# Num of data points
N = 25
# Training data set to be 90% of total data points
Ntrain = int(0.9 * N)
# To get the same results consistently, set seed for random number generator
np.random.seed(2)

# Make a polynomial like x^D, x^(D-1), ..., x^0
def make_poly(x,D):
    N = len(x)
    X = np.empty((N, D+1)) # +1 for bias term

    for d in range(D+1):
        X[:,d] = x**d
        # Normalize column when d > 1 so columns are zero mean unit variance
        if d > 1:
            X[:,d] = (X[:,d] - X[:,d].mean()) / X[:,d].std() # deviation from mean divided by std

    return X

# f(X) takes as input a one dimensional X, not the polynomial
def f(X):
    return np.sin(X)

x_axis = np.linspace(-np.pi, np.pi, 100) # 100 points between -pi to pi
y_axis = f(x_axis)

# Define data set X
# f(x) = sin(x) from x [-pi, +pi]
X = np.linspace(-np.pi, np.pi, N)
np.random.shuffle(X)
f_X = f(X)

# Create polynomial out of X
Xpoly = make_poly(X, MAX_DEGREE)

# Create empty arrays to store data as we loop through the experiments
train_scores = np.zeros((NUM_DATASETS, MAX_DEGREE))
test_scores = np.zeros((NUM_DATASETS, MAX_DEGREE))
# squared_biases = np.zeros((NUM_DATASETS, MAX_POLY))
# test_predictions = np.zeros((N - Ntrain, NUM_DATASETS, MAX_POLY))
train_predictions = np.zeros((Ntrain, NUM_DATASETS, MAX_DEGREE))
prediction_curves = np.zeros((100, NUM_DATASETS, MAX_DEGREE))

# Now lets do an experiment NUM_DATASET times,
# Randomly generating training data sets from our existing training data,
# Fitting models to these training data sets,
# Then average these models to find their bias and variance

model = LinearRegression()

for k in range(NUM_DATASETS):
    # Set up y = f(x) + random noise
    Y = f_X + np.random.randn(N)*NOISE_VARIANCE

    Xtrain = Xpoly[:Ntrain]
    Ytrain = Y[:Ntrain]

    Xtest = Xpoly[Ntrain:]
    Ytest = Y[Ntrain:]

    for d in range(MAX_DEGREE):
        model.fit(Xtrain[:, :d+2], Ytrain)
        predictions = model.predict(Xpoly[:, :d+2])

        x_axis_poly = make_poly(x_axis, d+1)
        prediction_axis = model.predict(x_axis_poly)
        # plt.plot(x_axis, prediction_axis)
        # plt.show()

        prediction_curves[:,k,d] = prediction_axis

        train_prediction = predictions[:Ntrain]
        test_prediction = predictions[Ntrain:]

        train_predictions[:,k,d] = train_prediction

        train_score = mse(train_prediction, Ytrain)
        test_score = mse(test_prediction, Ytest)

        train_scores[k,d] = train_score
        test_scores[k,d] = test_score

# plot the prediction curves for each polynomial degree along with the mean curve
for d in range(MAX_DEGREE):
    for k in range(NUM_DATASETS):
        plt.plot(x_axis, prediction_curves[:,k,d], color='green', alpha=0.5) # alpha sets opacity of the line curve
    # Plot average of all prediction curves for this degree as a thick blue line
    plt.plot(x_axis, prediction_curves[:,:,d].mean(axis=1), color='blue', lineWidth=2.0)
    plt.title("All curves for degree %d" % (d+1))
    plt.show()

# Calculate squared bias
avg_train_prediction = np.zeros((Ntrain, MAX_DEGREE))
squared_bias = np.zeros(MAX_DEGREE)
f_Xtrain = f_X[:Ntrain]
for d in range(MAX_DEGREE):
    for i in range(Ntrain):
        # Average of all experiments training data point 'i' for degree 'd'
        avg_train_prediction[i,d] = train_predictions[i,:,d].mean()
    # MSE over all training sets
    squared_bias[d] = ((avg_train_prediction[:,d] - f_Xtrain)**2).mean()

# Calculate variances
variances = np.zeros((Ntrain, MAX_DEGREE))
for d in range(MAX_DEGREE):
    for i in range(Ntrain):
        delta = train_predictions[i,:,d] - avg_train_prediction[i,d]
        # Maximum likelihood of a variance
        variances[i,d] = delta.dot(delta) / N
variance = variances.mean(axis=0)

# Plot bias-variance
degrees = np.arange(MAX_DEGREE) + 1
best_degree = np.argmin(test_scores.mean(axis=0)) + 1 # +1 to adjust for count starting at 0, we want it starting at 1
plt.plot(degrees, squared_bias, label='squared bias')
plt.plot(degrees, variance, label='variance')
plt.plot(degrees, test_scores.mean(axis=0), label='test scores')
plt.plot(degrees, squared_bias + variance, label='squared bias + variance')
plt.axvline(x=best_degree, linestyle="--", label='best complexity') # vertical line showing best complexity
plt.legend()
plt.show()

plt.plot(degrees, train_scores.mean(axis=0), label='train scores')
plt.plot(degrees, test_scores.mean(axis=0), label='test scores')
plt.axvline(x=best_degree, linestyle="--", label='best complexity')
plt.legend()
plt.show()
