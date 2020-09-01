import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
from sklearn.preprocessing import LabelEncoder, StandardScaler
from sklearn.ensemble import RandomForestRegressor
from sklearn.linear_model import LinearRegression
from sklearn.tree import DecisionTreeRegressor
from sklearn.model_selection import cross_val_score


# Will normalize all numerical columns to have 0 mean and unit variance
NUMERICAL_COLS = [
  'crim',
  'zn',
  'nonretail',
  'nox',
  'rooms',
  'age',
  'dis',
  'rad',
  'tax',
  'ptratio',
  'b',
  'lstat',
]

NO_TRANSFORM = ['river'] # Binary column encoded as 0/1 hence no need to transform

class DataTransformer:
    """
    Transforms a dataframe to a numerical matrix, normalizing the column values.
    It uses the scale values found during training to transform the test set.
    So call fit only once; call transform for all subsequent data.
    """
    def fit(self, df):
        self.scalers = {}
        for col in NUMERICAL_COLS:
            scaler = StandardScaler()
            scaler.fit(df[col].values.reshape(-1,1))
            self.scalers[col] = scaler

    def transform(self, df):
        N, _ = df.shape
        D = len(NUMERICAL_COLS) + len(NO_TRANSFORM)
        X = np.zeros((N,D))
        i = 0
        for col, scaler in self.scalers.items():
            X[:,i] = scaler.transform(df[col].values.reshape(-1,1)).flatten()
            i += 1
        for col in NO_TRANSFORM:
            X[:,i] = df[col]
        return X

    def fit_transform(self, df):
        self.fit(df)
        return self.transform(df)

def get_data():
    df = pd.read_csv("data_files/housing.data", header=None, delim_whitespace=True)
    df.columns = ['crim','zn','nonretail','river','nox','rooms','age','dis','rad',
                  'tax','ptratio','b','lstat','medv',]

    # Shuffle data
    N = len(df)
    # Split data into 70% train and 30% test
    train_idx = np.random.choice(N, size=int(0.7*N), replace=False)
    test_idx = [i for i in range(N) if i not in train_idx]
    df_train = df.loc[train_idx]
    df_test = df.loc[test_idx]

    # Normalize the data [(val - mean(val))/variance]
    transformer = DataTransformer()
    Xtrain = transformer.fit_transform(df_train)
    # df['medv'] is the median house price - what we want to predict
    # Since it can have a large range, we use the log of this value
    # This works coz we care about how correct the prediction is relative to the real value
    # ie. a prediction of 5000 for a house worth 10000 is very incorrect,
    # but a prediction off by 5000 for a million dollar house is fine
    Ytrain = np.log(df_train['medv'].values)
    Xtest = transformer.transform(df_test)
    Ytest = np.log(df_test['medv'].values)

    return Xtrain, Ytrain, Xtest, Ytest

if __name__ == '__main__':
    Xtrain, Ytrain, Xtest, Ytest = get_data()

    # Tried n_estimators in (10,20,50,150,200,500)
    model = RandomForestRegressor(n_estimators=100)
    model.fit(Xtrain, Ytrain)
    predictions = model.predict(Xtest)

    # Plot predictions versus test data
    # Scatter plots
    plt.scatter(Ytest, predictions)
    plt.xlabel("Test Values")
    plt.ylabel("Predicted Values")
    # Predicted values should be along the y=x line if they are accurate
    # So plot y=x line
    ymin = np.round(min(min(Ytest), min(predictions)))
    ymax = np.ceil(max(max(Ytest), max(predictions)))
    print("ymin: ", ymin, "ymax: ", ymax)
    r = range(int(ymin), int(ymax)+1)
    plt.plot(r,r)
    plt.show()

    plt.plot(Ytest, label='Test Values')
    plt.plot(predictions, label='Predicted Values')
    plt.legend()
    plt.show()

    # Comparing with Linear Regression and Single DT
    baseline = LinearRegression()
    single_DT = DecisionTreeRegressor()
    print("Cross Validation scores for single DT: ", cross_val_score(single_DT, Xtrain, Ytrain, cv=8).mean())
    print("Cross Validation scores for Lin-Reg (baseline): ", cross_val_score(baseline, Xtrain, Ytrain, cv=8).mean())
    print("Cross Validation scores for RF: ", cross_val_score(model, Xtrain, Ytrain, cv=8).mean())

    single_DT.fit(Xtrain, Ytrain)
    baseline.fit(Xtrain, Ytrain)
    print("Test score for single DT: ", single_DT.score(Xtest, Ytest))
    print("Test score for Lin-Reg (baseline): ", baseline.score(Xtest, Ytest))
    print("Test score for RF: ", model.score(Xtest, Ytest))
