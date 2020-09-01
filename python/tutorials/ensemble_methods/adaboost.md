A powerful off the shelf ensemble learning method. However unline random forests and bagging techniques that employ low-bias-high-variance models, adaboost works with high-bias-low-variance models, so called weak learners. Adaboost does not suffer from overfitting problems even as the size of the ensemble increases.

The hypothesis behind boosting is that combining many weak learners can yield a strong learner.

A weak learner is a decision tree with a max depth of 1; it is also called a decision stump. Stumps split the space along only one of the variables/dimensions; thus they can only split the decision space in half. The advantage of weak learners is that they can be trained quickly. 
Note: Another weak learner is Logisitic regression.

Adaboost is very specific to binary classification, requiring labels -1 and +1. It can be extended to multiclass classification and regression. In addition to stumps, linear classifier can also be used as a base mode.

Like with every other learning technique, it is important to make wise choices about the base model (DT, Stump etc.) and the cost or the loss function.

Adaboost uses an exponential loss function: L(y, f(x)) = exp(-1 * yf(x))

There are 3 key differences between Adaboost and Random Forests.

1. Adaboost combines a lot of stumps (weak learners) to make classifications
In Random Forest, all trees are full sized aka they are allowed to grow to their max depths. In contrast, in adaboost, a tree is just the root node and 2 leaves - such trees are called stumps. Adaboost is a forest of stumps. Individual stumps are not very good at making accurate predictions coz they are able to make use of only 1 variable to make decisions as opposed to max depth DTs that are able to make use of all variables. Stumps are there called weak learners. Adaboost works with a lot of weak learners to make classifications.

2. Some stumps have a larger vote in the classification than others
In a random forest each tree contributes in equal measure towards a decision. In contrast in a forest of stumps made with adaboost, some stumps get to have a higher say towards the final decision than others.

3. Each stump is made by taking the previous stumps mistakes into account
In a random forest each decision tree is made independently of the others, aka. it does not matter if any given DT was made before or after another DT. In contrast in adaboost, stumps are made in a particular order - the errors that the first stump makes influences how the second stump is made, the errors the second stump makes influences how the third stump is made, and so on.


How to make a forest of stumps with adaboost

Lets work on an example using patients' chest pain, blocked arteries and weight data matched to heart disease.
Step 1: First we assign a sample weight to each record. At the start all samples are given a weight of 1/N (number of samples). All samples are equally important.
Step 2: We make the first stump by determining which of the 3 variables best separates the data using the Gini index of the 3 stumps. For numerical data, we figure out which data sample is the best suited to serve as the root using the same technique employed in DTs. Pick the stump with the lowest impurity score to be the first stump in the forest.
Step 3: Now we need to determine how much say this stump will have in the final classification. We do this by determining how well this stump classified the samples. To do this, we record the number of mistakes this stump has made. The total error for a stump is the sum of the weights associated with the samples it incorrectly classified.
Note: Coz all of the sample weights add to 1, the total error for a stump will always be between 0 for a perfect stump and 1 for a really bad stump.
Step 4: Total error relates to the amount of say a stump gets in the final classification in the following way: Amount of say = (1/2)log((1-total\_error)/total\_error). To avoid division by zero or log of 0, a small error term is introduced.
Step 5: Increase the weights of the samples incorrectly classified by the previous stump and then decrease the weights of the other samples to represent the need for the next stump to correctly classify the incorrectly classified samples. We increase the sample weights using the formula: sample weight times e raised to the power of amount of say. This means that when the last stump did a good job classifying samples, the previous sample weight is scaled by a large amount and vice versa.
Step 6: Decrease sample weights of the other samples using the formula: sample weight times e raised to the power of -(amount of say).
Step 7: Normalize the set of new sample weights so that they all add up to 1 by dividing each new  weight by the sum of all the new weights. Now we use the new normalized sample weights to build the next stump.
Step 8: To get the next stump, in theory, we could use the sample weights to calculate weighted gini indexes to determine which variable should split the next stump. The weighted gini index would put more emphasis on correctly classifying the samples incorrectly classified by the last stump since those samples would have a larger sample weight. Alternatively we could use the new sample weights to create a another table of samples, same size as the original, but that contains multiple copies of the samples having the largest sample weights. We can do this by picking a random number between 0 and 1 and then determining where that number falls if we use the sample weights as a distribution. Depending on which bucket the number falls in, we copy the corresponding sample into the new samples table.
Step 9: Repeat the previous steps with this new samples table. Note that since the misclassified samples appear multiple times in the new table, they will be treated as a block and incur a large penalty for being misclassified. This is how the errors the first tree makes influence how the second tree is made, and so on.

How does the forest of stumps made using adaboost make classifications
Run down all the stumps with the data to be classified keeping track of the total amounts of say for stumps that classify the new data into one category versus another. The set of stumps with the largest total amount of say get to determine the classification of the new data.


Comparison with Stacking
Stacking uses the leave one out technique of cross validation for training its models. So we need to know the entire loss function before we can begin to optimize it. This implies we need to know  base models for all of these values i=1..N and m=1..M. These values are stored in a NxM matrix. So the number of models to train is of the order of NxM aka quadratic complexity. Quadratic complexity systems do not scale well.

Adaboost on the other hand is a greedy technique. That said, it does not try to optimize entire ensemble at the same time. It only used the current weights tofind the error and alpha. None of the earlier created stumps are changed. Thus it only needs to train M models and therefor has linear complexity which is far better than quadratic complexity.



