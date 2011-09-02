function  t=quadt(f,a,b,n)
%   QUADT valutazione numerica di integrali utilizzando la formula composita dei trapezi
%   t=quadt(f,a,b,n) calcola l'integrale della funzione f in [a,b] con la formula dei trapezi
%   su n nodi.
%   f è una stringa contenente la definizione della funzione.
%
%   Esempio
%         syms x
%         f=x^2*exp(sin(x))
%         t=quadt(f,-6,-2,50)
%      calcola l'integrale di f in [-6,-2] utilizzando la formula composita dei trapezi su 50
%      nodi
%
%   Esempio
%         t=quadt('exp(-t^2)',-0.5,0.5,21)
%      calcola l'integrale di f in [-0.5,0.5] utilizzando la formula composita dei trapezi su 21
%      nodi
%  
%   Vedere anche 
%       funzioni per il calcolo di integrali:  QUADM, QUADS
%       funzioni per il calcolo di pesi e nodi di formule gaussiane classiche: ZPLAGUE, ZPLEGE, ZPCHEBY

%   NOTE:
%      La funzione necessita del Symbolic Toolbox

%   Maggiori dettagli si possono trovare in M. Frontini, "FONDAMENTI DI CALCOLO NUMERICO", Libreria Clup, 2003 e in
%   M. Frontini e E. Sormani, "FONDAMENTI DI CALCOLO NUMERICO: problemi in laboratorio", APOGEO, 2005

%   Marco Frontini, Eros Sormani

%   Revisione: 4 $  $Data: 22 aprile 2005 10:24 $


h=(b-a)/(n-1);
x=[a:h:b];
% per certi valori di n, h non fa arrivare a b
if max(size(x))~=n;x(n)=b;end
%
%
y=double(subs(f,x));
if length(y)<length(x) %caso in cui la funzione integranda è una costante.
    y=y*ones(size(x));
end
t=2*sum(y(2:n-1));
t=.5*h*(t+y(1)+y(n));