function [autoval,x1,kk]=potinv(A,x0,alfa,nmax,toll)
%   POTINV Calcolo dell'autovalore di modulo minimo e del corrispondente autovettore con il metodo
%   delle potenze inverse con shift.
%   [autoval,x1,kk]=potinv(A,x0,0,nmax,toll) calcola l'autovalore di modulo minimo ed il
%   corrispondente autovettore della matrice A con tolleranza toll partendo dal vettore x0 con un
%   numero di iterazioni inferiore o uguale a nmax.
%   La funzione restituisce lo scalare autoval, approssimazione dell'autovalore di modulo minimo,
%   il vettore x1, approssimazione del corrispondente autovettore e lo scalare kk corrispondente al
%   numero di iterazioni del metodo delle potenze inverse effettuate.
%
%   [autoval,x1,kk]=potinv(A,x0,alfa,nmax,toll) calcola l'autovalore di A più prossimo ad alfa con
%   tolleranza toll partendo dal vettore x0 e con un numero di iterazioni inferiore o uguale a nmax.
%   La funzione restituisce lo scalare autoval, approssimazione dell'autovalore della matrice A più
%   prossimo ad alfa, il vettore x1, approssimazione del corrispondente autovettore e lo scalare kk
%   corrispondente al numero di iterazioni del metodo delle potenze inverse con shift alfa effettuate.
%
%   Esempio
%         A=rand(5)+diag([10,4,2,-1,9]);
%         [autoval,x1,kk]=potinv(A,ones(5,1),0,20,1e-4)
%      calcola l'autovalore di modulo minimo ed il corrispondente autovettore della matrice A con
%      tolleranza 1e-4 partendo dal vettore colonna di componenti unitarie.
%
%   Esempio
%         A=rand(5)+diag([10,4,2,-1,9]);
%         [autoval,x1,kk]=potinv(A,ones(5,1),2,20,1e-4)
%      calcola l'autovalore più prossimo a 2 ed il corrispondente autovettore della matrice A con
%      tolleranza 1e-4 partendo dal vettore colonna di componenti unitarie.
%
%   Vedere anche 
%       funzioni per il calcolo degli autovalori ed autovettori:  POTENZE
%       localizzazione degli autovalori: GERSCH
%       esempi di calcolo degli autovalori:
%
%   NOTE: risoluzione dei sistemi lineari ottenuta utilizzando la decomposizione PA=LU

%   Maggiori dettagli si possono trovare in M. Frontini, "FONDAMENTI DI CALCOLO NUMERICO", Libreria Clup, 2003 e in
%   M. Frontini e E. Sormani, "FONDAMENTI DI CALCOLO NUMERICO: problemi in laboratorio", APOGEO, 2005

%   Marco Frontini, Eros Sormani

%   Revisione: 4 $  $Data: 22 aprile 2005 10:24 $

[n,m]=size(A);
if n~=m;
   error('La matrice non è quadrata')
end;
n;nm1=n-1;np1=n+1;
%
% ricerca del massimo
%
[cmax,imax]=max(abs(x0));
%
x0=x0/x0(imax);
rap=1;
lam0=1;
%
% ciclo
%
kk=0;
A=A-alfa*eye(size(A));
[l,u,p]=lu(A);

while rap>toll
  pb=p*x0;
  kk=kk+1;
  y=l\pb;
  x1=u\y;
  lam1=x1(imax);
% ricerca del massimo...
  [cmax,imax]=max(abs(x1));
  x1=x1/x1(imax);
  rap=max(abs(x0-x1));
  x0=x1;
  lam0=lam1;
  
  if kk>nmax;
    rap=0;
    autoval=1/lam1+alfa;
    error('Attenzione il metodo non converge o nmax è piccolo per la tolleranza richiesta')
    return;
  end;
end
kk=kk; ;autoval=1/lam1+alfa;x1;