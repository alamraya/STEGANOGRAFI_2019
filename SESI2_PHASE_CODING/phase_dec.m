function out = phase_dec(signal, L_msg, L)

if nargin < 3
	L = 1024;
end

m   = 8*L_msg;             % Length of bit sequence (for 8bit)
x   = signal(1:L,1);       % First segment
Phi = angle(fft(x));       % Phase angles of first segment

% Retrieving data back from phases of first segments
data = char(zeros(1,m));   % Empty data sequence
for k=1:m
	if Phi(L/2-m+k)>0
    	data(k)='0';
    else
        data(k)='1';
	end
end

bin = reshape(data(1:m), 8, m/8)';
out = char(bin2dec(bin))';
end