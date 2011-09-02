%% EX. "A case study: Image compression"
clc
close all
clear all
% Let's import the image
A = importdata('Sunset.jpg');
% Look at its size (three planes: red, green, blue):
size(A)
figure(1)
imshow(A)
% Class conversion: uint8 -> double
% With color separation:
red   = double(A(:,:,1));
green = double(A(:,:,2));
blue  = double(A(:,:,3));

% 1) Singular Value Decomposition:
% A = 1/3*(double(A(:,:,1)) + double(A(:,:,2)) + double(A(:,:,3)));
% [U,S,V] = svd(A);

% We can also apply  SVD on each plane
[ur,sr,vr] = svd(red);
im_red = (ur * sr * vr');
[ug,sg,vg] = svd(green);
im_green = (ug * sg * vg');
[ub,sb,vb] = svd(blue);
im_blue = (ub * sb * vb');
% Reconstruction of the image and Class convertion: double -> uint8
im(:,:,1) = uint8(im_red);
im(:,:,2) = uint8(im_green);
im(:,:,3) = uint8(im_blue);
size(im)

figure(2), set(2, 'Position', [150 215 560 420])
title(sprintf('SVD with all terms'))
imshow(im)  

% 2) Step-by-step reconstruction
figure(3), set(3, 'Position', [724 215 560 420])
for ii=1:5:100
    clf
    im(:,:,1) = uint8(ur(:,1:ii) * sr(1:ii,1:ii) * vr(:,1:ii)');
    im(:,:,2) = uint8(ug(:,1:ii) * sg(1:ii,1:ii) * vg(:,1:ii)');
    im(:,:,3) = uint8(ub(:,1:ii) * sb(1:ii,1:ii) * vb(:,1:ii)');
    imshow(im)
    title(sprintf('SVD with %d terms',ii))
    pause, ii
    clear im    
end

% 3) Take a look to the structure of the matrices:
close all
figure
subplot(1,2,1)
spy(sr)
subplot(1,2,2)
semilogy(diag(sr),'.') % semilogarithmic plot of the singular values of red matrix