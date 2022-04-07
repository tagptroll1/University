# Main script for running script
# Candidate nr 31.

import os
import pandas

import sklearn as skl

import data_splitting
import Model_selector as MD

PRINT_FULL_DATA_SETS = False
PRINT_SAMPLE_DATA_SETS = False

def clear_terminal() -> None:
    """Utility method to clear terminal for windows, and mac"""
    os.system('cls' if os.name == 'nt' else 'clear')

# Clear terminal
clear_terminal()

# Select model from model_selector
# Model is the object, params is a dict of the paramters I set in model_selector
model, params = MD.random_forest().popitem()

# asks for csv files to train/test on
csv_train_path = input("Input training .csv file name or path\n>")
csv_test_path  = input("Input test .csv file name or path\n>")

# Load csv training file and split into X and y dataframes
X_train, y_train = data_splitting.split(csv_train_path)

# Load csv testing file, passes the "shape" of training data to replicate
X_test, y_test = data_splitting.split(csv_test_path, list(X_train.columns.values))

# Set params of model, and fit it
regression_model = model.set_params(**params)
regression_model.fit(X_train, y_train)

# Ask to see datasets
clear_terminal()

print("""Would you like to inspect all data, or data shape?\n)
        \r y - Yes, show me everything
        \r shape - Yes, show me the shape of the data
        \r n - No, continue to scoring""")
show_data_answer = input(">")

if show_data_answer.lower() in ("y", "yes"):
    PRINT_FULL_DATA_SETS = True
elif show_data_answer.lower() == "shape":
    PRINT_SAMPLE_DATA_SETS = True
    
# Clear terminal again before showing data / results
clear_terminal()
# Print all data if selected
if PRINT_FULL_DATA_SETS:
    print(X_train)
    print(X_test)
    print(y_train.to_frame())
    print(y_test.to_frame())

# Print 4 first entries of all dataframes
if PRINT_SAMPLE_DATA_SETS:
    print(X_train.head())
    print(X_test.head())
    print(y_train.to_frame().head())
    print(y_test.to_frame().head())

# Print result
score = regression_model.score(X_test, y_test)
print(f"""
    \r-----------------------
    \r  {model.__class__.__name__}
    \r-----------------------
    \r>Score: {score}
    \r-----------------------
    \r {'That went better than expected' if score > 50 else "I can explain..."}""")

