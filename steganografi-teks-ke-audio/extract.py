import wave
audio = wave.open("song_embedded.wav", mode='rb')

# Convert audio ke byte array
frame_bytes = bytearray(list(audio.readframes(audio.getnframes())))

# Mengekstrak LSB dari setiap byte
extracted = [frame_bytes[i] & 1 for i in range(len(frame_bytes))]