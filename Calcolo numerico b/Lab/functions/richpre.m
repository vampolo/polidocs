function [xv,iter]=richpre(A,b,x0,nmax,toll,alpha,P)
%   RICHPRE Calcola la soluzione del sistema lineare Ax=b utilizzando il metodo iterativo di
%   Richardson precondizionato.
%   [xv,iter]=richpre(A,b,x0,nmax,toll,alpha,P) calcola la soluzione del sistema lineare Ax=b 
%   con tolleranza toll partendo dal vettore x0 e con un numero di iterazioni inferiore o uguale
%   a nmax.
%   La funzione restituisce il vettore xv, approssimazione della soluzione ed il numero di iterazioni
%   effettuate.
%
%   Esempio
%         A=diag(ones(8,1)*8)+diag(ones(7,1)*2,1)+diag(ones(7,1)*2,-1);
%         b=[10,11,11,11,11,11,11,9]';
%         P=eye(8);
%         [xv,iter]=richpre(A,b,zeros(8,1),40,1e-12,0.125,P)
%      calcola la soluzione del sistema lineare Ax=b con tolleranza 1e-12 partendo dal vettore colonna 
%      di componenti nulle utilizzando il metodo iterativo di Richardson non precondizionato con
%      parametro alpha=0.125
%  
%   Vedere anche 
%       funzioni per risolvere sistemi lineari:  GAUSSEID, JACOB

%   NOTE: 
%       Non viene fatto alcun test sui criteri di convergenza.

%   Maggiori dettagli si possono trovare in M. Frontini, "FONDAMENTI DI CALCOLO NUMERICO", Libreria Clup, 2003 e in
%   M. Frontini e E. Sormani, "FONDAMENTI DI CALCOLO NUMERICO: problemi in laboratorio", APOGEO, 2005

%   Marco Frontini, Eros Sormani

%   Revisione: 4 $  $Data: 22 aprile 2005 10:24 $

[n,m]=size(A);
if n~=m
 error('La matrice deve essere quadrata.') 
end 
xv=x0;
r=b-A*x0;
for iter=0:nmax,
    z=P\r;
    xn = xv+alpha*z;
    r=b-A*xn;    
    err=norm(xn-xv);
    xv=xn;
    if(err<toll),
       break, 
    end
end
if iter==nmax,
   disp('Attenzione il metodo non converge o nmax è piccolo per la tolleranza richiesta')
end
return