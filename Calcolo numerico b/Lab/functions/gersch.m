function gersch(A)
%   GERSCH Visualizzazione grafica dei cerchi di Gerschgorin
%   gersch(A) visualizza in tre finestre grafiche distinte i cerchi di riga, i cerchi di colonna e 
%   l'intersezione dell'unione dei cerchi di riga e di colonna.
%   Sfruttando la proprietà di trasparenza degli oggetti grafici, l'intersezione dell'unione dei
%   cerchi di riga e di colonna appare di colore arancione; per il teorema di Gerschgorin gli
%   autovalori appartengono a suddetta intersezione.
%
%   Esempio
%         A=rand(5)+diag([10,4,2,-1,9]);
%         gersch(A)            
%   
%   Vedere anche 
%       funzioni per il calcolo degli autovalori ed autovettori:  POTENZE, POTINV
%       esempi di calcolo degli autovalori:
%
%   NOTE: 
%
%   NOTE:
%     Questa parte mostra la sintassi di GERSCH per la versione v5.
%
%   gersch(A) visualizza in due finestre grafiche distinte i cerchi di riga ed i cerchi di colonna.
%   Per il teorema di Gerschgorin gli autovalori appartengono all'intersezione dell'unione dei cerchi 
%   di riga e di colonna.
%
%   Esempio
%         A=rand(5)+diag([10,4,2,-1,9]);
%         gersch(A)            

%   Maggiori dettagli si possono trovare in M. Frontini, "FONDAMENTI DI CALCOLO NUMERICO", Libreria Clup, 2003 e in
%   M. Frontini e E. Sormani, "FONDAMENTI DI CALCOLO NUMERICO: problemi in laboratorio", APOGEO, 2005

%   Marco Frontini, Eros Sormani

%   Revisione: 4 $  $Data: 22 aprile 2005 10:24 $


[n,m]=size(A);
if n~=m;
   disp(' *** ERRORE *** matrice non quadrata '),
   return;
end;

c=diag(A); %estrazione dei centri dei cerchi

rr=sum(abs(A-diag(c))'); %il vettore riga rr contiene i raggi dei cerchi di riga 
rc=sum(abs(A-diag(c))); %il vettore riga rc contiene i raggi dei cerchi di colonna
t=0:0.1:2*pi; %campionatura con passo 0.1 dell'intervallo 0 2*pi
xr=real(c)*ones(1,length(t))+rr'*cos(t); %ogni riga di xr descrive sull'asse reale le ascisse di un cerchio di riga
xc=real(c)*ones(1,length(t))+rc'*cos(t);
yr=imag(c)*ones(1,length(t))+rr'*sin(t); %ogni riga di yr descrive sull'asse reale le ordinate di un cerchio di riga
yc=imag(c)*ones(1,length(t))+rc'*sin(t);
xmax=max(max([xr;xc]));ymax=max(max([yr;yc]));
xmin=min(min([xr;xc]));ymin=min(min([yr;yc]));



%-------------------Visualizzazione dei cerchi di riga------------------------
figure(1)
set(gca,'nextplot','replacechildren') 
clf reset, grid off, hold on, axis off
test=(rr==0); %controlla i raggi dei cerchi di riga nulli
crigragnullo=c(test==1);
Pr1=plot(real(crigragnullo),imag(crigragnullo),'r*');

Pr=patch(xr',yr','r');
Pr2=plot(xr',yr','k');
axis([xmin-0.5,xmax+0.5,ymin-0.5,ymax+0.5]),axis equal
LimXR=get(gca,'Xlim'); 
LimYR=get(gca,'Ylim');

R=getframe;

axis on, grid on
hold off

figure(2)
clf reset, axes;
Rcop=copyobj(Pr,gca);
hold on
Rcop1=copyobj(Pr1,gca);
Rcop2=copyobj(Pr2,gca);
view(2)

axis([xmin-0.5,xmax+0.5,ymin-0.5,ymax+0.5]),axis equal

title('Cerchi di Riga')
grid on, hold off

%-------------------Visualizzazione dei cerchi di colonna----------------------
figure(1)
clf reset, grid off,axis equal, grid off, hold on, axis off

test=(rc==0); %controlla i raggi dei cerchi di riga nulli
ccolragnullo=c(test==1);
Pc1=plot(real(ccolragnullo),imag(ccolragnullo),'y*');


Pc=patch(xc',yc','y');%visualizzazione dei cerchi di colonna in colore giallo con trasparenza 0.4
Pc2=plot(xc',yc','k');
axis([xmin-0.5,xmax+0.5,ymin-0.5,ymax+0.5]),axis equal

LimXC=get(gca,'Xlim');
LimYC=get(gca,'Ylim');

C=getframe;

grid on, axis on

hold off

figure(3)
clf reset, axes;
Ccop=copyobj(Pc,gca);
hold on
Ccop1=copyobj(Pc1,gca);
Ccop2=copyobj(Pc2,gca);
view(2)

axis([xmin-0.5,xmax+0.5,ymin-0.5,ymax+0.5]),axis equal

title('Cerchi di Colonna')
grid on, hold off

%-------------------Visualizzazione grafica del teorema di Gerschgorin solo per versione v6 o superiore---------
v=version;
if str2num(v(1))>5
    figure(1)
    clf reset
    hold on
    Cim=image(LimXC,LimYC,C.cdata,'AlphaData',0.5);
    rotate(Cim,[1 0 0],180)
    Rim=image(LimXR,LimYR,R.cdata,'AlphaData',0.3);
    rotate(Rim,[1 0 0],180)
    grid on, axis equal
    title('Visualizzazione grafica del teorema di Gerschgorin')
    hold off
else
    close(figure(1))
end