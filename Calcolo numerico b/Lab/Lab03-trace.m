%% LAB - 03 - 18/10/2010
%% Intro
% Indexing of elements in given matrix.
help mldivide
help lu
help chol
help det
help rank
%% EX.1
% 1) L1 and L1*A*L1'
clear all, close all
clc
A = [1 3 0; 3 11 4; 0 4 9]
% Construction of matrix L1
L1 = [1 0 0; -A(2,1)/A(1,1) 1 0; -A(3,1)/A(1,1) 0 1]
L1*A

A1 = L1*A*L1'
% Due to the symmetry of the matrix,...

% 2) In order to make A triangular, starting from A1, we have to remove the
% element in position(4,2) through L2:
L2 = eye(3);
L2(3,2) = -A1(3,2)/A1(2,2)
L2*A1
% thus, due to A symmetry, a diagonal matrix through L2*A1*L2' is obtained:
D = L2*A1*L2'

% 3) Lj^-1 = 2I - Lj. L results (L2*L1)^-1 = L1^-1 * L2^-1. L can also
% be expressed as L = 3I - L1 - L2, without performing any matrix
% inversion:
L = 3*eye(3) - L1 - L2
% we check:
A - L*D*L'

%% EX.2
help lu
% 1) Matrix creation (A is symmetryc!) and LU decomposition 
clear all, clc
A = pascal(4)
[L,U,P] = lu(A)
% In order to obtain D and Us such that P*A = L*U = L*D*Us: D*Us = U ...
D = diag(diag(U))  % construction of the diagonal matrix.
Us = D\U           % calculation of signed U = D^-1 * U
% Even if A is symmetric, the obtained decomposition is not of the form A =
% L*D*L', with L a lower triangular matrix with unitary main diagonal and D
% a diagonal matrix. In fact, Us is not the transpose of L. lu command
% uses pivoting...
L*D*Us
A

% 2) Matrix creation and LU decomposition WITHOUT PIVOTING
A = pascal(4);
% Note that lu command allows to perform LU decomposition without pivoting 
% only for sparse matrices (see help sparse...).
% Conversion of matrix to sparse form and forcing of the diagonal pivoting
% in the call of LU command: :
A = sparse(A);
[Ltilde,U] = lu(A,0); % 0 for no pivoting
% Back to full storage organization:
Ltilde = full(Ltilde)
U = full(U)
% Note that L1 = U':
Ltilde-U'
% Verification of A = Ltilde * D * Ltilde', with D now the identity matrix
U'*U     % equivalent to Ltilde * Ltilde'
full(A)
D = eye(4), Ltilde*D*Ltilde'

% 3) A is symmetryc. Verify that A is positive-definite:
% - by hand...
% - by using the chol command for Cholesky Decomposition (A is symmetryc!):
A = pascal(4);
W = chol(A)
% the obtained matrix is the same of U obtained before (peculiarity of
% Pascal matrices and all matrices with unitary principal minors).

%% EX.3
% 1) LU decomposition exists if all the principal minors are different from
% zero. Try by hand. We can also check by means of the symbolic tool in
% MATLAB:
clear all, clc
syms A alpha beta
A = [1 1 1 1; alpha 4 2 1; 27 9 beta 1; 1e-4 16 4 1e+4]
A1 = A(1,1)
A2 = det(A(1:2,1:2))
solve(det(A2),alpha)
A3 = det(A(1:3,1:3))
solve(det(A3),beta)
pretty(ans)
A4 = det(A)
pretty(solve(det(A4),beta))

% 2) Let's check that, for alpha = 1 and Beta = 21, all the principal minors
% are different from zero (LU decomposition exists).
A = [1 1 1 1; 1 4 2 1; 27 9 21 1; 1e-4 16 4 1e+4]
% For the chosen values A3 = 0. In fact, given alpha = 1, we have:
betaA3null = subs(solve(det(A3),beta),alpha,1)
% The problem can be overcome by swapping 3rd and 4th rows.
% Two different ways:
% 1st way
P = [1 0 0 0; 0 1 0 0; 0 0 0 1; 0 0 1 0];
As = P*A
% 2nd way, that gives the same result:
Ap = A([1 2 4 3],:)
% The new matrix should have all the principal minors different from zero.
% Let's check:
for i = 1:size(As,1)
    Min(i) = det(As(1:i,1:i));
end
Min'

% 3) We can solve the system by the following series of istructions:
b = A*ones(4,1);
[L,U,P] = lu(A);
y = L\P*b;
% Thus
x = U\y;
format long
x

% 4) Relate the obtained error on the solution vector with the condition
% number estimation of matrix A...
err = norm(abs(x-ones(4,1)))   % norm of the error respect to [1 1 1 1]'
% From the conditioning we can lose up to approximately
round(abs(log10(cond(A)))) 
% (decimal) digits of accuracy ( -> see next exercises).

%% EX.4
% 1) Estimation of Cond(Hn). Let's construct the righ-hand side as:
% b = Hn*ones(n,1)
% db of the order of 1e-3
clc
clear all
n = [10 20 40]';

for k = 1:length(n)
    H = hilb(n(k));
    x = ones(n(k),1);
    b = H*ones(n(k),1);
    db = rand(n(k),1)*1e-3;
    B = b + db;
    X = H\B;
    dx = X -x;
    c(k) = (norm(dx)*norm(b))/(norm(x)*norm(db));
end
c'
semilogy(n,c,'o-')

% 2) Estimation of Cond(Hn) by means of svd [SINGULAR VALUE DECOMPOSITION]
% command. Let's try with n = 10.
[U,D,V] = svd(hilb(10));
% reduce the diagonal matrix D to a column vector:
v = diag(D)
CondH10svd = v(1)/v(end)
% 3) Hilbert matrix is symmetryc and positive definite. It can be shown
% to have the singular values that coincide with the eigenvalues
lambda = eig(hilb(10));
CondH10eig = max(lambda)/min(lambda)
% comment on accuracy...differences between eig and svd algorithms (see the
% related helps): ...
eps*CondH10eig
% we can lose up approximately to abs(log10(CondH10eig)) significant dec.
% digits ...

% 4) Using cond matlab command cond (read its help!), we obtain:
cond(hilb(10))
cond(hilb(20))
cond(hilb(40))

% The estimations obtained at the first point are lower: the condition
% number represents the maximum possible amplification of the relative
% error on data...

% 5) 
clear all, clc
nn = 1:13;
for i = nn
    h = hilb(i);
    xx = ones(i,1);
    b = h*xx;
    x = h\b;
    c(i) = cond(h,Inf);
    err(i) = norm(x-xx,inf);
end
semilogy(nn,c,'ob-')
hold on
semilogy(nn,err,'or-')
xlabel('n'),ylabel('Cond(H_n), err')
% for n > ... the solution results completely wrong...
% The difference in the semilog plot between c and err is almost constant
% and approximately equal to 16, the number of digits of accuracy in
% MATLAB: this means that the number of digits of accuracy lost relative
% to machine accuracy is equal to the logarithm of the condition number.

%% EX.5
% 1) Matrix definition and condition number calculation in MATLAB:
A = ones(5,5);
A(1,1) = 1.001; A(3,2) = 1.001; A(2,3) = 1.001; A(5,5) = 1.001;
A
format long
cond(A)

% 2) The matrix is symmetric: its condition number in 2-norm results:
CondA = max(abs(eig(A)))/min(abs(eig(A)))
% the obtained value approximately coincides with the previous one:
% comment...

% 3) For example:
x = ones(5,1);
b = A*x;
% Applying only a small perturbation on the last element of b:
db = zeros(5,1); db(5) = 1e-4; 
B = b + db;
X = A\B;
dx = X-x;
c = (norm(dx)*norm(b))/(norm(x)*norm(db))

%% HOMEWORK
%% EX H3.1
% 1) Partial solution:
D = ones(1,10)*2;
d = ones(1,9);
An10 = diag(D) + diag(d,1) + diag(d,-1)
% To verify the symmetry, for example
all(all(An10'-An10,1) == 0)
%
Wchol = chol(An10)

% 2) ...

% 3) ...
%% EX H3.2
% ...
%% EX H3.3
% try also by hand...