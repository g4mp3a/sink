# DONT SAVE

import pandas as pd
import numpy as np


df = pd.read_csv('tweets.csv')
target = df['is_there_an_emotion_directed_at_a_brand_or_product']
text = df['tweet_text']

fixed_text = text[pd.notnull(text)]
fixed_target = target[pd.notnull(text)]

from sklearn.feature_extraction.text import CountVectorizer
count_vect = CountVectorizer(lowercase=False)
count_vect.fit(fixed_text)

counts = count_vect.transform(fixed_text)

<<<<<<< HEAD
#from sklearn.naive_bayes import MultinomialNB
#nb = MultinomialNB()
=======
from sklearn.naive_bayes import MultinomialNB
nb = MultinomialNB()
#from sklearn.dummy import DummyClassifier
#nb = DummyClassifier(strategy='stratified')
#from sklearn.ensemble import GradientBoostingClassifier
#nb = GradientBoostingClassifier()
>>>>>>> 98c477bf28b80d359c8ef643239bbaf2502089df

# Train with this data with a dummy classifier:
from sklearn.neural_network import MLPClassifier
nb = MLPClassifier(alpha=1)

nb.fit(counts[0:6000], target[0:6000])

predictions = nb.predict(counts[6000:9092])
correct_predictions = sum(predictions == target[6000:9092])
incorrect_predictions = (9092 - 6000) - correct_predictions
print('# of correct predictions: ' + str(correct_predictions))
print('# of incorrect predictions: ' + str(incorrect_predictions))
print('Percent correct: ' + str(100.0 * correct_predictions / (correct_predictions + incorrect_predictions)))

print("From cross_val_score")
from sklearn.model_selection import cross_val_score

scores = cross_val_score(nb, counts, fixed_target, cv=20)
print(scores)
print(scores.mean())
