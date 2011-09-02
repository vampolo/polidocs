function [zeron,fzeron,itern]=Nzero(f,x0,toll,nmax,p)
%   NZERO risolve equazioni e sistemi di equazioni non lineari con il metodo di Newton
%   [zeron,fzeron,itern]=nzero(f,x0,toll,nmax,p) calcola lo zero della funzione f,
%   di molteplicità p, con tolleranza toll partendo dall'approssimazione x0. nmax rappresenta il
%   numero massimo di iterazioni che il metodo può eseguire, compatibile con la tolleranza richiesta,
%   per il calcolo dello zero; nmax rappresenta un controllo sulla convergenza del metodo.
%   f è una stringa contenente la definizione del sistema (vettore riga) di n variabili simboliche.
%   x0 è un vettore riga di n valori iniziali.
%   p è la molteplicità della radice; p è opzionale e definibile solo nel caso monodimensionale.
%   La funzione restituisce zeron, approssimazione dello zero della funzione, fzeron, valore che assume
%   la funzione in zeron ed il numero di iterazioni impiegato (itern).
%
%   Esempio
%         [zeron,fzeron,itern]=nzero('[x-y^2+3*log(x),1-5*x+2*x^2-x*y]',[3,2],1e-3,10)
%      calcola lo zero del sistema di equazioni [x-y^2+3*log(x),1-5*x+2*x^2-x*y] partendo dal vettore
%      [3,2] con tolleranza 1e-3.
%
%   Esempio
%         syms x
%         f=x*exp(-x)-exp(-3)
%         [zeron,fzeron,itern]=nzero(f,0.5,1e-5,10)
%      calcola lo zero di f partendo da 0.5 con tolleranza 1e-5
%
%   Esempio
%         [zeron,fzeron,itern]=nzero('tan(x)*(cos(x)*tan(x)-x)+x^2/(4*cos(x))',1.9,1e-5,10,2)
%      calcola lo zero di f, di molteplicità 2, partendo da 1.9 con tolleranza 1e-5
%  
%   Vedere anche 
%       funzioni per il calcolo degli zeri:  BZERO

%   NOTE:
%      La funzione necessita del Symbolic Toolbox

%   Maggiori dettagli si possono trovare in M. Frontini, "FONDAMENTI DI CALCOLO NUMERICO", Libreria Clup, 2003 e in
%   M. Frontini e E. Sormani, "FONDAMENTI DI CALCOLO NUMERICO: problemi in laboratorio", APOGEO, 2005

%   Marco Frontini, Eros Sormani

%   Revisione: 4 $  $Data: 22 aprile 2005 10:24 $


if nargin<5
    p=1;
end

f=sym(f);
varsym=findsym(f); %determinazione delle variabili nel sistema definito da f

err=1;
u=x0';
n=0;
J=jacobian(f,varsym);
while err>toll & (n<=nmax)
    Jc=double(subs(J,['[',varsym,']'],u));
    ff=double(subs(f,['[',varsym,']'],u)).';
    u1=u-Jc\(p*ff);
    err1=norm(subs(f,['[',varsym,']'],u)).';
    err2=norm(u1-u);
    err=max([err1,err2]);
    u=u1;
    n=n+1;
    if n>nmax
        disp('Raggiunto il limite massimo di iterazioni;')
        disp('il metodo non converge o nmax è inadeguato alla tolleranza richiesta')
        error(' ');
    end
end
zeron=u;
itern=n-1;
fzeron=double(subs(f,['[',varsym,']'],u)).';
