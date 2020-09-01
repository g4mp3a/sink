Irreducible Error
Data generating processes are noise. Noise is random aka cant predict its values, only its stats like mean and variance
Linear regression model is: y = ax + b + epsilon where epsilon ~ N(0, sigma squared). Epsilon is gaussian distrubuted with 0 mean.
Even f_hat(x) = 2x + 1 does not achieve 0 error on y = 2x + 1 + epsilon

Bias
Bias is the delta between the model and the true f(x). PS: Some sources refer to the square of this delta as the bias.
bias = E[f(x) - f_hat(x)]

High bias implies the model is far off from the general trend in the data. Simple.
Low bias implies the model is overfitted, ie. it fits the data perfectly, but it does not represent the general trend in the data. Very complex.

Variance
In statistics, variance is the deviation of a random variable from its mean in squared units.
Specifically in the context of bias-variance trade off, variance is the statistical variance(MSE) of the predictor over all possible training sets that are drawn from the same data generating process. For example, in the case overfitting we get models that fit every training set perfectly, but the variance between models for different training sets would be large. In other words, techniques that overfit, models created from training sets obtained from the same data generating process vary greatly. These models are said to have low bias (towards the training data), but high variance (between the models). On the flip side, in case of very simple fitting techniques, models created by learning from training sets obtained from the same data generating process have low variance but suffer from large bias.

Variance has nothing to do with accuracy. It is only a measure of the inconsistency of a predictor over different training sets. Variance is a proxy for model complexity. Complexity is a malleable term. For example, a deep decision tree is complex, a shallow one is not. In KNN, K=1 implies complex, K=50 does not.

The goal is to find the true f(x). Being close to training points is a proxy solution.

In linear models, regularization encourages weights to be small or 0 and thus helps decrease model complexity by preventing overfitting.

We can not in a general sense say that linear models are more or less complex that non linear models. A large dimension linear model can be more complex than a non linear model with a small number of inputs (aka small D).


Bias-Variace Trade Off
Overall error is a combination of (1)bias, (2)variance, and (3)irreducible error. In ML, the best we can do is to minimize the error to the irreducible error. At this point, the reducible error is 0 and we know the true f(x). The goal then is to get the bias and variance to be as small as possible. The problem is changing one inversely affects the other.

For describing bias variance trade off, the bulls eye example can be handy.
1. We are close to f(x) when the dots are concentrated in the center.
2. When dots are concentrated near a point away from the center, the predictor has low variance but suffers from high bias.
3. When dots are generally close to the center but not concentrated around a point, the predictor has low bias but high variance.
4. When the dots are neither concentrated around a point nor close to the center, we are way off of the mark.

The bias-variace trade-off occurs in the context of looking at the same model, but altering the complexiy of the model. 

Can models be combined such that we achieve better fit as well as better generalization?


Bias-Variance Decomposition
Expected error of a model = bias squared + variance + irreducible error.
Mean squared error is used for both regression and classification models despite the fact that MSE is not used for optimizing the classifier.

Identities:
Mean of noise E[epsilon] = 0
Mean of noise squared = variance of the noise

Bias is the delta between the ground truth function and the average estimator.
Variance is the variance of the estimator aka MSE of the estimator
Irreducible error is the variance of the data noise.

PS: This is not the error between true f(x) and f_hat(x). We can't find that error coz we never see the true f(x). We only see y which is f(x) + noise.

Cross-Validation
K-Fold cross-validation is way to choose good hyper parameters. Cross-validation always returns the scores for predictions outside the training set which is what we use as an estimate of test error.

From the polynomial regression example, we found that the lowest test error coincides with the point at which the sum of bias sqaured and variance is also minimum. 
Therefore, when we use cross-validation to look for the minimum test error, we are optimizing the sum of bias sqaured and variance as well. In this way we use cross-validation to find the best model complexity which is akin to chooing the best hyperparameters. Similarly, we can say that optimizing the sum of bias squared and variance corresponds to minimizing the generalization error.

K-Fold Cross-Validation method
Split data into K parts (typical values for K = 5,8,10)
Loop K time
In each iteration, use 1 part for validation and the rest for training

This returns K test scores. We can use the mean of these scores as a measure of how good the hyperparameter setting is. We can also use statistical testing to check if any one setting is satistically significantly better than another.

Leave-One-Out Cross-Validation
A variation on K-Fold cross-validation. We set K=N and we train on N-1 points and perform validation on the one point we left out in a loop of N iterations.







