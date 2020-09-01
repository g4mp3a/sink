Bootstrap Estimation
It is possible to achieve low bias and low variance by combining several models together by using bootstrapping. Bootstrapping is also called resampling. Specifically we use sampling with replacement.

Why does it work?
The mean of the bootstrapped estimates is equal to the parameter.

If each bootstrapped estimate is completely uncorrelated from the others, then the variance would be the original variance divided by B (number of samples). This imples that the variance is reduced by an order B for every sample. That said, there will be at least some correlation given the sample data set is the same.

Bootstrap mean for any linear model does not greatly improve variance of the model. This is coz for linear models, it can be shown that rho aka the correlation = N/(2N-1) ~ 0.5

Bootstrapping offers biggest advantages when used with highly non linear models like DTs as different samples will produce highly irregular decision boundaries that done correlate with each other.

Bootstrap Aggregating, often called Bagging in short, is a twist on the bootstrap resampling method where we use these samples to train different models and then average them to create a final model. This averaging may or may not reduce variance depending on the model used. So the difference here is that instead of calculating a mean or others stats we use the sample to train a model instead.

Prediction is then done by averaging if regression and voting if classification.
In classification we need to collect the votes. That said, if a classifier returns the class probabilities, then we can just use averaging as in regression.

Stacking
When we simply average out the models, each model countributes to the score in equal measure. Stacking is a way of combining models by assigning weights to models and taking a weighted average over them; this way more accurate models can exert a greater influence over the final model compared to sloppier ones. The technique used to assign different weights to models is called stacking.
