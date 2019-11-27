import wave
song = wave.open("song_embedded.wav", mode='rb')
# Konversi Audio ke bit
frame_bytes = bytearray(list(song.readframes(song.getnframes())))

# Ekstaksi tiap bit
extracted = [frame_bytes[i] & 1 for i in range(len(frame_bytes))]
# Konversi kembali ke dalam string
string = "".join(chr(int("".join(map(str,extracted[i:i+8])),2)) for i in range(0,len(extracted),8))

decoded = string.split("###")[0]

# Menampilkan pesan (Plaintext)
print("Berhasil Diekstraksi! Pesan:  "+decoded)
song.close()
