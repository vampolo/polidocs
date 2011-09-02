%% LAB - 05 - 08/11/2010
%% Intro
help meshgrid
help contour

help mldivide
help pinv
help qr
help svd

%% EX. 1
clear all, close all, clc
% Definition of the coefficient matrix A and the RHS b:
A = [3 2; 2 6];
b = [2 -8]';

% 1) By hand...

% 2) Remind that the gradient methods can be interpreted as minimizing
% methods for the functional PHI(X).
% We can construct the level curves related to the quadratic form PHI(X):
% Creation of the two-dimensional grid and calculation of PHI:
xx = linspace(-3,7,200);
yy = linspace(-7,3,200);
[X,Y] = meshgrid(xx,yy);
PHI = 0.5*(X.*(A(1)*X+A(2)*Y)+Y.*(A(3)*X+A(4)*Y)) - (b(1)*X+b(2)*Y);
% Contour plot superposed to the previous graph:
contour(X,Y,PHI,50), colormap hot, colorbar
hold on
% Let's implement the gradient (steepest descent) method in the following
% way, adding the visualization of each step.
% Starting guess...
x0 = [-2 2]'; tol = 1e-3;
nmax = 100;
xv=x0;
plot(xv(1),xv(2),'ob','LineWidth',2)

% Implementation of the Steepest Descent Method:
r = b - A*xv; % initial residual
norm2b = norm(b);
for iter=0:nmax,

    alpha = (r'*r)/(r'*A*r);
    xn = xv + alpha*r;     
    r = r - alpha*A*r; % = r - A*xn
    err=norm(r)/norm2b;
    
    plot([xv(1) xn(1)], [xv(2) xn(2)],'-or','LineWidth',3)
    
    xv=xn;
    if(err<tol),
       break, 
    end
end
if iter==nmax,
   disp('Attenzione il metodo non converge o nmax piccolo per la tolleranza richiesta')
end
% We obtain: 
iter
xv

% Comments: note the directions of the steps in the graph respect to the
% level curves of the quadratic form PHI...

%% EX. 2
% Implementation of the function grad.m (start from richpre.m file for
% example...)

% 1) Construction of a cycle and visualization...
clear all, close all
nmax = 2000;
toll = 1e-6;
nn = [3 5 10 20];
for n=nn
    A  = gallery('poisson',n);
    b  = A*ones(size(A(:,1)));
    x0 = zeros(size(A(:,1)));
    [xv,iter,err] = grad(A,b,x0,nmax,toll);
    figure(1),
    subplot(1,3,1), semilogy(err,'or'), hold on, ...
        xlabel('Iteration'), ylabel('Normalized residual')
    subplot(1,3,2), loglog(n^2,iter,'ob'), hold on, ...
        xlabel('Matrix order'), ylabel('n_{iter}')
    subplot(1,3,3), loglog(condest(A),iter,'ob'), hold on, ...
        xlabel('\kappa(A)'), ylabel('n_{iter}')
    pause % press return to go on
end

% Comments...

%% EX. 3
clear all
close all
clc
% 1) Solution of the system in the lsq sense:
A = [1 0 -1; 1 0 -3; 0 1 1; 0 -1 1];
b = [4 6 -1 2]'
A1 = A'*A;
b1 = A'*b;
x = A1\b1
% check the orthogonality of the solution respect to R(A)
err = A'*(A*x-b)  % -> ok

% 2) Cholesky factorization can be applied...comments...
cond(A)
cond(A1)

% 3) SVD on the matrix: A = U*D*V'
[U,D,V] = svd(A)
% Appling U^-1 on the left to the RHS
b1 = U'*b;
% catching the singular values from D
sigma = diag(D);
% Appling D^-1 on the left of b1:
z = D\b1
% the solution will be
x = V*z

% 4) Look at the matrix structure in the decomposition:
[Q,R,E] = qr(A)
norm(Q)
R1 = R(1:3,1:3)
%For the RHS:
g = Q'*b
% We obtain
xqr = R1\g(1:3)
xqr = E*xqr

% Equivalently:
[q,r] = qr(A)
xqr = r\(q'*b)

% 5) Moore-Penrose pseudoinverse 
pinv(A)*b
% comments: more computation needed

% 6) Compare the obtained results with
A\b
help mldivide

%% EX. 4
clear all, clc
A = [1 2 3; 4 5 6; 7 8 9; 10 11 12; 13 14 15];
b = [16 17 18 19 20]'

% 1) A is rank deficient: no unique solution. Check:
A1 = A'*A;
x1 = A1\(A'*b)
r = rank(A)

% 2) Note The middle column is the average of... xnull = [1 -2 1]'
% ...

% 3) lSQ solution by means of \ command
xx = A\b % basic solution: r non-zero components (see QR factorization)
% ...and pinv command
yy = pinv(A)*b
% pinv does not give any warning. Moreover the solutions are not the same
% as for full rank systems. pinv finds the minimum norm solution:
norm(A*xx-b,inf)
norm(A*yy-b,inf)

norm(xx),norm(yy)
% comments.

% 4) SVD
[U,D,V] = svd(A)
b1 = U'*b;
z = D\b1;
x=V*z
norm(x)

%% EX. "A case study: Image compression"

% see the related m-file (SVD_image.m):
edit SVD_image
%% EX. 5
% 1) Eigenvalue localization
A = [3 1 1 0 0; 0 4 1 0.5 0.5; 2 2 5 0 0; 2 2 1 9 1; 0 0 2 3 9]
help gersch
gersch(A)
% comments...

% 2) Using eig command in MATLAB:
[V,D] = eig(A)

% 3) Study of the convergence of Jacoby iteration method applied to A:
D = diag(diag(A));
L = tril(A,-1);
U = triu(A,1);
Bj = -D\(L+U);
gersch(Bj)
% comments...

%% HOMEWORK
%% EX H5.1
% ...
%% EX H5.2
A = [ 1 2 3; 2 3 4; 3 4 5; 4 5 6;]
b = [1 2 2 1];
% 1) ...
rank(A)
% ...
%% EX H5.3
% ...
%% EX H5.4
% ...
%% EX H5.5
A1 = [4 -1 1 0 0; 1 3 -1 0 0; 0 1 1 0 0; 0 0 0 2 1; 0 0 0 1 8];
A2 = [5 4 1 1;4 5 1 1;1 1 4 2;1 1 2 4];
% For A1:
gersch(A1) % the circle on the right (highest lambda) is isolated -> real eigenvalue...
% Also note the conditioning of its eigenvalues...
% For A2 (is symmetric!):
gersch(A2) % the eigenvalues lambda_i are real and are in [-1,11]
%% EX H5.6* (optional)
% See ref. 4 [chapter 10.3], for a brief explanation. It's useful for next
% lab.