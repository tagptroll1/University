# machinelearningexam

The exam i submited for my machinelearning course, graded A
pip install -r requirements.txt to install dependencies

>Run main.py, 

>write in "Flaveria.csv" to load the training set we were given

>write in "TESTdata.csv"  to load in test data the lecturer used to grade us



<h2> Message posted from lecturer with all scores found during the exam </h2>

Dear all, these are the results of the competition. I have decided that scores above 0.58 will get an A, those between 0.57 and 0.4 will get a B, >0.1 gets a C and the rest D. There are 3 assignments not scored. Two of those got an E and one F.  If you have a look at the second table, you can see the range of score obtained for each method used. If you look at kNN, Ridge and Linear Regression, you will see an amazing span of scores. This is because these methods are very wrong to be used for datasets with categorical variables and the high scores where obtained were the result of brute force adjusting of parameters. These three methods heavily depend on the data being a particular shape and features being real numbers. In contrast you will see that the decision tree based methods give consistent results and high score. These really are the methods that you should reach for when you have categorical features and small number of features. Particularly kNN under categorical features: just don't. The boolean strings you obtain that encode the features completely destroy the similarity between features and this similarity is what you need to be able to use kNN in a meaningful way. I am positively impressed by the ability to "squeeze blood from a stone" in the use of kNN I have seen.

SVM-s can handle small data sets, but the data does need to be of real numbered features and it does to have the "clustered" appearance. 

Special mention to the one person who used a neural network, despite me explicitly saying that this is a bad idea for small data sets. The only reason it worked at all, and it is a minor miracle, is because a very simple mlp is actually a linear regressor.  

I hope you enjoyed the course and the competition. 

The full grades will be released at some point next week (I have entered them in INSPERA)

 
    Score 	Comment 	Grade
    0.8543396241 	Ridge 	A
    0.813871679 	Ridge 	A
    0.6833205123 	BaggingRegressor 	A
    0.6275098303 	AdaBoost 	A
    0.6904478574728292 	RandomForestRegressor 	A
    0.5929462199 	kNN 	A
    0.5885601752 	RandomForestRegressor 	A
    0.5852601378 	Ridge 	A
    0.5851229331 	RandomForestRegressor 	A
    0.5843334911 	RandomForestRegressor 	A
    0.5839970167 	GaussianProcessRegressor 	A
    0.5839969944 	kNN 	A
    0.5839969944 	kNN 	A
    0.5839969944 	kNN 	A
    0.5839969944 	kNN 	A
    0.5839969944 	Radius Neighhbours 	A
    0.5839969944 	kNN 	A
    0.5839969944 	DecisionTreeRegressor 	A
    0.5839969944 	kNN 	A
    0.5839969944 	DecisionTreeRegressor 	A
    0.5839969944 	DecisionTreeRegressor 	A
    0.5811741556 	RandomForestRegressor 	A
    0.5839969944 	kNN 	A
    0.5750696526 	Linear Regression 	B
    0.5750696526 	Linear Regression 	B
    0.5731999167 	Gradient Boosting Regeressor 	B
    0.5700600248 	Ridge 	B
    0.5625999377 	AdaBoost 	B
    0.5609304073 	Linear Regression 	B
    0.551361419 	Ridge 	B
    0.5478815187 	RandomForestRegressor 	B
    0.5405230907 	Ridge 	B
    0.5405230907 	Ridge 	B
    0.5372101133 	Ridge 	B
    0.5372101133 	Ridge 	B
    0.5372101133 	Ridge 	B
    0.5289865389 	LinearRegression 	B
    0.5107201765 	kNN 	B
    0.5087033054 	kNN 	B
    0.4982592763 	TheilSenRegressor 	B
    0.4840072564 	kNN 	B
    0.474369476 	BaggingRegressor 	B
    0.4580658384 	RandomForestRegressor 	B
    0.4525027215 	SVR 	B
    0.4481274903 	MLP 	B
    0.4440129279 	RandomForestRegressor 	B
    0.442426228 	RAndomForestRegressor 	B
    0.44 	Gradient Boosting Regeressor 	B
    0.4382649344 	Ridge 	B
    0.4358085126 	Ridge 	B
    0.4344268275 	RAndomForestRegressor 	B
    0.4293992554 	kNN 	B
    0.429 	kNN 	B
    0.4012657706 	BayesianRidge 	B
    0.3952950633 	BayesianRidge 	B
    0.3342174168 	kNN 	C
    0.3321830311 	BaggingRegressor 	C
    0.302576322 	LinearRegression(treats random state as a paremeter to optymise, that was removed from code when testing) 	C
    0.302576322 	LinearRegression 	C
    0.3019870239 	Ridge 	C
    0.2842467757 	Linear Regression 	C
    0.2808009856 	kNN 	C
    0.280012576 	Linear Regression 	C
    0.280012576 	Linear Regression 	C
    0.280012576 	Linear Regression 	C
    0.280012576 	Linear Regression 	C
    0.280012576 	Linear Regression 	C
    0.280012576 	Linear Regression 	C
    0.280012576 	Linear Regression (could not run, had to debug) 	C
    0.28 	Linear Regression 	C
    0.2799999125 	Ridge 	C
    0.2797573174 	Ridge 	C
    0.2707656339 	Ridge 	C
    0.2656223818 	Ridge 	C
    0.2656223818 	Ridge 	C
    0.2498651326 	ARDRegression 	C
    0.2230900773 	NuSVR 	C
    0.2023185963 	kNN 	C
    0.09220703021 	NuSVR 	D
    -0.06048410008 	SCR 	D
    -0.09243638286 	kNN 	D
    -0.1521436113 	ridge 	D
    -0.6929470638 	LinearRegression (removes too many data points from training it seriously overfits) 	D
    -0.6944199602 	kNN 	D
