import wave
audio = wave.open("song_embedded.wav", mode='rb')

# Convert audio ke byte array
frame_bytes = bytearray(list(audio.readframes(audio.getnframes())))

# Mengekstrak LSB dari setiap byte
extracted = [frame_bytes[i] & 1 for i in range(len(frame_bytes))]

# Kembali konversikan LSB ke string pesan
pesan = "".join(chr(
    int("".join(map(str, extracted[i:i+8])), 2)) for i in range(0, len(extracted), 8))

# Menghilangkan karakter padding
ekstraksi = pesan.split("###")[0]

# Menampilkan string hasil ekstraksi
print("Ekstraksi berhasil: "+ekstraksi)
audio.close()
