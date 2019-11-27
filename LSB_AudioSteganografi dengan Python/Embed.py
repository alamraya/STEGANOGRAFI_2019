import wave
# Membaca Audio Bit
song = wave.open("song.wav", mode='rb')
# Merubah audio file ke bit
frame_bytes = bytearray(list(song.readframes(song.getnframes())))


# Pesan Rahasia
string='KIFUNSIL'
string = string + int((len(frame_bytes)-(len(string)*8*8))/8) *'#'
# Konversi Text ke Biner
bits = list(map(int, ''.join([bin(ord(i)).lstrip('0b').rjust(8,'0') for i in string])))

# Merubah tiap bit pada audio dengan tiap bit pada text
for i, bit in enumerate(bits):
    frame_bytes[i] = (frame_bytes[i] & 254) | bit
# Mendapatkan bit
frame_modified = bytes(frame_bytes)

# Membuat bit pada audio
with wave.open('song_embedded.wav', 'wb') as fd:
    fd.setparams(song.getparams())
    fd.writeframes(frame_modified)
song.close()
