<?php
$result = "";
if (isset($_POST['submit'])) {
    include_once("encrypt.php");
    include_once("upload.php");
    $message = $_POST['text'];
    $namaFile = upload($_FILES['berkas']);
    $result = encrypt($namaFile, $message);
}

?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Stegano EOF</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-6">

                <ul class="nav nav-tabs" id="myTab" role="tablist">
                    <li class="nav-item">
                        <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Embed</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Ekstract</a>
                    </li>
                </ul>

                <div class="tab-content" id="myTabContent">

                    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                        <h3>STEGANO METODE END OF FILE</h3>
                        <div class="card">
                            <div class="card-body">
                                <form action="" method="POST" enctype="multipart/form-data">
                                    <div class=" form-group">
                                        <label for="text">Masukan pesan yang akan disisipkan</label>
                                        <input type="text" class="form-control" id="text" name="text">
                                        <div class="form-group">
                                            <label for="exampleFormControlFile1">Pilih gambar untuk di embed</label>
                                            <input type="file" class="form-control-file" id="berkas" name="berkas">
                                        </div>
                                        <button type="submit" class="btn btn-primary mb-3" style="width:100%" name="submit">Embed</button>
                                        <label for="email">hasil embed</label>
                                        <div class="card">
                                            <div class="card-body">
                                                <?php
                                                if ($result != '') {
                                                    echo "<a href='download.php?file=$result'>Download File</a>";
                                                }
                                                ?>
                                            </div>
                                        </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                    <div class="card">
                        <div class="card-body">
                            <form id="extract" type="post" enctype="multipart/form-data">
                                <div class="form-group">
                                    <label for="exampleFormControlFile1">Pilih gambar untuk di Ekstract</label>
                                    <input type="file" class="form-control-file" id="extractImage" name="berkasembed">
                                </div>
                                <button type="submit" class="btn btn-primary mb-3" style="width:100%" name="submit">Ekstract</button>
                            </form>
                            <label for="email">hasil pesan yang telah di ekstract</label>
                            <div class="card">
                                <div class="card-body">
                                    <span id="result_extract"></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

    <script>
        $(document).ready(function() {
            $('#extract').submit(function(e) {
                e.preventDefault();
                var form_data = new FormData(this);
                console.log(form_data);
                $.ajax({
                    url: "decrypt.php",
                    type: "POST",
                    dataType: "json",
                    contentType: false,
                    cache: false,
                    processData: false,
                    data: form_data,
                    success: function(result) {
                        $('#result_extract').text(result.result);
                    }
                })
                return false;
            })
        })
    </script>
</body>

</html>