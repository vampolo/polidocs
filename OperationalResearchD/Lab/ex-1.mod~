# LAB SESSION 1. - EXAMPLE A. 

set I;           # set of columns 
set J;           # set of rows

param A{J,I};    # matrix A
param b{J};      # coefficients
param c{I};      # cost vector

var x{I}, >= 0, integer;  # positive continuous variables (vector)

# Objective function
minimize Cost: 
	sum {i in I} c[i]*x[i]; 

# Constraint definition
subject to MatrixConstraint {j in J}: 
	sum {i in I} A[j,i]*x[i] <= b[j];