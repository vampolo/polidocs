# Exercise 10: Emergency calls
# Refer to the lab slides

#---------------------
# Sets and Parameters
#---------------------

set P;   # Sites
set C;   # Calls

# Number of available ambulances
param k, integer >= 0;

# Time from site i in P to call j in C
param t {P,C}, >=0;


#----------------------
# Decision Variables
#----------------------
var x {P,C}, binary;    # assign calls to ambulances
var y {P},   binary;    # ambulance on site 
var T, >= 0;            # max time

#-------------------
# Objective Function
#-------------------
minimize WaitTime: T;


#-----------------------------------------------------
# Feasibility constraints
#-----------------------------------------------------

# All calls must be served
s.t. Calls {j in C}:
	sum {i in P} x[i,j] = 1;

# The number of allocated ambulances cannot be higher than the available numbers
s.t. Ambulances:
	sum {i in P} y[i] <= k;

# Every call must be served by an existing ambulance (coherence constraints)
s.t. Coherence {i in P, j in C}:
	x[i,j] <= y[i];

# Take max time
s.t. MaxTime {j in C, i in P}:
	T >= t[i,j]*x[i,j];

# VARIANT:
#s.t. Calls {j in C}:
#	sum {i in P} x[i,j] = 2;