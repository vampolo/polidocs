function f=fourier(m,n)
%   FOURIER Matrice di Fourier
%   f=fourier(m,n) è la matrice di Fourier di ordine n con elementi f(j,k)=exp(-ik*2pi*j/n)
%   Se y è un vettore (n,1) allora Y=fourier(n)*y è la trasformata discreta del vettore
%
%   Esempio
%  
%   Vedere anche:


%   NOTE: 

%   Maggiori dettagli si possono trovare in M. Frontini, "FONDAMENTI DI CALCOLO NUMERICO", Libreria Clup, 2003 e in
%   M. Frontini e E. Sormani, "FONDAMENTI DI CALCOLO NUMERICO: problemi in laboratorio", APOGEO, 2005

%   Marco Frontini, Eros Sormani

%   Revisione: 4 $  $Data: 22 aprile 2005 10:24 $

if nargin == 1
   n=m;
end
w=exp(2*pi*i/m);
vw=w.^[0:m-1]';
f=ones(m,n);
f(:,2)=vw;
for j=3:n;
   f(:,j)=f(:,2).*f(:,j-1);
end