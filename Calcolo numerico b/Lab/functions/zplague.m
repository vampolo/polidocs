function [z,p] = zplague(n)
%   ZPLAGUE calcolo dei nodi e dei pesi della quadratura di Gauss-Laguerre
%   [z,p] = zplague(n) calcola i nodi z e i pesi p della quadratura di Gauss-Laguerre su n nodi per il
%   calcolo dell'integrale di w(x)*f(x) nell'intervallo [0,inf] con w(x)=exp(-x).
%
%   Esempio
%         [z,p]=zplague(5)
%      calcola i nodi e i pesi della formula di quadratura di Gauss-Laguerre su 5 nodi
%  
%   Vedere anche 
%       funzioni per il calcolo di integrali: QUADT, QUADM, QUADS
%       funzioni per il calcolo di pesi e nodi di formule gaussiane classiche: ZPLEGE, ZPCHEBY

%   NOTE:
%      matrice di Jacobi con alfa(k)=2k+1 e beta(k)=k^2 per k>0

%   NOTE:
%      I nodi sono gli autovalori della matrice di Jacobi Jac mentre i pesi sono legati alla prima componente
%      degli autovettori normalizzati (V1(k))della matrice Jac secondo la relazione: p(k)=beta(0)*V1(k)^2

%   Maggiori dettagli si possono trovare in M. Frontini, "FONDAMENTI DI CALCOLO NUMERICO", Libreria Clup, 2003 e in
%   M. Frontini e E. Sormani, "FONDAMENTI DI CALCOLO NUMERICO: problemi in laboratorio", APOGEO, 2005

%   Marco Frontini, Eros Sormani

%   Revisione: 4 $  $Data: 22 aprile 2005 10:24 $

if n<=1
  z=1;p=1;
  return
end
jac=zeros(n);
k=[1:n];
v=sqrt(k.^2);
jac=jac+diag(v(1:n-1),1)+diag(v(1:n-1),-1)+diag(2*(k-1)+1);
[p,z]=eig(jac);
norm2=sqrt(diag(p'*p));    % normalizzazione per pesi
p=(1*p(1,:)'.^2)./norm2;   % 1=beta0; ' per aver p colonna
z=diag(z);		   % z colonna
