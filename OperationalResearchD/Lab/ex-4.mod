# author: Stefano Gualandi, gualandi_at_elet.polimi.it
# Fondamenti di Ricerca Operativa
#
# "Ex-4: Oil Refinery" - MODELLO
# (vedi slides usate a lezione)


#---------------------
# Insiemi e parametri
#---------------------

set I;                # insieme di olii
set J;                # insieme di benzine

param c{I}, >= 0;     # costo unitario di ogni olio
param d{I}, >= 0;     # disponibilita` di ogni olio

param r{J}, >= 0;     # prezzo di vendita delle benzine

param q_min{I,J}, default 0;    # quantita` minima percentuale per tipo di olio per benzina 
param q_max{I,J}, default 1.0;  # quantita` massima percentuale per tipo di olio per benzina 


#----------------------
# Variabili decisionali
#----------------------
var x{I, J}, >= 0;     # matrice di quantita` di olii in ciascuna benzina
                       # x[i,j] indica la quantita` di olio <i> nella
                       # benzina <j>

var y{J}, >= 0;        # quantita` prodotta di ciascuna benzina


#-------------------
# Funzione obiettivo
#-------------------
maximize Profitto:
	sum {j in J} r[j]*y[j] -            # ricavi meno...
	sum {i in I, j in J} c[i]*x[i,j];   # ... costi

#-----------------------------------------------------
# Vincoli che definiscono la regione di ammissibilita`
#-----------------------------------------------------

# Vincolo di risorsa sulla disponibilita` di ciascun olio
subject to Risorsa {i in I}:
	sum{j in J} x[i,j] <= d[i];

# Quantita` prodotta di ciascun tipo di benzina
subject to BenzinaProdotta {j in J}:
	y[j] = sum {i in I} x[i,j];

# Vincoli di miscelazione olio/benzina
subject to QuantitaMax {i in I, j in J}:
	x[i,j] <= q_max[i,j]*y[j];

subject to QuantitaMin {i in I, j in J}:
	x[i,j] >= q_min[i,j]*y[j];