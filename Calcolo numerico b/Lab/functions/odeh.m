function [t,y]=odeh(ode,tspan,y0,options,varargin)
%   ODEH risolve un sistema di equazioni differenziale con il metodo di Heun
%   [t,y] = odeh(ode,tspan,y0,options) con tspan = [t0 tfinal] integra il
%   sistema di equazioni differenziali y'=ode(t,y) da t0 a tfinal con
%   condizione iniziale y0. La funzione ode(t,y) deve restituire un vettore colonna
%   corrispondente a f(t,y). options è un argomento creato con la funzione odeset (help odeset) e 
%   deve contenere solo il passo di integrazione iniziale definito nella proprietà 'InitialStep'
%   
%   [t,y] = odeh(ode,tspan,y0,options,p1,p2) accetta in ingresso p1,p2,... parametri addizionali
%   necessari all'odefile ode(t,y,p1,p2...)
%
%   Esempio
%         h = odeset('InitialStep',0.1)
%         [t,y]=odeh(@f,[0 10],4,h);   
%      integra l'equazione y'=f(t,y) nell'intervallo [0 10] con condizione iniziale y(0)=4
%      utilizzando il metodo di Heun con passo h=0.1. La funzione f(t,y) è definita come una
%      funzione MATLAB usuale; ad esempio:
%        function dydt=f(t,y)
%        dydt=-y;
%
%   Esempio
%        h = odeset('InitialStep',0.05);
%        R=1.4;L=2;C=0.32;Vs=1;
%        [t,Y]=odeh(@F,[0 15],[0.5;0],h,R,L,C,Vs);
%        plot(Y(:,1),Y(:,2))
%      integra il sistema di equazioni differenziali Y'=F(t,Y,R,L,C,Vs), dove Y e F sono vettori
%      colonna, nell'intervallo [0 15] con condizioni iniziali [Y(1);Y(2)]=[0.5;0], valori dei
%      parametri R=1.4;L=2;C=0.32;Vs=1 e passo h=0.05. La funzione F(t,Y,R,L,C,Vs) è definita come
%      una funzione MATLAB usuale; ad esempio:
%        function dydt=F(t,y,R,L,C,Vs)
%        dydt=[1/C*y(2);1/L*(Vs-y(1)-R*y(2))];
%
%   Vedere anche 
%       funzioni per l'integrazione di equazioni differenziali:  ODEE, ODERK4, ODE12
%       definizione delle proprietà dei resolver: ODESET
%       creazione di una function handle: @
%
%   NOTE: 
%
%   NOTE:
%     Questa parte mostra la sintassi di ODEH per la versione v5.
%
%   Esempio
%         h = odeset('InitialStep',0.1)
%         [t,y]=odeh('f',[0 10],4,h);   
%      integra l'equazione y'=f(t,y) nell'intervallo [0 10] con condizione iniziale y(0)=4
%      utilizzando il metodo di Heun con passo h=0.1. La funzione f(t,y) è definita come una
%      funzione MATLAB usuale; ad esempio:
%        function dydt=f(t,y)
%        dydt=-y;
%
%   Esempio
%        h = odeset('InitialStep',0.05);
%        R=1.4;L=2;C=0.32;Vs=1;
%        [t,Y]=odeh('F',[0 15],[0.5;0],h,R,L,C,Vs);
%        plot(Y(:,1),Y(:,2))
%      integra il sistema di equazioni differenziali Y'=F(t,Y,R,L,C,Vs), dove Y e F sono vettori
%      colonna, nell'intervallo [0 15] con condizioni iniziali [Y(1);Y(2)]=[0.5;0], valori dei
%      parametri R=1.4;L=2;C=0.32;Vs=1 e passo h=0.05. La funzione F(t,Y,R,L,C,Vs) è definita come
%      una funzione MATLAB usuale; ad esempio:
%        function dydt=F(t,y,R,L,C,Vs)
%        dydt=[1/C*y(2);1/L*(Vs-y(1)-R*y(2))];

%   Maggiori dettagli si possono trovare in M. Frontini, "FONDAMENTI DI CALCOLO NUMERICO", Libreria Clup, 2003 e in
%   M. Frontini e E. Sormani, "FONDAMENTI DI CALCOLO NUMERICO: problemi in laboratorio", APOGEO, 2005

%   Marco Frontini, Eros Sormani

%   Revisione: 4 $  $Data: 22 aprile 2005 10:24 $

if nargin < 4
    error('Parametri di ingresso insufficienti.');
end  

h=odeget(options,'InitialStep');

tt=[tspan(1):h:tspan(2)];

if tt(end)~= tspan(2)
    tt=[tt,tspan(2)];
end

y=y0;
[n,m]=size(y);
if n<m
    y=y';   %nel caso in cui si usi con un sistema differenziale i vettori li considera colonna
end
for t=tt(1:end-1)
    yo=y(:,end);
    yy=yo;
    fn=feval(ode,t,yy,varargin{:});   % tangente nel punto iniziale
    t=t+h;	     
    yy=yy+h*fn;
    fn1=feval(ode,t,yy,varargin{:}); 
    y=[y,yo+.5*h*(fn+fn1)];	%accoda alle soluzioni al passo precedente la nuova
end
t=tt';
y=y';
