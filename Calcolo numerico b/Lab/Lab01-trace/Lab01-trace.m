%% LAB - 01 - 04/10/2010
%% INTRODUCTION to MATLAB
% Examples and help command
%% INTRODUCTORY EXAMPLES (1)
format long
a=4/3
b=a-1
c=3*b
e=1-c
%% INTRODUCTORY EXAMPLES (2a)
x=0;
while x~=1
    x=x+1/16
    [x,sqrt(x)]
end
%% INTRODUCTORY EXAMPLES (2b)
x=0;
while x~=1
    x=x+0.1
    [x,sqrt(x)]
end
%% IEEE STD 754 - Examples (1)
% SINGLE PRECISION
% Set the desired number of bits
clc, clear all
k = 32;  % "single precision"
w = 8;   % "single precision"
% Convert the decimal number 12.25, using MATLAB commands, we get
n = 12.25
rb = num2bin(quantizer([k w],'float','round'),n)
%  Back to decimal (check)
rd = bin2num(quantizer([k w],'float','round'),rb)

%  We can check by hand:
% Binary encoding of 12.25 obtained before
% "0 10000010 10001000000000000000000"
% "s    e               f            "

s    = 0;
bias = -(2^(w-1)-1)
e    = (2^7+2)             % "10000010"
E    = e + bias
f    = 1*2^(-1) + 1*2^(-5) % ".10001000000000000000000"
rd1  = (-1)^s*2^E*(1 + f)
%% FORMAT
help format
format long
format short 
format rat
format hex
format
%% EX.1 - MACHINE EPSILON

% 1) routine
k = 0;
EPS = 1/2;
while (1+EPS) > 1
    EPS = EPS/2;
    k=k+1;
end
k
disp(sprintf('From definition machine epsilon results %d',2*EPS))

% 2) Cleve Moler's Sequence
format long
a=4/3
b=a-1
c=b+b+b
e=1-c
% Comments on error propagations...
% fl(4/3) = (4/3)*(1+e)
% ...

% 3) eps command
eps

%% EX.2 - FLOATING-POINT
clc, clear all
k = 6;     % storage bits
w = 3;     % exponent bits
t = k-w-1; % trailing bits

% 1) The number of elements that can be represented:
L = 2-2^(w-1)
U = 2^(w-1) - 1
bias = -(2^(w-1)-1) % = 3 = "011"
Card = 2^(t)*(U-L+1) + 1
err = 2^(-t) % error for rounding

% 2) Numbers: 2,1.6,-1,-14.01
q = quantizer([k w],'float','round')

num2bin(q,2)       % "0 100 00"
num2bin(q,1.6)     % "0 011 10"
num2bin(q,-1)      % "1 011 00"
num2bin(q,-14.01)  % "1 111 00" (-Inf)

% 3) Double precision
num2bin(quantizer('double'),[2 1.6 -1 -14.01])

% 4) Hexadecimal conversion
num2hex([2 1.6 -1 -14.01])

% 5) Conversion to single
format long
d = [2 1.6 -1 -14.01]
s = single(d)

%% Numerical Cancellation - An example
a = 9.901020304e-9;
b = 9.0990055e-9;
c = 1.8500185e8;
s1 = (a+b) + c;
s2 = a + (b+c);
e = abs(s1-s2)

%% EX.3 LOSS-OF-SIGNIFICANCE ERROR - An example

% 1) ...Taylor expansion gives: 1+1/2*x+1/6*x^2+1/24*x^3+1/120*x^4 + Err
syms x
f = (exp(x)-1)/x
pretty(f)
ezplot(f,[0,1])
ftaylor5 = taylor(f,0)

% 2)
format long
k = [1:30]';
x = 2.^(-k);
fs  = (exp(single(x))-1)./single(x);
fd  = (exp(x)-1)./x;
ft5 = 1+1/2*x+1/6*x.^2+1/24*x.^3+1/120*x.^4;

disp(' ')
disp('       k           x        Taylor     single f(x)   ')
disp(' ')
disp([k x ft5 fs])
disp(' ')
disp('          k                  x                 Taylor              double f(x)')
disp(' ')
disp([k x ft5 fd])
disp(' ')
disp('       k           x        Taylor    single f(x)  double f(x)   ')
disp(' ')
disp([k x ft5 fs fd])

% In single precision results the error increases dramatically
% for x < 2^(-12) = 1/4096, which is not such a small number; a second
% threshold is the reached at around 2^(-24).
% Double precision arithmetic only defers the onset and lessens the
% severity of the error, but does not eliminate it entirely!
% For k=30 -> err = 4.6566e-9 ...

% Comments: start checking exp(x) through Taylor expansion in single
% precision representation, before subtracting 1 at the numerator.
for kk=1:25
disp(sprintf('k = %d  -> %s\n',kk,num2bin(quantizer('single'),1 + (2^(-kk))+1/2*2^(-kk)^2)))
pause
end
%...other comments

% 3) ...

% 4) For n terms in the expansion, we get an approximate error on the
% evaluation of f(x):
% Err = 1/(n+1)! * x^(n) * exp(xi), with xi in (0,x)

format long
for n = 1:15
  n
  Err = 1/factorial(n+1) * (0.5)^(n)*exp(0.5)
end
%% HOMEWORK
% Hints
% H1.1.1 -> Taylor's expansion
% H1.1.2 -> Multiply numerator and denominator for...