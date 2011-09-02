function [a,b,c,d,S,S1]=CubicSpline(x,y,derivata,muL,muR,VV)
%   CUBICSPLINE Interpola i dati x,y  mediante una spline cubica determinata dai valori muL, muR 
%   assunti agli estremi dalla derivata di ordine 1 o 2, definito dal parametro derivata.
%   [a,b,c,d,S,S1]=CubicSpline(x,y,derivata,muL,muR,VV) restituisce nei vettori a,b,c,d i coefficienti
%   delle cubiche a(i)+b(i)(z-x(i))+c(i)(z-x(i))^2+d(i)(z-x(i))^2*(z-x(i+1)) negli intervalli [x(i),x(i+1)]
%   e i vettori S e S1 dei valori, della spline e della sua derivata prima, nei punti definiti dal vettore VV.
%
%   Esempio
%         x=linspace(-1,1,10);
%         y=exp(x);
%         X=linspace(-1,1,101);
%         [a,b,c,d,S]=CubicSpline(x,y,1,exp(-1),exp(1),X);
%      calcola la spline vincolata interpolante la funzione exp(x) in 10 punti equidistanti dell'intervallo [-1,1]
%      valutandola in 101 punti equidistanti
%  
%   Vedere anche 

%   NOTE: 

%   Maggiori dettagli si possono trovare in M. Frontini, "FONDAMENTI DI CALCOLO NUMERICO", Libreria Clup, 2003 e in
%   M. Frontini e E. Sormani, "FONDAMENTI DI CALCOLO NUMERICO: problemi in laboratorio", APOGEO, 2005

%   Marco Frontini, Eros Sormani

%   Revisione: 4 $  $Data: 22 aprile 2005 10:24 $

n=length(x);
Dx=diff(x);
yp=diff(y)./Dx;
T=zeros(n-2,n-2);
r=zeros(n-2,1);
for i=2:n-3
   T(i,i)=2*(Dx(i)+Dx(i+1));
   T(i,i-1)=Dx(i+1);
   T(i,i+1)=Dx(i);
   r(i)=3*(Dx(i+1)*yp(i)+Dx(i)*yp(i+1));
end

if derivata==1
%codice per Spline complete
T(1,1)=2*(Dx(1)+Dx(2));
T(1,2)=Dx(1);
T(n-2,n-2)=2*(Dx(n-2)+Dx(n-1));
T(n-2,n-3)=Dx(n-1);
r(1)=3*(Dx(2)*yp(1)+Dx(1)*yp(2))-Dx(2)*muL;
r(n-2)=3*(Dx(n-1)*yp(n-2)+Dx(n-2)*yp(n-1))-Dx(n-2)*muR;
s=[muL;T\r(1:n-2);muR];		%vettore soluzione delle derivate prime nei nodi
end

if derivata==2
%codice per Spline con derivata seconda fissata agli estremi
T(1,1)=2*Dx(1)+1.5*Dx(2);
T(1,2)=Dx(1);
T(n-2,n-2)=1.5*Dx(n-2)+2*Dx(n-1);
T(n-2,n-3)=Dx(n-1);
r(1)=1.5*Dx(2)*yp(1)+3*Dx(1)*yp(2)+Dx(1)*Dx(2)*muL/4;
r(n-2)=3*Dx(n-1)*yp(n-2)+1.5*Dx(n-2)*yp(n-1)-Dx(n-2)*Dx(n-1)*muR/4;
stilde=T\r;
s1=(3*yp(1)-stilde(1)-muL*Dx(1)/2)/2;
sn=(3*yp(n-1)-stilde(n-2)+muR*Dx(n-1)/2)/2;
s=[s1;stilde;sn];
end

a=y(1:n-1);
b=s(1:n-1);
c=(yp'-s(1:n-1))./Dx';
d=(s(1:n-1)+s(2:n)-2*yp')./(Dx'.^2);

%valutazione della Spline nei punti VV
j=0;
nint=max(size(VV)); % nint: numero intervalli
DV=diff(VV);
for i=1:nint	
   for k=1:n-1
      if (VV(i)>=x(k))&(VV(i)<=x(k+1))%determina l'intervallo in cui si trova il punto VV(i);
         j=k;
      end
   end
   if j==0;
      VV=0;
      disp(' ** ERRORE **  VV fuori intervallo ')
      return
   end
   S(i)=a(j)+b(j)*(VV(i)-x(j))+c(j)*(VV(i)-x(j))^2+d(j)*(VV(i)-x(j))^2*(VV(i)-x(j+1));
   S1(i)=+b(j)+2*c(j)*(VV(i)-x(j))+2*d(j)*(VV(i)-x(j))*(VV(i)-x(j+1))+d(j)*(VV(i)-x(j))^2;
end



