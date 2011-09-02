function [z,p] = zpcheby(n)
%   ZPCHEBY calcolo dei nodi e dei pesi della quadratura di Gauss-Chebyshev.
%   [z,p] = zpcheby(n) calcola i nodi z e i pesi p della quadratura di Gauss-Chebyshev su n nodi per il
%   calcolo dell'integrale di w(x)*f(x) nell'intervallo [-1,1] con w(x)=(1-x^2)(-1/2).
%
%   Esempio
%         [z,p]=zpcheby(5)
%      calcola i nodi e i pesi della formula di quadratura di Gauss-Chebyshev su 5 nodi
%  
%   Vedere anche 
%       funzioni per il calcolo di integrali: QUADT, QUADM, QUADS
%       funzioni per il calcolo di pesi e nodi di formule gaussiane classiche: ZPLEGE, ZPLAGUE

%   NOTE:
%      matrice di Jacobi con alfa(k)=0, beta(0)=pi, beta(1)=1/2, beta(k)=1/4 per k>1

%   NOTE:
%      I nodi sono gli autovalori della matrice di Jacobi Jac mentre i pesi sono legati alla prima componente
%      degli autovettori normalizzati (V1(k))della matrice Jac secondo la relazione: p(k)=beta(0)*V1(k)^2

%   Maggiori dettagli si possono trovare in M. Frontini, "FONDAMENTI DI CALCOLO NUMERICO", Libreria Clup, 2003 e in
%   M. Frontini e E. Sormani, "FONDAMENTI DI CALCOLO NUMERICO: problemi in laboratorio", APOGEO, 2005

%   Marco Frontini, Eros Sormani

%   Revisione: 4 $  $Data: 22 aprile 2005 10:24 $

if n<=1
  z=0;p=pi;
  return
end
jac=zeros(n);
k=[1:n];
v=sqrt(ones(1,n)*1/4);
jac=jac+diag(v(1:n-1),1)+diag(v(1:n-1),-1);
jac(1,2)=sqrt(1/2);jac(2,1)=sqrt(1/2);
[p,z]=eig(jac);
norm2=sqrt(diag(p'*p));    % normalizzazione per pesi
p=(pi*p(1,:)'.^2)./norm2;   % pi=beta0; ' per aver p colonna
z=diag(z);		   % z colonna
