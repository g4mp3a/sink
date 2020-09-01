import pandas as pd
import numpy as np


df = pd.read_csv('tweets.csv')
target = df['is_there_an_emotion_directed_at_a_brand_or_product']
text = df['tweet_text']

fixed_text = text[pd.notnull(text)]
fixed_target = target[pd.notnull(text)]

from sklearn.feature_extraction.text import CountVectorizer
count_vect = CountVectorizer()
count_vect.fit(fixed_text)

counts = count_vect.transform(fixed_text)

from sklearn.naive_bayes import MultinomialNB
from sklearn.dummy import DummyClassifier
from sklearn.naive_bayes import GaussianNB
from sklearn.gaussian_process.kernels import RBF
from sklearn.tree import DecisionTreeClassifier


#nb = DummyClassifier(strategy='most_frequent')
from sklearn.gaussian_process import GaussianProcessClassifier
nb = DecisionTreeClassifier(max_depth=5)

#prop_train = 0.7
#n_train = int(np.ceil(fixed_target.shape[0] * prop_train))
#n_test = fixed_target.shape[0] - n_train
#print('training on {} examples ({:.1%})'.format(n_train, prop_train))
#print('testing on {} examples'.format(n_test))

<<<<<<< HEAD
nb.fit(counts[0:6000], fixed_target[0:6000])

#predictions = nb.predict(counts[n_train:])
#print(sum(predictions == fixed_target[n_train:])/n_test)

predictions = nb.predict(counts[6000:9092])
correct_predictions = sum(predictions == target[6000:9092])
incorrect_predictions = (9092 - 6000) - correct_predictions
print('# of correct predictions: ' + str(correct_predictions))
print('# of incorrect predictions: ' + str(incorrect_predictions))
print('Percent correct: ' + str(100.0 * correct_predictions / (correct_predictions + incorrect_predictions)))

print("From cross_val_score")
from sklearn.model_selection import cross_val_score

scores = cross_val_score(nb, counts, fixed_target, cv=10)
print(scores)
print(scores.mean())
=======
predictions = nb.predict(counts[n_train:])
print(sum(predictions == fixed_target[n_train:])/(n_test+0.))
>>>>>>> 98c477bf28b80d359c8ef643239bbaf2502089df
