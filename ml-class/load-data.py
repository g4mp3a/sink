import pandas as pd
import numpy as np

df = pd.read_csv('tweets.csv')
target = df['is_there_an_emotion_directed_at_a_brand_or_product']
text = df['tweet_text']

#print(target[9])
#print(text[9])
#print(text[target=="I can't tell"].count)
#print(text.shape[0])
#print(len(text))
