import wave

# Membaca file wave audio
song = wave.open("song.wav", mode='rb')

# Membaca frame dan convert ke byte array
frame_bytes = bytearray(list(song.readframes(song.getnframes())))

# Pesan rahasia
pesan = 'Keamanan Informasi - Informatika Unsil Satu Ideologi Satu Solidaritas'

# Menambahkan data tambahan (padding) untuk mengisi seluruh byte audio yang tersisa.
pesan = pesan + int((len(frame_bytes)-(len(pesan)*8*8))/8) * '#'

# Convert pesan ke bit array
bits = list(
    map(int, ''.join([bin(ord(i)).lstrip('0b').rjust(8, '0') for i in pesan])))

    # Mengganti LSB setiap byte dari audio dengan bit dari bit array pesan
for i, bit in enumerate(bits):
    frame_bytes[i] = (frame_bytes[i] & 254) | bit

# Hasil byte yang sudah dimodifikasi
frame_modified = bytes(frame_bytes)

# Mengubah bytes hasil modifikasi ke file wave audio baru
with wave.open('song_embedded.wav', 'wb') as fd:
    fd.setparams(song.getparams())
    fd.writeframes(frame_modified)
song.close()
