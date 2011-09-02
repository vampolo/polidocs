%% LAB - 02 - 11/10/2010
%% Intro
help colon
help times
help power
help plot

%% EX.1

% 1) Evaluation of the conditioning of f(q)
clear all, close all
clc
syms q
f = 1/q*(1-(1-2*q)^(-3/2))
pretty(f)
fp = diff(f)
pretty(fp)
Condf = q*fp/f
pretty(Condf)
Condf = simplify(Condf)
limit(Condf,q,0) % limit of the conditioning for q toward 0 
limit(f,q,0)     % limit of the function f(q) for q toward 0 
% We get 0 -> Errors on q are not amplified. Let's verify:
format long
q = logspace(-8,-20,7)' % vector containing decreasing q values toward 0
% for i =1:length(q)
%     ff(i) = (1-(1-2*q(i)).^(-3/2))/q(i);
% end
ff = (1-(1-2*q).^(-3/2))./q;
disp(' ')
disp('       q                    f(q)')
disp(' ')
disp([q ff])

% The experimental results are in contrast with the limit of the function
% f(q) for q toward 0...Comments...

% 2) Taylor's expansion
syms q f
f = 1/q*(1-(1-2*q)^(-3/2));
ftaylor = taylor(f,q,0,3) % all terms have the same sign...
format long
q = logspace(-8,-20,7)'; % vector containing decreasing q values toward 0

ft = - 3 - (15*q)/2 - (35*q.^2)/2;

disp(' ')
disp('          q                 Taylor')
disp(' ')
disp([q ft])

%% EX.2

% 1) Starting from I0
format long
I(1) = log(6/5);                   % n  = 0
for nn = 2:51
    I(nn) = 1/(nn-1) - 5*I(nn-1);  % nn = n+1
end
disp(sprintf('With the proposed recursive relation I(50) = %d\n',I(end)))
figure
plot(0:50,I,'o-r')
% semilogy(0:50,abs(I))
xlabel('n')
ylabel('I(n)')
% comments on error propagation In,calc = 1/n - 5*In-1,calc
% In,calc = In,true *[1+e]
% ...

% 2) Inverse recurrency: In-1 = 1/5*(1/n  - In)
% Now the algorithm is stable, error decreases at each step...verify
% Let's choose a starting n, for example n = 1000 (large enough)
n_start = 1000;
i = 1/(6*n_start); % approximation
for n = n_start-1:-1:50
    i = 1/5*(1/n-i);
end
disp(sprintf('With the inverse recursive relation I(50) = %d\n',i))

%% EX.3

% 1) Calculating...
clear all
clc
x(1) = 1;     % x0 fixed at 1
for n = 0:70  % x1...x71
    x(n+2) = 2^(n+1)*(sqrt(1+x(n+1)/2^n)-1);
end
x(72)   % x71, different from the theoretical value
figure(2)
plot(0:71,x,'o-b')
% This bad result is due to the numerical cancellation effect. The first n
% value for which the sequence results zero can be calculated as (try by
% hand):
x0=1;
n = log(log(1+x0)/eps)/log(2)+1

% consistently with what observed...

% 2) Equivalently:
clc, clear all
x(1) = 1;     % x0 fixed at 1
for n = 0:70  % x1...x71
    x(n+2) = 2*x(n+1)/(sqrt(1+x(n+1)/2^n)+1);
end
x(end)
x(end) - log(2)   % very near to the theoretical limit log(2) [eps]...
figure(1)
plot(0:71,x,'o-b','Linewidth',2)
hold on
plot([0 80],log(2)*ones(1,2),'--r','Linewidth',2)
% The convergence is very rapid; (1 + xn/2^n) approximately 1 for n >= 52.

%% EX.4

% 1) alfa = 1 ... integral <= 1/8
% 2) Symbolic Tool
clc, clear all
syms x
f = x^40/(x+8);
I = int(f,0,1);
If = double(I)

% 3) Applying the Fundamental Theorem of Integral Calculus...
F = int(f,x) % Primitive function...
pretty(F)
simplify(diff(F,x))
I = double(subs(F,x,1)-subs(F,x,0))

% 4) Recursive formula for alpha = 40
A(1) = log(9/8);
for n = 1:40
    A(n+1) = -8*A(n) + 1/n;
end
A(41)  % A40

% 5) The recursive formula can also be expresses as:
% An-1 = -1/8*An + 1/(8*n)  n = 80,...,40
% A80  = fixed value
% We can use for A80 the overestimation of the integral obtained before...
A(81) = 1/8;  % A80 from 1)
for n = 80:-1:40
    A(n) = -1/8*A(n+1) + 1/(8*n);
end
format long
A(41)  % A40

% In order to verify the result we can use the fuction quadl
Im = quadl('x.^40./(x+8)',0,1,1e-16)

%% EX.5
% format rat

A1 = [1 2 1; 3 4 0; 2 10 4] , b1 = [3 3 10]';
rank(A1)
[A b X] = GaussElim(A1,b1);

A2 = [1 2 1; 2 4 3; -1 -3 0] , b2 = [0 3 2]';
rank(A2)
[A b X] = GaussElim(A2,b2);
[A b X] = GaussElimPiv(A2,b2);

% in the second case pivoting is necessary...

%% HOMEWORK
%% EX H2.1
% 1) Implementation of the relations
% -- a
clc, clear all, close all
p(1)=1;
p(2)=1/3;
for i=2:100
    p(i+1) = 100/3*p(i)-p(i-1);
end
figure(1)
subplot(1,2,1)
plot(1:101,p)
title('p_n = 10/3*p_{n-1} - p_{n-2}')
xlabel('n')
% -- b
pp(1)=1;
for i=1:100
    pp(i+1)=1/3*pp(i);
end
subplot(1,2,2)
plot(1:101,pp)
title('p_n = 1/3*p_{n-1}')
xlabel('n')

%% EX H2.2
%...
%% EX H2.3
help chol