function [xv,iter,err]=grad(A,b,x0,nmax,toll)
%   GRAD Calcola la soluzione del sistema lineare Ax=b utilizzando
%   il metodo iterativo del gradiente senza precondizionatore.
%   ...

[n,m]=size(A);
if n~=m
 error('La matrice deve essere quadrata.') 
end 
xv=x0;
r=b-A*x0;
norm_b = norm(b);
if  (norm_b == 0.0), norm_b = 1.0; end

for iter=0:nmax,
    
    alpha = (r'*r)/(r'*A*r);
    xn = xv + alpha*r;     
    r = r - alpha*A*r; % = r - A*xn
    err(iter+1) = norm(r)/norm_b; % vettore contenente errori relativi sul residuo
    
    xv=xn;
    if(err(iter+1)<toll),
       break, 
    end
end
if iter==nmax,
   disp('Attenzione il metodo non converge o nmax è piccolo per la tolleranza richiesta')
end
return