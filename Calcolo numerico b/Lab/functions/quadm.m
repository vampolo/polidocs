function  t=quadm(f,a,b,n)
%   QUADM valutazione numerica di integrali utilizzando la formula composita del punto medio.
%   t=quadm(f,a,b,n) calcola l'integrale della funzione f in [a,b] con la formula del punto medioo
%   su n intervallini (coincidenti con n punti medi).
%   f è una stringa contenente la definizione della funzione.
%
%   Esempio
%         syms x
%         f=x^2*exp(sin(x))
%         t=quadm(f,-6,-2,50)
%      calcola l'integrale di f in [-6,-2] utilizzando la formula composita del punto medio su 50
%      intervallini
%
%   Esempio
%         t=quadm('exp(-t^2)',-0.5,0.5,20)
%      calcola l'integrale di f in [-0.5,0.5] utilizzando la formula composita del punto medio su 20
%      intervallini
%  
%   Vedere anche 
%       funzioni per il calcolo di integrali:  QUADT, QUADS
%       funzioni per il calcolo di pesi e nodi di formule gaussiane classiche: ZPLAGUE, ZPLEGE, ZPCHEBY

%   NOTE:
%      La funzione necessita del Symbolic Toolbox

%   Maggiori dettagli si possono trovare in M. Frontini, "FONDAMENTI DI CALCOLO NUMERICO", Libreria Clup, 2003 e in
%   M. Frontini e E. Sormani, "FONDAMENTI DI CALCOLO NUMERICO: problemi in laboratorio", APOGEO, 2005

%   Marco Frontini, Eros Sormani

%   Revisione: 4 $  $Data: 22 aprile 2005 10:24 $

h=(b-a)/n;
x=[a+h/2:h:b];
%
y=double(subs(f,x));
if length(y)<length(x) %caso in cui la funzione integranda è una costante.
    y=y*ones(size(x));
end
t=h*sum(y);