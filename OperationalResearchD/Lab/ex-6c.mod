# author: Stefano Gualandi, gualandi_at_elet.polimi.it
#
# "Ex-6: Networking" - MODELLO


#---------------------
# Set and Parameter
#---------------------
set Nodes;                       # Vertex set
set Arcs within {Nodes, Nodes};  # Arc set

param s symbolic in Nodes;       # Source
param t symbolic in Nodes;       # Sink

set A := Arcs union {(t,s)};

# Auxiliary data set
set FS {i in Nodes} := setof {(i,j) in A} (i,j);
set BS {i in Nodes} := setof {(j,i) in A} (j,i);

param cap {A}  >= 0, default Infinity;          # Arc capacity

#----------------------
# Decision variables
#----------------------
var x {(i,j) in A}, >= 0, <= cap[i,j];   # Flow on each arc

var flow >= 0;                   # overall flow

#-------------------
# Objective function
#-------------------
maximize Flusso: 	
	x[t,s];

#------------------------------
# Flow balance constraints
#------------------------------
subject to balance {i in Nodes}: 
	sum {(i,j) in FS[i]} x[i,j] - sum {(j,i) in BS[i]} x[j,i] = 0;
