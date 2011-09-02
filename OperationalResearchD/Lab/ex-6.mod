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
subject to balance {i in Nodes: i<>s && i<>t }: 
	sum {j in Nodes : (i,j) in Arcs} x[i,j] 
	- sum {j in Nodes : (j,i) in Arcs} x[j,i] 
	= 0;

subject to flowIn: 
	sum {(s,j) in Arcs} x[s,j] 
	- sum {(j,s) in Arcs} x[j,s] 
	= flow;

subject to flowOut: 
	sum {(t,j) in Arcs} x[t,j] 
	- sum {(j,t) in Arcs} x[j,t] 
	= -flow;
