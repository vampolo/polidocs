function [lam1,x1,j]=potenze(A,x0,nmax,toll)
%   POTENZE Calcolo dell'autovalore di modulo massimo e del corrispondente autovettore con il metodo
%   delle potenze
%   [lam1,x1,j]=potenze(A,x0,nmax,toll) calcola l'autovalore di modulo massimo ed il corrispondente
%   autovettore della matrice A con tolleranza toll partendo dal vettore x0 e con un numero di
%   iterazioni inferiore o uguale a nmax.
%   La funzione restituisce lo scalare lam1, approssimazione dell'autovalore di modulo massimo, il
%   vettore x1, approssimazione del corrispondente autovettore e lo scalare j corrispondente al
%   numero di iterazioni del metodo delle potenze effettuate.
%
%   Esempio
%         A=rand(5)+diag([10,4,2,-1,9]);
%         [lam1,x1,j]=potenze(A,ones(5,1),60,1e-4)
%      calcola l'autovalore di modulo massimo ed il corrispondente autovettore della matrice A con
%      tolleranza 1e-4 partendo dal vettore colonna di componenti unitarie.
%  
%   Vedere anche 
%       funzioni per il calcolo degli autovalori ed autovettori:  POTINV
%       localizzazione degli autovalori: GERSCH
%       esempi di calcolo degli autovalori:

%   NOTE: 

%   Maggiori dettagli si possono trovare in M. Frontini, "FONDAMENTI DI CALCOLO NUMERICO", Libreria Clup, 2003 e in
%   M. Frontini e E. Sormani, "FONDAMENTI DI CALCOLO NUMERICO: problemi in laboratorio", APOGEO, 2005

%   Marco Frontini, Eros Sormani

%   Revisione: 4 $  $Data: 22 aprile 2005 10:24 $


[n,m]=size(A);
if n~=m;
   error('La matrice deve essere quadrata.');
end;
% ricerca del massimo (vettore V)
[cmax,ind]=max(abs(x0));
%
x01=x0/x0(ind);
rap=1;
lam0=1;
%
% ciclo
j=0;
while rap>toll;
  j=j+1;
  x1=A*x01;
  lam1=x1(ind);
% ricerca del massimo
   [cmax,ind]=max(abs(x1));
  x1=x1/x1(ind);
  rapx=max(abs(x01-x1));
  raplam=abs(lam0-lam1);
  rap=max([rapx,raplam]);
  x01=x1;
  lam0=lam1;
  if j>nmax;
   rap=0;
   error('Attenzione il metodo non converge o nmax è piccolo per la tolleranza richiesta')
  end;
end