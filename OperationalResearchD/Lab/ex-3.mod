# LAB SESSION 1. - EXAMPLE B.

# Declaration of the set of pedixes

set M;       # set of materials

# Declaration of data

param p {M} #gain of material i
param w {M} #weight of material i
param s {m} #volume of material i

#Constants

param S;
param W;

#declaration of x
var x{M}>=0, binary;

maximize Profit:
  sum{i in M} p[i]*x[i];

subject to weight:
  sum{i in M} w[i]*x[i]<= W;

subject to weight:
  sum{i in M} w[i]*x[i]<= S;

----------------------------------
# Variable declaration

# to maximise the sum of the scores
var x {Types} >= 0, integer;

# objective function
maximize Gain:
	 sum{i in Types} Score[i]*x[i];   

# constraint the availability of every components
subject to ComponentConstraint {j in Components}:
	sum{i in Types} Necessary[i,j]*x[i] <= Availability[j];

