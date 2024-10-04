# py -m pip install --upgrade pip
# py -m pip install pandas
import numpy as np
import pandas as pd

valors = np.array([1,2,3,4,5])
#serie = pd.Series(valors)
serie = pd.Series(valors, index = ['a', 'b', 'c', 'd', 'e'])
print(serie)