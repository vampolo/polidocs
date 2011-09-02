# author: Stefano Gualandi, gualandi_at_elet.polimi.it
# Fondamenti di Ricerca Operativa
#
# "Ex-8: Assegnamento di Frequenze" - MODELLO

#---------------------
# Insiemi e parametri
#---------------------
param n >= 0;
set V := 1..n;
set E within {i in V, j in V: i<>j};

set F := 1..n;

#----------------------
# Variabili decisionali
#----------------------
var x {V,F} binary;
var y {F}   binary;

#-------------------
# Funzione obiettivo
#-------------------
minimize NumeroColori: 
	sum {f in F} y[f];

#--------------------------
# Vincoli di interferenza
#--------------------------
s.t. Interferenza {(i,j) in E, f in F}:
	x[i,f] + x[j,f] <= y[f];

#--------------------------
# Vincoli di assegnamento
#--------------------------
s.t. Assegnamento {i in V}:
	sum {f in F} x[i,f] = 1;

