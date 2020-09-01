# -*- coding: utf-8 -*-
"""
This sample is used to predict a website visitor's propensity to buy a product
"""

import pandas as pd
import sys
import os
import datetime
import subprocess
from sklearn.model_selection import train_test_split
from sklearn.externals import joblib

#download datafile from GS to local store
data_file="gs://course-prep/source-data/web-browsing-data.csv"

subprocess.check_call(['gsutil', 'cp', \
                       data_file, "web-browsing-data.csv" ], \
                    stderr=sys.stdout)

#Load the web browsing data file and check its contents
browsing_data = pd.read_csv("web-browsing-data.csv")
print("Data Structure Loaded :\n----------------------------------")
print(browsing_data.dtypes)
print("Data Loaded :\n----------------------------------")
print(browsing_data.head())

#Choose a subset of columns as predictors and the BUY as a target
predictors = browsing_data[['REVIEWS','BRO_TOGETHER','COMPARE_SIMILAR','WARRANTY','SPONSORED_LINKS']]
targets = browsing_data.BUY

#Split into training and testing datasets
pred_train, pred_test, tar_train, tar_test \
     = train_test_split(predictors, targets, test_size=.3)
    
print( "Predictor - Training : ", pred_train.shape, \
      "Predictor - Testing : ", pred_test.shape )

from sklearn.naive_bayes import GaussianNB

#Build a classifer to fit the data
classifier=GaussianNB()
classifier=classifier.fit(pred_train,tar_train)

#Run predictions on the test dataset
predictions=classifier.predict(pred_test)

#Analyze accuracy of predictions
from sklearn.metrics import confusion_matrix
from sklearn.metrics import accuracy_score
print("Confusion Matrix : \n-----------------------------")
print(confusion_matrix(tar_test,predictions))
print("Accuracy : \n----------------")
print(accuracy_score(tar_test,predictions))

#Export Classifier to a file. Filename should be model.joblib.
model = "model.joblib"
joblib.dump(classifier,model)

#Upload model file to Cloud Storage
model_path = os.path.join('gs://', "course-prep", datetime.datetime.now().strftime(
    'propensity_%Y%m%d_%H%M%S'), model)
subprocess.check_call(['gsutil', 'cp', model, model_path], stderr=sys.stdout)


