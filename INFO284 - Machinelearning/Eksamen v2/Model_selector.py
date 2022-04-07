# ==========================================================================================
# This file is purely for convenience, the model selected in main.py is the final answer for
# the assignment!
# ==========================================================================================
# Candidate nr 31.

# I use this --
from sklearn.ensemble import RandomForestRegressor

# Not used
from sklearn.tree import DecisionTreeRegressor
from sklearn.neighbors import KNeighborsRegressor
from sklearn.linear_model import LinearRegression
from sklearn.linear_model import HuberRegressor
from sklearn.linear_model import Ridge
from sklearn.svm import SVR


def random_forest() -> RandomForestRegressor:
    return {
        RandomForestRegressor():{
            "n_estimators": 10, 
            "criterion": "mse", 
            "max_depth": None, 
            "min_samples_split": 2, 
            "min_samples_leaf": 1, 
            "min_weight_fraction_leaf": 0.0, 
            "max_features": "auto", 
            "max_leaf_nodes": 6, 
            "min_impurity_decrease": 0.0, 
            "min_impurity_split": None, 
            "bootstrap": True, 
            "oob_score": False, 
            "n_jobs": 1, 
            "random_state": None, 
            "verbose": 0, 
            "warm_start": False
        }
    }

#==============
#|| NOT USED ||
#==============

def knn() -> KNeighborsRegressor:
    return {
        KNeighborsRegressor(): {
            "n_neighbors": 2,
            "weights": "distance",
            "algorithm": "brute",
            "leaf_size": 20,
            "p": 1,
            "metric": "minkowski",
            "metric_params": None,
            "n_jobs": 1
        }
    }

def ridge() -> Ridge:
    return {
        Ridge(): {
            "alpha": 2,
            "fit_intercept": True,
            "normalize": False,
            "copy_X": True,
            "max_iter": None,
            "tol": 0.001,
            # ["auto", "svd", "cholesky", "lsqr", "sparse_cg", "sag", "saga"]
            "solver": "auto",
            "random_state": None
        }
    }

def decisiontree() -> DecisionTreeRegressor:
    return {
        DecisionTreeRegressor():{
            "criterion": "mse",
            "splitter": "best",
            "max_depth": None,
            "min_samples_split": 2,
            "min_samples_leaf": 1,
            "min_weight_fraction_leaf": 0.0,
            "max_features": None,
            "random_state": None,
            "max_leaf_nodes": None,
            "min_impurity_decrease": 0.0,
            "min_impurity_split": None,
            "presort": False
        }
    }

def linear() -> LinearRegression:
    return {
        LinearRegression(): {
            'copy_X': True, 
            'fit_intercept': True, 
            'n_jobs': 1, 
            'normalize': False}
    }

def svr() -> SVR:
    return {
        SVR(): {
            "C": 1.0,
            "cache_size": 200,
            "coef0": 0.01,
            "degree": 3,
            "epsilon": 0.1,
            "gamma": "auto",
            "kernel": "rbf",
            "max_iter": -1,
            "shrinking": True,
            "tol": 0.001,
            "verbose": False
        }
    }

def huber() -> HuberRegressor:
    return {
        HuberRegressor(): {
            'alpha': 0.0001,
            'epsilon': 3,
            'fit_intercept': True,
            'max_iter': 100,
            'tol': 1e-05,
            'warm_start': False
        }
    }
