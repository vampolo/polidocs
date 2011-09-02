function [A,b,X] = GaussElimPiv(A,b)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Gaussian Elimination Algorithm with partial pivoting
%
%       [A,b,X] = GaussElimPiv(A,b)
%
% Input  : A = system coefficient matrix
%          b = right-hand side vector
% Output : A = obtained upper triangle matrix
%          b = obtained right-hand side vector
%          X = solution vector
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
n = length(b);
clc
disp(' System Matrix and Rigth-hand side');
[A,b]
disp(' ');
disp('Press any key to continue..');
disp(' ');
pause
clc

% elimination toward an upper triangle matrix
for i = 1:n-1
clc
    aa = abs(A(i,i));
    p = i;
    for j = i+1:n
        if abs(A(j,i)) > aa
            aa = abs(A(j,i));
            p=j;
        end
    end
    if p > i % swap p and i rows
        
        tmpa = A(i,i:n);
        A(i,i:n) = A(p,i:n);
        A(p,i:n) = tmpa;
        tmpb = b(i);
        b(i) = b(p);
        b(p) = tmpb;
        disp(' System Matrix and Rigth-hand side');
        [A,b]
        disp(' ');
        disp(sprintf('rows %d and %d swapped.',i,p));
        disp(' ');
        pause
        clc
    end
    for j = i+1:n
        m = A(j,i)/A(i,i);
        A(j,1:n) = A(j,1:n) - m*A(i,1:n);
        b(j) = b(j) - m*b(i);
        disp(' System Matrix and Rigth-hand side');
        [A,b]
        disp(' ');
        disp('Press any key to continue..');
        disp(' ');
        pause
        clc
    end
end
% back-substitution
X = b;
disp('Back Substitution: We get the solution vector x. ');
for k = n:-1:1
    X(k) = X(k)/A(k,k);
    disp(sprintf('x%d = %d',k,X(k)));
    X(1:k-1) = X(1:k-1) - A(1:k-1,k)*X(k);
end