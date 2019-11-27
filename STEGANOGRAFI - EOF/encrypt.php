<?php
include_once('functions.php');


function encrypt($source, $message)
{
  //Variabel
  $msg = $message; // Enkripsinya
  $src = 'terupload/' . $source; // Format Gambar

  $msg .= '|'; // Tanda EOF , untuk menunjukkan decrypter di akhir pesan
  $msgBin = toBin($msg); //Convert pesan pada binary
  $msgLength = strlen($msgBin); //Panjang Pesan
  $img = imagecreatefromjpeg($src); //Identifikasi Gambar
  list($width, $height, $type, $attr) = getimagesize($src); //Ukuran Gambar

  if ($msgLength > ($width * $height)) { //Gambar memiliki bit lebih banyak daripada piksel dalam gambar
    //maka akan muncul pop up
    echo ('Message too long. This is not supported as of now.');
    die();
  }

  $pixelX = 0; //Koordinat piksel
  $pixelY = 0; //

  for ($x = 0; $x < $msgLength; $x++) { //Encrypt pesan bit by bit

    if ($pixelX === $width + 1) { //Jika nilai true, maka telah mencapai akhir baris piksel, dan mulai pd baris berikutnya
      $pixelY++;
      $pixelX = 0;
    }

    if ($pixelY === $height && $pixelX === $width) { //Memeriksa apakah telah mencapai EOF
      echo ('Max Reached');
      die();
    }

    $rgb = imagecolorat($img, $pixelX, $pixelY); //Warna piksel pada posisi x dan y
    $r = ($rgb >> 16) & 0xFF; //Kembali ke nilai merah
    $g = ($rgb >> 8) & 0xFF; //^^ hijau
    $b = $rgb & 0xFF; //^^ biru

    $newR = $r; //tidak mengubah warna merah atau hijau, hanya lsb biru
    $newG = $g; //^
    $newB = toBin($b); //Convert blue to binary
    $newB[strlen($newB) - 1] = $msgBin[$x]; //Ubah bit paling tidak signifikan dengan pesan bit from out
    $newB = toString($newB); //Konversi biru ke nilai integer

    $new_color = imagecolorallocate($img, $newR, $newG, $newB); //swap pixel dengan pixel baru yang lsb birunya berubah (terlihat sama)
    imagesetpixel($img, $pixelX, $pixelY, $new_color); //Atur warna pada posisi x dan y
    $pixelX++; //piksel berikutnya (horizontal)

  }
  $randomDigit = rand(1, 9999); //Digit acak untuk nama file
  imagepng($img, 'terembed/result' . $randomDigit . '.png'); //Create image
  // echo ('done: ' . 'result' . $randomDigit . '.png'); //image file name

  imagedestroy($img);
  return 'result' . $randomDigit . '.png';
}
