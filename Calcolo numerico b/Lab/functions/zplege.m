function [z,p] = zplege(n)
%   ZPLEGE calcolo dei nodi e dei pesi della quadratura di Gauss-Legendre
%   [z,p] = zplege(n) calcola i nodi z e i pesi p della quadratura di Gauss-Legendre su n nodi per il
%   calcolo dell'integrale di w(x)*f(x) nell'intervallo [-1,1] con w(x)=1.
%
%   Esempio
%         [z,p]=zplege(5)
%      calcola i nodi e i pesi della formula di quadratura di Gauss-Legendre su 5 nodi
%  
%   Vedere anche 
%       funzioni per il calcolo di integrali: QUADT, QUADM, QUADS
%       funzioni per il calcolo di pesi e nodi di formule gaussiane classiche: ZPLAGUE, ZPCHEBY

%   NOTE:
%      matrice di Jacobi con alfa(k)=0, beta(0)=2, beta(k)=1/(4-k^(-2)) per k>0

%   NOTE:
%      I nodi sono gli autovalori della matrice di Jacobi Jac mentre i pesi sono legati alla prima componente
%      degli autovettori normalizzati (V1(k))della matrice Jac secondo la relazione: p(k)=beta(0)*V1(k)^2

%   Maggiori dettagli si possono trovare in M. Frontini, "FONDAMENTI DI CALCOLO NUMERICO", Libreria Clup, 2003 e in
%   M. Frontini e E. Sormani, "FONDAMENTI DI CALCOLO NUMERICO: problemi in laboratorio", APOGEO, 2005

%   Marco Frontini, Eros Sormani

%   Revisione: 4 $  $Data: 22 aprile 2005 10:24 $

if n<=1
  z=0;p=2;
  return
end
jac=zeros(n);
k=[1:n-1];
v=(k)./(sqrt(4*(k.^2)-1));
jac=jac+diag(v,1)+diag(v,-1);
[p,z]=eig(jac); %restituisce autovettori normalizzati
p=2*p(1,:)'.^2;  % beta0=2;
z=diag(z);		
