Bias Variance Trade-Off
Expected error is a combination of bias, variance, and irreducible error
Irreducible error aka noise can not be reduced; only bias and variance can be reduced.
Bias and variance are inversely related to each other
Reducing test error represents a happy medium
Ensemble methods can lower both variance and bias, sort of.

Bootstrapping
Estimate confidence intervals
Reduces variance by simple calculating that variance over and over again with resampled datasets.
When the correlation between bootstrap samples/models is 1, no reduction in variance is achieved; but when the correlation between bootstrap samples/models is 0, we achieve a reduction of the order of B, the number of samples.
Using DTs of max depth trained on different bootstrapped samples, we can build a set of models that have very decorrelated from each other.

Random Forest
Further decorrelated models by using feature bagging in addition to bagging above.

Dropout regularization in neural networks randomly samples features in a manner similar to feature bagging and thus emulates an ensemble of neural networks.

Adaboost
Uses high bias and low viariance DTs. A set of weak learners combine together to build a strong learner when weighted properly. This works really well given the low test error rates.

A 1-hidden-layer feed forward neural network has the same structure as Adaboost using a logistic base learner. Adaboost performs well even though it does not optimize its paramters globally.

Beyond Boosting
Mixture of experts: Each base learner is an expert at some thing different. Model weights are a function of the inputs, meaning some models are better at making some kinds of classifications than others.
Gradient boosting:

Notes:
mu(hat) - mu is a 0 centered gaussian with a variane of sigma squared over N.
sigma squared over N is the variance of mu(hat)
Need a large of samples aka N to decrease the variance of mu(hat), while only a small increase in sigma aka variance of X can lead to large increase in the variance of mu(hat)
Confidence Intervals are the range of values that are likely to contain true mu with a certain amount of confidence; aka over a large # of experiments, the true value of the sample mean would lie within those intervals with the said confidence value.
Confidence level = 1 - significance level (alpha)
CDF (Cumulative Distribution Function) is related to PDF (Probability Distribution Function)
The formula used to obtain the CI (Confidence Interval) of a Gaussian distribution can be used to estimate the CI of a Bernoulli distribution as well.
Remember CLT (Central Limit Theorem). This shows that the maximum likelihood estimate of mu is Gaussian distributed.
In non-Bayesian methods we need to collect a large # of samples to make the CI smaller compared to the standard deviation, sigma.
