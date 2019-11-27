<?php
/*******************************************************
 * Copyright (C) 2019 Faisal Al Isfahani <177006047@student.unsil.ac.id>
 * 
 * This file is part of Information Security Assesment.
 * 
 * permission of Faisal Al Isfahani
 *******************************************************/


include("operators.php");
include("stego.php");
include("extract.php");



if (!empty($_POST['secret'])) {
	$extension = strtolower(substr($_FILES['maskfile']['name'], -3));
	if ($extension == "jpg") {
		$plaintext = $_POST['secret'];;
		$base64 = base64_encode($plaintext);
		steg_hide($_FILES['maskfile'], $base64);
	} else {
		$result = "Only .jpg mask files are supported";
		echo $result;
	}
}
?>


<html>

<head>

	<title>Kripto Stegano</title>
	<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
	<link rel="stylesheet" href="css/AdminLTE.min.css">
	<link rel="stylesheet" href="css/bootstrap.min.css">
</head>

<body class="hold-transition login-page" style="background:#6fb98f">
	<div class="col-xs-12" style="margin-bottom:20px;">
	</div>
	<div class="col-xs-12 col-sm-8 col-sm-offset-2 col-md-6 col-md-offset-3" style="background:white; margin-bottom:50px;">

		<!-- /.login-logo -->
		<div class="login-box-body">
			<div class="alert alert-success" role="alert">
				<h1 class="alert-heading" align="center">Stegano LSB</h1>
			</div>
			<div class="nav-tabs-custom">
				<?php
				if (!empty($_FILES['gambar']['tmp_name'])) {
					$result = steg_recover($_FILES['gambar']);
					$base64 = base64_decode($result);

					
					echo "
					<div class='col-md-6 col-sm-6 col-xs-12'>
						<div class='info-box'>
							<span class='info-box-icon bg-aqua'><i class='fa fa-envelope-o' style='padding-top:20px'></i></span>

							<div class='info-box-content'>
								<span class='info-box-text'>Base 64</span>
								<span class='info-box-number'>$result</span>
							</div>
							<!-- /.info-box-content -->
						</div>
					</div>
					<div class='col-md-6 col-sm-6 col-xs-12'>
						<div class='info-box'>
							<span class='info-box-icon bg-yellow'><i class='fa fa-files-o' style='padding-top:20px'></i></span>

							<div class='info-box-content'>
								<span class='info-box-text'>Plaintext</span>
								<span class='info-box-number'>$base64</span>
							</div>
						</div>
					</div>

	";
				}
				?>
				<ul class="nav nav-tabs">
					<li class="active" style="border-top-color:#004445"><a href="#tab_2" class="btn btn-app" data-toggle="tab"><i class="fa fa-lock" style="color:#004445"></i> Embed</a></li>
					<li style="border-top-color:#004445"><a href="#tab_1" class="btn btn-app" data-toggle="tab"><i class="fa fa-unlock" style="color:#004445"></i> Ekstrak</a> </li>

				</ul>
				<div class="tab-content">
					<div class="tab-pane" id="tab_1">
						<h2 align=center style="color:#004445">Ekstrak Message</h2>
						<form action="<?php $_SERVER['PHP_SELF'] ?>" method="post" enctype="multipart/form-data">

							<label style="color:#004445">Stego Image </label>
							<div class="input-group" style="margin-bottom:30px">
								<span class="input-group-addon"><i class="fa fa-image"></i></span>
								<input type="file" class="form-control" accept="image/*" name="gambar" id="gambar">
							</div>
							<div class="row">
								<div class="col-xs-12">
									<button type="submit" class="btn btn-success btn-flat pull-right">Ekstrak</i></button>
								</div>
							</div>
						</form>
					</div>
					<!-- /.tab-pane -->
					
					<div class="tab-pane active" id="tab_2">
						<h2 align=center style="color:#004445">Embed Message</h2>
						<form id="form_stegano" action="<?php $_SERVER['PHP_SELF'] ?>" method="post" enctype="multipart/form-data">
							<div class="form-group has-feedback">
							</div>
							<div class="form-group has-feedback">
								<textarea id="secret" name="secret" class="form-control" rows=3 placeholder="Masukan Pesan Rahasia"></textarea>
								<span class="fa fa-file-text-o form-control-feedback"></span>
							</div>
							<label style="color:#004445">Cover Image (.jpg) </label>
							<div class="input-group" style="margin-bottom:30px">
								<span class="input-group-addon"><i class="fa fa-image"></i></span>
								<input type="file" class="form-control" accept="image/jpeg" name="maskfile">
							</div>
							<div class="row">
								<!-- /.col -->
								<div class="col-xs-12">
									<button type="submit" class="btn btn-success btn-flat pull-right">Embed</button>
								</div>
								<!-- /.col -->
							</div>
						</form>
					</div>
					<!-- /.tab-pane -->
				</div>
				<!-- /.tab-content -->

			</div>
		</div>

	</div>
	<!-- /.login-box -->
	<!-- jQuery 2.2.3 -->
	<script src="js/jquery-2.2.3.min.js"></script>
	<!-- Bootstrap 3.3.6 -->
	<script src="js/bootstrap.min.js"></script>

</body>

</html>