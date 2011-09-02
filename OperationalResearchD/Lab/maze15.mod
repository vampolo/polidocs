param n, integer >=0;

set Rows := {n};
set Colomns := {n};
set ArcsRow within {Rows, Rows};
set ArcsColomns within {Colomns, Colomns};
param c {Rows, Colomns};
param s {Rows, Colomns};

var x {Rows, Colomns}, binary; 

maximize EveryCellAccessed {i in Rows} :
	sum {j in Colomns} x[i,j];


#incomplete
#moves are allowe if:
#[(row = row) OR (column = column)] AND [(shape = shape) OR (color = color)]