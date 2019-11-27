<?php
include_once("upload.php");

function decrypt($file)
{
  include('functions.php');

  $src = 'terupload/' . $file; //mengubah gambar untuk di decrypt
  $img = imagecreatefrompng($src); //identifikasi gambar
  $real_message = ''; //Variabel kosong untuk menyimpan pesan

  $count = 0; //Memeriksa char terakhir
  $pixelX = 0; //pixel x coordinates
  $pixelY = 0; //pixel y coordinates

  list($width, $height, $type, $attr) = getimagesize($src); //ukuran gambar

  for ($x = 0; $x < ($width * $height); $x++) { //mengulangi pixel demi pixel
    if ($pixelX === $width + 1) { //apabila nilai true, maka akan mencapai akhir baris piksel, dan mulai baris berikutnya
      $pixelY++;
      $pixelX = 0;
    }

    if ($pixelY === $height && $pixelX === $width) { //Memeriksa apakah telah mencapai EOF
      echo ('Max Reached');
      die();
    }

    $rgb = imagecolorat($img, $pixelX, $pixelY); //Warna piksel pada posisi x dan y
    $r = ($rgb >> 16) & 0xFF; //Mengembalikan nilai merah
    $g = ($rgb >> 8) & 0xFF; //^^ Hijau
    $b = $rgb & 0xFF; //^^ Blue

    $blue = toBin($b); //Ubah warna biru menjadi biner

    $real_message .= $blue[strlen($blue) - 1]; //Ad the lsb to our binary result

    $count++; //menghitung bahwa satu digit telah ditambahkan

    if ($count == 8) { //Setiap kali menekan 8 angka baru, periksa nilainya
      if (toString(substr($real_message, -8)) === '|') { //Berapa nilai 8 digit terakhir?
        // echo ('done<br>'); //Yes we're done now
        $real_message = toString(substr($real_message, 0, -8)); //mengkonfersi dari string and hapus /
        // echo ('Result: ');
        return $real_message; //Show
        die;
      }
      $count = 0; //reset perhitungan
    }

    $pixelX++; //ubah koordinat x pada berikutnya
  }
}


if (isset($_FILES['berkasembed'])) {
  $filename = upload($_FILES['berkasembed']);
  $result = decrypt($filename);
  $hasil = array("result" => $result);
  echo json_encode($hasil);
}
