Decision Trees:
In general a DT asks a question and classifies a person based on a Y/N answer. DT can also be based on ranked ddata. Classification can be based on categories or numeric. Numeric data can be combined with Y/N data. The final classifications can be repeated. Start at top and work down till you cant go any further. Root, Internal nodes, Leaf nodes.


How to decide root of the tree. Impure leaf nodes. Measure impurity using Gini impurity. Gini impurity is given by 1 - (probablity of yes)squared - (probability of no)squared. The Gini impurity for chest pain is the weighted average of the leaf node impurities given that the leaf nodes dont represent equal size populations. The feature with the lowest Gini impurity is chosen as the root as it best separates the population.

For Y/N questions.
1. Calculate all the Gini impurity scores
2. If the node has the lowest score, then there is no point in separating the data any more and make it a leaf node.
3. If separating the data results in an improvement, then pick the separation with the lowest impurity score.

For numeric data ,like patient weight, how can we determine the root of the tree aka. the best weight to divide the patients. Using the steps below -
1. Sort the patients by weight, lowest to highest.
2. Calculate the average weight for all adjacent patients
3. Calculate the Gini impurity values for each average weight. Pick the lowest score.

For Ranked data: like joke ranks or multipiple choice data
Ranked data is similar to numeric data except instead now we calculate impurity scores for all possible ranks. Dont need to calculate the score for the highest rank as every data point would fall before it.
For multiple choices like colour choice being R,B og G, we need to calculate the score for each option as well as for each possible combination.

Random Forests:
DTs are easy to build, easy to use and easy to interpret. As a practical matter tho, trees have one aspect that prevents them from being the ideal tool for predictive learning, namely inaccuracy.
In other words they work great with the data used to create them, but they are not flexible when it comes to classifying new samples. In otherwords they overfit their data when allowed to grow to the max allowable depth and hence these models are not good at making predictions. In other words, they have low bias and large variance.

Random forests combine the simplicity of DTs with flexibility resulting in a vast improvement in accuracy.
How to create a RF.
1. Create a bootstrapped data set. To create a bootstrapped dataset that is the same size as the original, wen can randomly select samples from the original dataset; note that we can pick the same sample more than once. So sampling with replacement.
2. Create a DT from the bootstrapped dataset, but only use a randomly selected subset of variables (columns) at each step.
3. Repeat steps 1 and 2 a large number of times: Make a new bootstrapped dataset and build a tree using a randomly selected subset of variables at each step.
The above steps result in a wide variety of DTs. This variety is what makes Random Forests more effective than DTs.

To make a prediction, run the new data set through all of the DTs in the forest and calculate the votes for each outcome. The predicted outcome is the one with the most votes. This is sort of like averaging all the DTs into a final predictive model.

Bootstrapping the data plus using the aggregate to make a decision is called Bagging.

How to find out the accuracy of the RF
Cross Validation of DTs is done using the out-of-bag dataset and checking if the DTs built without using the oob dataset correctly predicts the outcome for the oob sample. Ultimately the accuracy measure of the RF is given by the proportion of oob samples that are correctly classified the RF. Incorrectly classified proportion is the oob error.

We can compare the oob error for RFs built using different numbers of variables at each step and choose the RF with the lowest error. In other words, we
1. Build a RF
2. Estimate the accuracy of the RF
3. Change the number of variables used per step and repeat steps 1 and 2.
Do the above a number of times to build different RFs and choose the most accurate one aka one with the lowest oob error.
Typically start by using the square of the number of variables and then try a few settings above and below that value.

Missing Data
RF consider 2 types of missing data - 
1. Missing data in the original dataset used to create the random forest.
2. Missing data in the sample that needs to classified

Missing data in original dataset: The general idea for dealing with these situations is to make a initial guess that could be bad, then gradually refine that guess. Initial guess for a Y/N value variable can be the common value for that variable in the dataset. For numeric variables, initial guess could be the median value. 

Now to refine the guessed values, we start by determining which samples are similar to the sample with missing data. 

How do we determine similarity?
Step 1: Build a RF.
Step 2: Run all fo the data down all of the DTs. Keep track of samples that end up at the same leaf node of a DT coz such samples are considered to be similar. We keep track of similar samples for each tree using a proximity matrix. Such a proximity matirx has a row for each sample and a column for each sample. Cells at the intersection of samples that ended up at the same leaf node have their values incremented by 1. We do this over all DTs.
Step 3: After running the data down all the trees, we get a filled up proximity matrix. WE then divide the numbers in the matrix by the total number of DTs.

Ste 4: Calculate new estimates using proximity matrix.
Now we use the proximity values for sample 4 to refine the initial guesses about the missing data. 

For example, for a Y/N data column, we calculate the new estimate for the missing data by taking the weighted average using proximity values to determine the weights. The weighted frequency for Y is the frequency of Y times the weight for Y. The weight for Y is the proximity of Y divided by all proximities for the sample with missing data. The proximity for Y is the sum of the proximities in the Y valued columns. We select the value having the higher weighted frequency as the new estimate value. 

For numeric data columns, we use promixites to calculate a weighted average of the column data to obtain a new estimate value. So the new estimate is equal to the sum over all column values of (column value * (proximity value / all proximity values)).

Step 5: Now we do all the above steps all over again with the new estimates, aka, build a RF, run the data through all the DTs, recalculate the proximities and recalculate the missing values. 
We do this 6 or 7 times until the missing values converge ie. no longer change when recalculated.

Notes about the proximity matrix:
A proximity value of 1 means the samples are as close as close can be.
1 - proximity value is a measure of separation or distance between samples.
So we can easily create a distance matrix from a proximity matrix. Distance matrix can be used to create heat maps, or MDS plots - multidimensional scaling plots are scatter plots representing distances/dissimiliarities between samples in a visual way.

This means that if we can use a data set to create a tree, we can draw heatmaps or MDS plots to show how the samples are related to each other.

Now about dealing with missing data in a new sample that we want to classify
Step 1: Make 2 copies of the data - one with positive classification (a Y outcome) and another N outcome.
Step 2. Then use the method described in the previous section to estimate the missing value in both copies of the data.
Step 3: Then run both the samples down all the DTs in the RF and determine which of the two is correclty labeled by the RF most of the times. 
The option that was correctly labeled the most number of times wins. We have now filled in the missing data and classified our sample.


The sample mean of B Random, Independent and Identically Distributed (IID) variables, each with variance std_dev squared, is 1/B times std\_dev squared. The sample mean of B Identically Distributed (ID) but not Independent variables is given by (1-correlation)/B times std\_dev sqaured plus correlation times std\_dev squared. 
Random Forest tries to reduce this correlation ie. build a set of trees decorrelated from each other. Bagging techniques employ a large number of overfitting trees ie. trees that are most probably very different from each other. In other words, in bagging we try to build a an average model from many high variance low bias models. Trees when allowed to grow to arbitraty depths are perfect for this as they can achieve 100% accuracy on the training set but have high variance aka. they are not very accurate when predicting/classifying new data samples. But due to the previous equation for the variance of an ensemble, we can achieve much lower combined variance by finding trees that are not correlated with each other. So other than allowing trees to grow to max depths on different bootstrap samples, what techniques can be useful in esuring that trees are not correlated with each other. In bootstrapping we randomly select which samples to train on, just so we can randomly select which features to train on. This is called feature bagging and is one technique used to create trees that are not correlated with each other. How do we choose the number of features to train on? Recommended values are as follows -
For Classification use floor(swaure root of D), uto a low of 1 and
For regression use floor(D/3), upto a low of 5 and where D is total number of reatures in the dataset.
The best option is to choose a value of d (<D) that works best for a given data set using cross validation techniques.

Notes on Predictions:
For binary classfications, the output is the rounded average value. For regression, the output is the average.

Notes on Noise in data:
What if a large number of features are just noise and what if RF chooses only noise to split on and what if it does this for a large proportion of trees? For example, for d = 10, the probability of choosing at least 1 relevant feature from a dataset having 3 relevant features and 100 irrelevant features is 26.6%. With 6 relevant features, this goes up to 46%.

Advantages of RF:
1. Very little training is needed.
2. Can let all trees go to arbitrary depth w/o incurring much penalty.
3. Fast and well performing.

Random Forests can be a good alternative to plug and play deep leanring (API) solutions considering Neural Nets have many more hyperparameters and are sensitive to those choices.

Connection to Deep Learning
Dropout is a regularization technique used in modern Neural Networks. In this technique, a subset of randomly selected nodes in each layer are dropped ie. they dont feed any information forward to the rest of the network. This is similar to throwing away features. Thus, there are 2 to the power N different neural networks that we can obtain from a nerual network of N nodes. Dropout regularization is used to reduce the training times of an ensemble of neural networks. Neural networks of a medium size take approximately an hour or more to train. To train a ensemble of 200 networks, it can take upwards of 200 hours. For larger values of N (~ 1000, a resonable value), the training times are exponentially higher. Dropout regularization emulates 2 to the power of N networks by randomly dropping nodes. Say a network drops nodes with a probability of p(drop) during training, then during prediction we dont drop any nodes, but multiply every layer by 1-p(drop)



