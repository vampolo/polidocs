# LAB SESSION 1. - EXAMPLE B.

# Declaration of the set of pedixes

set Types;       # set of model
set Components;  # set of components

# Declaration of data

param Score        {Types};              # score of each model
param Availability {Components};         # available quantity of each components
param Necessary    {Types, Components};  # neccessary quantity of every component for each model

# Variable declaration

# to maximise the sum of the scores
var x {Types} >= 0, integer;

# objective function
maximize Gain:
	 sum{i in Types} Score[i]*x[i];   

# constraint the availability of every components
subject to ComponentConstraint {j in Components}:
	sum{i in Types} Necessary[i,j]*x[i] <= Availability[j];

