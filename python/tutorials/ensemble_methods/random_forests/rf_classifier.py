import warnings
import numpy as np
import pandas as pd
from sklearn.preprocessing import LabelEncoder, StandardScaler
from sklearn.tree import DecisionTreeClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.linear_model import LogisticRegression
from sklearn.model_selection import cross_val_score
warnings.simplefilter(action='ignore', category=FutureWarning)

# No numerical columns in the data-set
NUMERICAL_COLS = ()
# No headers and so...
CATEGORICAL_COLS = np.arange(22) + 1 # 1..22 inclusive

class DataTransformer:
    """
    Transforms a dataframe to a numerical matrix.
    Normalizes numerical columns. One-Hot encodes categorical columns.
    It uses the scale values found during training to transform the test set.
    So call fit only once; call transform for all subsequent data.
    """
    def fit(self, df):
        self.scalers = {}
        self.encoders = {}
        for col in NUMERICAL_COLS:
            scaler = StandardScaler()
            scaler.fit(df[col].values.reshape(-1,1))
            self.scalers[col] = scaler
        for col in CATEGORICAL_COLS:
            encoder = LabelEncoder()
            # Even tho training set may not have a 'missing' value, test set may
            values = df[col].tolist()
            values.append("missing")
            encoder.fit(values)
            self.encoders[col] = encoder
        # Get dimensions
        self.D = len(NUMERICAL_COLS)
        for col, encoder in self.encoders.items():
            self.D += len(encoder.classes_)
        print("dimensions: ", self.D)

    def transform(self, df):
        N, _ = df.shape
        X = np.zeros((N, self.D))
        i = 0
        for col, scaler in self.scalers.items():
            X[:,i] = scaler.transform(df[col].values.reshape(-1,1)).flatten()
            i += 1
        for col, encoder in self.encoders.items():
            K = len(encoder.classes_)
            X[np.arange(N), encoder.transform(df[col]) + i] = 1
            i += K
        return X

    def fit_transform(self, df):
        self.fit(df)
        return self.transform(df)

def replace_missing(df):
    # Missing numerical values are replaced by column median
    for col in NUMERICAL_COLS:
        if np.any(df[col].isnull()):
            med = np.median(df[col][df[col].notnull()])
            df.loc[df[col].isnull(), col] = med
    # Set missing category to 'missing'
    for col in CATEGORICAL_COLS:
        if np.any(df[col].isnull()):
            df.loc[df[col].isnull(), col] = 'missing'

def get_data():
    df = pd.read_csv("data_files/agaricus-lepiota.data", header=None)

    # Convert label column: e/p to 0/1
    # e = edible = 0, p = poisonous = 1
    df[0] = df.apply(lambda row: 0 if row[0] == 'e' else 1, axis=1)

    # Check for missing data
    replace_missing(df)

    # Shuffle data
    N = len(df)
    # Split data into 70% train and 30% test
    train_idx = np.random.choice(N, size=int(0.7*N), replace=False)
    test_idx = [i for i in range(N) if i not in train_idx]
    df_train = df.loc[train_idx]
    df_test = df.loc[test_idx]

    # Normalize the data [(val - mean(val))/variance]
    # transformer = DataTransformer()
    # Xtrain = transformer.fit_transform(df_train)
    # Ytrain = df_train[0].values
    # Xtest = transformer.transform(df_test)
    # Ytest = df_test[0].values

    # Transform data
    transformer = DataTransformer()
    X = transformer.fit_transform(df)
    Y = df[0].values

    #return Xtrain, Ytrain, Xtest, Ytest
    return X, Y

if __name__ == '__main__':
    #Xtrain, Ytrain, Xtest, Ytest = get_data()
    X, Y = get_data()

    # Baseline
    baseline = LogisticRegression()
    # print("Cross Validation score for Log-Reg: ", cross_val_score(baseline, Xtrain, Ytrain, cv=8).mean())
    print("Cross Validation score for Log-Reg: ", cross_val_score(baseline, X, Y, cv=8).mean())
    # Single DT
    single_DT = DecisionTreeClassifier()
    # print("Cross Validation score for 1 DT: ", cross_val_score(single_DT, Xtrain, Ytrain, cv=8).mean())
    print("Cross Validation score for 1 DT: ", cross_val_score(single_DT, X, Y, cv=8).mean())

    # RF
    # Tried n_estimators in (10, 20, 30, 40, 50, 100, 200).
    # Not much difference between single DT and RF for this data set
    model = RandomForestClassifier(n_estimators=10)
    # print("Cross Validation score for RF: ", cross_val_score(model, Xtrain, Ytrain, cv=8).mean())
    print("Cross Validation score for RF: ", cross_val_score(model, X, Y, cv=8).mean())

    # single_DT.fit(Xtrain, Ytrain)
    # baseline.fit(Xtrain, Ytrain)
    # model.fit(Xtrain, Ytrain)
    # print("Test score for single DT: ", single_DT.score(Xtest, Ytest))
    # print("Test score for Lin-Reg (baseline): ", baseline.score(Xtest, Ytest))
    # print("Test score for RF: ", model.score(Xtest, Ytest))

    # predictions = model.predict(Xtest)
    # [print(x-y) for x,y in zip(predictions, Ytest)]


