# Input Graph
param n >= 0;
set V := 1..n;
set E within V cross V;

# Edge cost
param c {E} >= 0;

# Required nodes
set T within V;

# Given the set of vertices V of n nodes, there exists 2^n-1 subsets of V
set W := 0..(2**n - 1);
# To obtain every possible subset of V you can write the following
# (REMARK: TRY TO DISPLAY THE VALUE OF P!)
set P {k in W} := {i in V: (k div 2**(i-1) mod 2) = 1};

# Cutset of S: set of edges leaving the cut set S (note that S is a subset of V)
set CutSet{S in W} := {(i,j) in E: (i in P[S] && j not in P[S])} union {(i,j) in E: (i not in P[S] && j in P[S])};

# Connectivity between required nodes
param r {(i,j) in E} := if (i in T) && (j in T) then 1 else 0;

# If the cut must be covered
param f {S in W} := if exists {(i,j) in CutSet[S]} (r[i,j] = 1) then 1 else 0;

#--------------------------------------------------
# THIS DATA ARE ONLY TO PRODUCE A GRAPHICAL OUTPUT
# Coordinate of the vertices
param x{i in V} default 10*i;
param y{i in V} default 10*i;

param x0 := (min{i in V} x[i]) - 10.0;
param y0 := (min{i in V} y[i]) - 10.0;
param x1 := (max{i in V} x[i]) + 10.0;
param y1 := (max{i in V} y[i]) + 10.0;

# Name of the output file
param file symbolic default "steiner.eps";
#--------------------------------------------------

# MODEL
var z {E} binary;

minimize cost:
	sum {(i,j) in E} c[i,j]*z[i,j];

s.t. cut {S in W: card(CutSet[S]) > 0}:
	sum {(i,j) in CutSet[S]} z[i,j] >= f[S];

