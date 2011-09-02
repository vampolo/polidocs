%% LAB - 04 - 25/10/2010
%% Intro
% Download the .m files containing the latest versions of the function used
% in the numerical labs.

help diag
help jacob
help gausseid
help richpre

%% EX. 1
% 1) Try by hand...
% 2) Find an example...

%% EX. 2
% 1) Matrix construction and LU decomposition by lu MATLAB command:
clear all
close all
clc
I4 = eye(4);
B4 = diag(-4*ones(4,1))+diag(ones(3,1),-1)+diag(ones(3,1),+1);
A4 = [B4 I4 zeros(4,4) zeros(4,4);
      I4 B4 I4 zeros(4,4);
      zeros(4,4) I4 B4 I4;
      zeros(4,4) zeros(4,4) I4 B4];
% Try also to construct the matrix exploiting kron function in MATLAB.
  
[L,U] = lu(A4);

% 2) Sparsity plot
figure(1)
subplot(2,2,1), spy(A4), title('A_4')
subplot(2,2,2), spy(L), title('L')
subplot(2,2,3), spy(U), title('U')
subplot(2,2,4), spy(L*U) 
hold on, spy(A4,'r'), title('L*U (blue), A_4 (red)')
% Comment: fill-in ...

%% EX. 3

% 1) System and Jacobi method
A = [1 2 -2; 1 1 1; 2 2 1];
b = [1 3 5]';
[xv,iter]= jacob(A,b,zeros(3,1),10,1e-5)

[xv,iter]= jacob(A,b,zeros(3,1),10,1e-8)

% Yes, the method converges in three iterations indep. on the tolerance.

% 2) Calculation of the Jacoby method iteration matrix:
D      = diag(diag(A));
L      = tril(A,-1);
U      = triu(A,1);
Bj     = -D\(L+U);
rhoBj  = max(abs(eig(Bj))) % Note: the eigenvalue conditioning of Bj
                           % is bad ...(see next labs)

Bj^2
Bj^3 % At the third iter. step we get the null matrix.
% Thus the method converges in three iter.
% Comment the result, by calculating the eigenvalues of Bj from the
% definition. -> lambda1 = lambda2 = lambda3 = 0 ...
% The method becomes direct in this case.

% 3) Gauss-Seidel: convergence.
Bgs    = -(D+L)\U;
rhoBgs = max(abs(eig(Bgs)))
% no convergence [xv,iter]= gausseid(A,b,zeros(3,1),1000,1e-5)

%% EX. 4
clc, clear all, close all
A = diag(8*ones(8,1))+diag(2*ones(7,1),1)+diag(2*ones(7,1),-1);
b = A*ones(8,1);

% 1) The matrix is...

% 2)  Convergence: check on the norm of the iteration matrix.
D = diag(diag(A));
L = tril(A,-1);
U = triu(A,1);
Bj = -D\(L+U);
Bgs = -(D+L)\U;
norm(Bj,Inf)       % ok
norm(Bgs,Inf)      % ok
% Starting from the relation concerning the error at the k-th iteration,
% we construct the estimation:
x0 = zeros(8,1);
e = 1e-12;
% For Jacobi
x1j = Bj*x0 + D\b;
kjmin = log(e*(1-norm(Bj,Inf))/norm(x1j-x0,Inf))/log(norm(Bj,Inf))
% For Gauss-Seidel
x1gs = Bgs*x0 + (D+L)\b;
kgsmin = log(e*(1-norm(Bgs,Inf))/norm(x1gs-x0,Inf))/log(norm(Bgs,Inf))

% 3) Let's verify:
[xv,n_iter]= jacob(A,b,zeros(8,1),100,1e-12)
[xv,n_iter]= gausseid(A,b,zeros(8,1),100,1e-12)

% 4) Alpha, Alpha_opt - Richardson (not preconditoned).
% P^-1*A = A is symmetryc and pos. def., thus 0<alpha<2/lambda_max
% Starting from the eigenvalues:
lambda = eig(A)
alpha_max = 2/max(lambda)
alpha_opt = 2/(max(lambda) + min(lambda))
[xv,n_iter]= richpre(A,b,zeros(8,1),40,1e-12, alpha_opt,eye(8))
% We obtain the same number of iteration of Jacobi iteration method:
% Compare the two matrices.

%% EX. 5
clc, clear all, close all
B = rand(5) + diag(10*ones(5,1));
A = B*B';

% 1) The matrix is constructed in such a way that...

% 2) Richardson preconditioned: Alpha, alpha_opt
alpha = 0:0.001:2; % alpha = linspace(0,2,2000);
P = diag(diag(A));
j = 1;
for k = alpha
B = eye(size(A)) - k*(P\A);
rhoB(j) = max(abs(eig(B)));
j = j+1;
end
plot(alpha,rhoB,'o-r'), grid on
xlabel('\alpha'), ylabel('\rho(B(\alpha))')
[rhoBmin ind_rhoBmin] = min(rhoB)
alpha_opt = alpha(ind_rhoBmin)
% Compare with theory. P and A are both symm. and pos. def. =>
lambdaPA = eig(P\A)
alpha_opt_th = 2/(max(lambdaPA) + min(lambdaPA))

%% HOMEWORK
%% EX H4.1
% 1) Try by hand...Compare the results...

%% EX H4.2
% 1) ...
%% EX H4.3 (Partial solution)
% 1) Matrix construction
clc, clear all
A = diag(3*ones(6,1)) + diag(-1*ones(5,1),-1) + diag(-1*ones(5,1),1) ...
     + rot90(diag([-1 -1 0 0 -1 -1]));
b = [1 0 1 1 0 1]';
% Look at the matrix A: it is ...

% 2) ...
D      = diag(diag(A));
L      = tril(A,-1);
U      = triu(A,1);
Bj     = -D\(L+U);
rhoBj  = max(abs(eig(Bj)))
Bgs    = -(D+L)\U;
rhoBgs = max(abs(eig(Bgs)))

% 3) ...

%% EX H4.4
% ...
%% EX H4.5
% ...