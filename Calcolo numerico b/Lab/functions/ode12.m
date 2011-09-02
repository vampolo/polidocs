function [t,y]=ode12(ode,tspan,y0,ep,options,varargin)
%   ODE12 risolve un'equazione differenziale ccon il metodo adattativo di Eulero-Heun
%   [t,y] = ode12(ode,tspan,y0,ep,options) con tspan = [t0 tfinal] integra
%   l'equazioni differenziali y'=ode(t,y) da t0 a tfinal con
%   condizione iniziale y0 controllando l'errore di troncamento locale unitario ep.
%   La funzione ode(t,y) deve restituire un vettore colonna
%   corrispondente a f(t,y). options è un argomento creato con la funzione odeset (help odeset) e 
%   deve contenere solo il passo di integrazione iniziale definito nella proprietà 'InitialStep'
%   
%   [t,y] = ode12(ode,tspan,y0,ep,options,p1,p2) accetta in ingresso p1,p2,... parametri addizionali
%   necessari all'odefile ode(t,y,p1,p2...)
%
%   Esempio
%         h = odeset('InitialStep',0.1)
%         [t,y]=ode12(@f,[0 10],4,0.001,h);     
%      integra l'equazione y'=f(t,y) nell'intervallo [0 10] con condizione iniziale y(0)=4
%      utilizzando il metodo di Eulero-Heun con passo h=0.1 garantendo un errore di troncamento locale unitario 
%      inferiore o uguale a 0.001 La funzione f(t,y) è definita come una
%      funzione MATLAB usuale; ad esempio:
%        function dydt=f(t,y)
%        dydt=-y;
%
%   Vedere anche 
%       funzioni per l'integrazione di equazioni differenziali:  ODEE, ODEH, ODERK4
%       definizione delle proprietà dei resolver: ODESET
%       creazione di una function handle: @
%
%   NOTE: 
%
%   NOTE:
%     Questa parte mostra la sintassi di ODE12 per la versione v5.
%
%   Esempio
%         h = odeset('InitialStep',0.1)
%         [t,y]=ode12('f',[0 10],4,0.001,h);     
%      integra l'equazione y'=f(t,y) nell'intervallo [0 10] con condizione iniziale y(0)=4
%      utilizzando il metodo di Eulero-Heun con passo h=0.1 garantendo un errore di troncamento locale unitario 
%      inferiore o uguale a 0.001 La funzione f(t,y) è definita come una
%      funzione MATLAB usuale; ad esempio:
%        function dydt=f(t,y)
%        dydt=-y;

%   Maggiori dettagli si possono trovare in M. Frontini, "FONDAMENTI DI CALCOLO NUMERICO", Libreria Clup, 2003 e in
%   M. Frontini e E. Sormani, "FONDAMENTI DI CALCOLO NUMERICO: problemi in laboratorio", APOGEO, 2005

%   Marco Frontini, Eros Sormani

%   Revisione: 4 $  $Data: 22 aprile 2005 10:24 $

if nargin < 4
    error('Parametri di ingresso insufficienti.');
end  

h=odeget(options,'InitialStep'); %acquisisce il passo di integrazione iniziale
tt=tspan(1);
t(1)=tt;
y=y0;
[n,m]=size(y);
if n<m
    y=y';   %nel caso in cui si usi con un sistema differenziale i vettori li considera colonna
end
i=1;
while tt<=tspan(2)
    if tt+h>=tspan(2)
        h=tspan(2)-tt;
    end
    yy=y(:,end);
    fn=feval(ode,tt,yy,varargin{:});
    yeulero=yy+h*fn;    %soluzione con il metodo di Eulero
    fn1=feval(ode,tt+h,yeulero,varargin{:});  
    yheun=yy+.5*h*(fn+fn1);  %soluzione con il metodo di Heun
    
    err=abs(yheun-yeulero); %stima dell'errore di troncamento locale
    if (err<=h*ep & err>=1/4*h*ep)
        %se err è compreso tra 1/4*h*ep e h*ep allora procede
        yy=yheun;
        y=[y,yy];
        i=i+1;
        t(i)=tt+h;
        tt=tt+h;       
    elseif err>h*ep
        %se err>h*ep allora dimezza il passo e ripete l'integrazione
        h=h/2;
    else %err<1/4*h*ep
        %se err<1/4*h*ep allora procede ma raddoppiando il passo di integrazione perchè troppo piccolo
        yy=yheun;
        y=[y,yy];
        i=i+1;
        t(i)=tt+h;
        h=h*2;
        tt=tt+h;   
    end
end
