# Candidate nr 31.

import pandas
import numpy
from typing import Tuple

def split(path:str, shape=None) -> Tuple[pandas.DataFrame, pandas.DataFrame]:
    """converts a csv file into pandas dataframes
    converts n_level to 1, 2, or 3
    converts species to dummy values (0,0,0,0,0,1) for instance
    seperates data and labels (n_level & species)/(weights)
    Removes the species column, and Plant weight
    
    Parameters:
    path - Path to a csv file
    shape (Optional) - a list of column names to form current one based on
    
    Returns:
    data - dataframe consisting of n_levels, and 6 columns representing specie type
    weights - a dataframe containing only Plant Weight(g)"""
    
    # Reads data from given csv file
    data = pandas.read_csv(path)

    # Converts L, M, H to 1, 2, 3
    data.iloc[:, 0] = data.iloc[:, 0].apply(lambda n:  {"L": 1, "M": 2, "H": 3}.get(n))
    # Drops column species, uses dataframe returned from drop to concat with dummies created
    # by the species column
    data = pandas.concat([data.drop("species", axis=1),
                          pandas.get_dummies(data["species"])], axis=1)

    # Gets plant weights from index 1
    weights = data.iloc[:,1]
    # Drops the weights of the original dataframe
    data = data.drop("Plant Weight(g)", axis=1)

    # If shape is provided make sure every column in shape is in our data
    if shape:
        for col in shape:
            if col not in data:
                # If it's not in our dataframe add the column with only 0s
                data[col] = numpy.zeros(len(data.index), dtype=numpy.int8)
        # Re arreng column order
        data = data[shape]

    return data, weights




