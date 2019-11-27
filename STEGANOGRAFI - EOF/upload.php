<?php
function upload($file)
{
    // ambil data file
    $namaFile = $file['name'];
    $namaSementara = $file['tmp_name'];

    // tentukan lokasi file akan dipindahkan
    $dirUpload = "terupload/";

    // pindahkan file
    $terupload = move_uploaded_file($namaSementara, $dirUpload . $namaFile);
    return $namaFile;
}
