function [zerob,fzerob,iterb]=Bzero(f,a,b,toll)
%   BZERO risolve equazioni non lineari con il metodo di bisezione
%   [zerob,fzerob,iterb]=bzero(f,a,b,toll) calcola lo zero della funzione f 
%   con tolleranza toll appartenente all'intervallo [a,b]. L'intervallo deve essere tale per cui
%   f(a)*f(b)<0.
%   f è una stringa che definisce la funzione.
%   La funzione restituisce zerob, approssimazione dello zero della funzione, fzerob, valore che assume
%   la funzione in zerob ed il numero di iterazioni impiegato (iterb).
%
%   Esempio
%         syms x
%         f=x*exp(-x)-exp(-3)
%         [zerob,fzerob,iterb]=bzero(f,0,0.2,1e-10)
%      calcola lo zero di f nell'intervallo del teorema degli zeri [0,0.2] con tolleranza 1e-10
%
%   Esempio
%         [zerob,fzerob,iterb]=Bzero('sin(t)-(t-1)^2*log(t)-1',0.2,0.4,1e-10)
%      calcola lo zero di f con il metodo di bisezione nell'intervallo [0.2,0.4]
%      con tolleranza 1e-10
%  
%   Vedere anche 
%       funzioni per il calcolo degli zeri:  NZERO

%   NOTE:
%      La funzione necessita del Symbolic Toolbox

%   Maggiori dettagli si possono trovare in M. Frontini, "FONDAMENTI DI CALCOLO NUMERICO", Libreria Clup, 2003 e in
%   M. Frontini e E. Sormani, "FONDAMENTI DI CALCOLO NUMERICO: problemi in laboratorio", APOGEO, 2005

%   Marco Frontini, Eros Sormani

%   Revisione: 4 $  $Data: 22 aprile 2005 10:24 $

f=sym(f);
x=a;f1=double(subs(f,x));
if f1==0;x=a;fx=f1;n=0;
    zerob=x;
    fzerob=fx;
    iterb=n;
    return;
end
x=b;f2=double(subs(f,x));
if f2==0;x=b;fx=f2;n=0;
    zerob=x;
    fzerob=fx;
    iterb=n;
    return;
end
if sign(f1)*sign(f2) > 0
  error('Attenzione f(a)*f(b) > 0 '),
  return,
end;
% n= numero iter. neces. per prec. toll
n=fix(log(abs(b-a)/toll)/log(2)+1);
for i=1:n;
  x=a+(b-a)/2; % piu' accurato di (a+b)/2
  fx=double(subs(f,x));
    if sign(f1)*sign(fx)>0;
	 xn=b;
	 a=x;
      else;
	 xn=a;
	 b=x;
    end;
end;
zerob=x;
fzerob=fx;
iterb=n;