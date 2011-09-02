# Exercise 11: Telecommunication Network
# Refer to the lab slides

#---------------------
# Insiemi e parametri
#---------------------
set Nodes;                       # Insiemi di nodi
set Arcs within {Nodes, Nodes};  # Insiemi di archi

param s symbolic in Nodes;       # Sorgente della comunicazione
param t symbolic in Nodes;       # Destinazione della comunicazione

param c1 {Arcs}  >= 0;           # Costo se nominale
param c2 {Arcs}  >= 0;           # Costo se di back-up

#----------------------
# Variabili decisionali
#----------------------

# Quantita` di flusso sull'arco
var x {(i,j) in Arcs}, binary;   

var y {(i,j) in Arcs}, binary;   


#-------------------
# Funzione obiettivo
#-------------------
minimize Cost: 	
	sum {(i,j) in Arcs} (c1[i,j]*x[i,j]+c2[i,j]*y[i,j]);


#------------------------------
# Vincoli di bilancio sui nodi
#------------------------------
subject to NominalPath {i in Nodes: i<>s && i<>t }: 
	sum {j in Nodes : (i,j) in Arcs} x[i,j] = sum {j in Nodes : (j,i) in Arcs} x[j,i] ;

subject to NominalSource: 
	sum {(s,j) in Arcs} x[s,j] = 1;

subject to NominalTarget: 
	sum {(j,t) in Arcs} x[j,t] = 1;

subject to BackupPath {i in Nodes: i<>s && i<>t }: 
	sum {j in Nodes : (i,j) in Arcs} y[i,j] = sum {j in Nodes : (j,i) in Arcs} y[j,i] ;

subject to BackupSource: 
	sum {(s,j) in Arcs} y[s,j] = 1;

subject to BackupTarget: 
	sum {(j,t) in Arcs} y[j,t] = 1;

# Disjoint paths
s.t. Disjoint {(i,j) in Arcs}:
	x[i,j]+y[i,j] <= 1;
