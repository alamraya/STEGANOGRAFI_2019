# Kelompok
1. Imam kharits		177006030
2. Rifaldi adi putra	177006041
3. Rifki mubarok	177006078
4. Helmi faisal lutfi	177006086

# StegaPhoto

#### End Of File Markers

  End of File (EOF) penanda atau potongan menentukan akhir dari aliran data file. Seharusnya tidak ada  konten setelah penanda EOF dan sehingga setiap data yang ditempatkan setelah penanda akan diabaikan oleh perangkat lunak parsing file.
  Penanda EOF untuk jenis berkas gambar adalah sebagai berikut:

  * PNG: `AE426082`
  * JPEG: `FFD9`
  * GIF: `3B`

## 1. Implementation

#### 1.1. Hiding the Files

The files are hidden inside the image through a series of steps:

  1. Pertama gambar dibaca di StegaPhoto, semua file dibaca sebagai buffer array menggunakna [API FileReader](https://developer.mozilla.org/en-US/docs/Web/API/FileReader). Gambar kemudian diubah menjadi [Uint8Array](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Uint8Array) (sebuah array dari 8 bit unsigned integer).

  2. File tersebut kemudian diarsipkan menggunakan [JSZip](https://stuk.github.io/jszip/), dengan pilihan compression. File Zip yang dihasilkan akan disembunyikan di dalam gambar.

  3. -	Berikutnya enkripsi ZIP (file didalam zip). Enkripsi menggunakan  [SubtleCrypto API](https://developer.mozilla.org/en-US/docs/Web/API/SubtleCrypto) dan memebutuhkan beberapa langkah:

      1. Kunci derivasi di-hash menggunakna **SHA-256** dan salt. Salt adalah Uint8Array diambil dari awal gambar (hal ini untuk mencegah serangan Parssword Dictionary).

      2. Kunci baru kemudian diturunkan menggunakan algoritma **PBKDF2**.

  4. Setelah mengenkripsi ZIP file, hasilnya dikonversi ke Uint8Array lain. Gambar dan ZIP dienkripsi kemudian concatenated, untuk menempatkan file setelah EOF marker

      ```
         Gambar Original
      ╭────────────┸────────────╮
      |   Konten Gambar   | EOF | ZIP Terenkripsi |
      ```

  5. Sekarang file telah disembunyikan didalam gambar, Akhirnya gambar di konversi ke Blob dan URL yang dibuat untuk mengunduh gambar yang dihasilkan.

#### 1.2. Menampilkan Files

Menampilkan file memiliki proses yang sama seperti menyembunyikan, dengan cara sebaliknya:
  1. Berkas dibaca menggunakam FileReader API dan dikonversi ke Uint8Array.

  2. EOF marker ditemukan dengan iterasi melalui array. File yang akan diambil segera mengikuti EOF  marker.

  3. Jika tidak ada konten mengikuti EOF marker, maka gambar tidak berisi file tersembunyi. Dekripsi ZIP tersembunyi menggunakan teknik yang sama seperti yang digunakan untuk enkripsi. Nmaun, kunci derivasi digunakan untuk memperoleh kunci dekripsi yang kemudian digunakan dengan algoritma AES-CTR untuk mendekripsi data.

  4. Setelah ZIP telah didekripsi, file kemudian dapat diekstrak menggunkana perpustakaan JSZip- ini harus persis sama dengan file asli, bit-for-bit.

  5. Meskipun file telah diekstrak dari ZIP, untuk kemudahan itu adalah ZIP yang ditawarkan kepada pengguna akhir untuk mengunduh.


