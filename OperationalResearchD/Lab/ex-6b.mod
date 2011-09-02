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

# Auxiliary data set
set FS {i in Nodes} := setof {(i,j) in Arcs} (i,j);
set BS {i in Nodes} := setof {(j,i) in Arcs} (j,i);

param cap {Arcs}  >= 0;          # Arc capacity


#----------------------
# Decision variables
#----------------------
var x {(i,j) in Arcs}, >= 0, <= cap[i,j];   # Flow on each arc

var flow >= 0;                   # overall flow

#-------------------
# Objective function
#-------------------
maximize Flusso: 	
	flow;

#------------------------------
# Flow balance constraints
#------------------------------
subject to balance {i in Nodes diff {s,t}}: 
	sum {(i,j) in FS[i]} x[i,j] - sum {(j,i) in BS[i]} x[j,i] = 0;

subject to flowIn: 
	sum {(s,j) in FS[s]} x[s,j] - sum {(j,s) in BS[s]} x[j,s] = +flow;

subject to flowOut: 
	sum {(t,j) in FS[t]} x[t,j] - sum {(j,t) in BS[t]} x[j,t] = -flow;
