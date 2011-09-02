# author: Stefano Gualandi, gualandi_at_elet.polimi.it
# Fondamenti di Ricerca Operativa
#
# "Ex-5" - MODELLO


#---------------------
# Insiemi e parametri
#---------------------
param n >= 1;                    # Numero di scarpe
param m >= 1;                    # Numero di operai

set M := 1..n;                   # Insiemi di modelli di scarpe
set O := 1..m;                   # Insiemi di operai

param p {O,M};                   # Tempo di operaio i per modello j
param q {O};                     # Tempo massimo per operaio i
param g {M};                     # Prezzo di vendita modello j
param r {M};                     # Produzione massima modello j


#----------------------
# Variabili decisionali
#----------------------
var x {O,M}, >= 0, integer;       # Quantita` di scarpe modello j prodotte da operaio i


#-------------------
# Funzione obiettivo
#-------------------
maximize Flusso: 	
	sum {j in M, i in O} g[j]*x[i,j];


#---------
# Vincoli
#---------

subject to TempoLavorativo {i in O}:
	sum {j in M} p[i,j]*x[i,j] <= q[i];

subject to ProduzioneMax {j in M}:
	sum {i in O} x[i,j] <= r[j];

