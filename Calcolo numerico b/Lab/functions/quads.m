function  t=quads(f,a,b,n)
%   QUADS valutazione numerica di integrali utilizzando la formula composita di Simpson.
%   t=quads(f,a,b,n) calcola l'integrale della funzione f in [a,b] con la formula di Simpson
%   su n nodi. Il numero di nodi deve essere dispari.
%   f è una stringa contenente la definizione della funzione.
%
%   Esempio
%         syms x
%         f=x^2*exp(sin(x))
%         t=quads(f,-6,-2,51)
%      calcola l'integrale di f in [-6,-2] utilizzando la formula composita di Simpson su 51
%      nodi
%
%   Esempio
%         t=quads('exp(-t^2)',-0.5,0.5,21)
%      calcola l'integrale di f in [-0.5,0.5] utilizzando la formula composita di Simpson su 21
%      nodi
%  
%   Vedere anche 
%       funzioni per il calcolo di integrali:  QUADM, QUADT
%       funzioni per il calcolo di pesi e nodi di formule gaussiane classiche: ZPLAGUE, ZPLEGE, ZPCHEBY

%   NOTE:
%      La funzione necessita del Symbolic Toolbox; il numero di nodi deve essere dispari

%   Maggiori dettagli si possono trovare in M. Frontini, "FONDAMENTI DI CALCOLO NUMERICO", Libreria Clup, 2003 e in
%   M. Frontini e E. Sormani, "FONDAMENTI DI CALCOLO NUMERICO: problemi in laboratorio", APOGEO, 2005

%   Marco Frontini, Eros Sormani

%   Revisione: 4 $  $Data: 22 aprile 2005 10:24 $

if n == fix(n/2)*2
   error('Attenzione: il numero dei punti deve essere dispari');
end
%
h=(b-a)/(n-1);
x=[a:h:b];
% per certi valori di n, h non fa arrivare a b
if max(size(x))~=n;x(n)=b;end
%
y=double(subs(f,x));
if length(y)<length(x) %caso in cui la funzione integranda è una costante.
    y=y*ones(size(x));
end
t=4*sum(y(2:2:n));
t=t+2*sum(y(3:2:n-1));
t=h*(t+y(1)+y(n))/3;