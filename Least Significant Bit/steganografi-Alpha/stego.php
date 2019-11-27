<?php
function steg_hide($maskfile, $hidefile)
{

	$binstream = "";
	$make_odd = Array();

	$pic = ImageCreateFromJPEG($maskfile['tmp_name']);
	$attributes = getImageSize($maskfile['tmp_name']);
	$outpic = ImageCreateFromJPEG($maskfile['tmp_name']);

	if(!$pic || !$outpic || !$attributes)
	{
		return "cannot create images - maybe GDlib not installed?";
	}

	$data = $hidefile;

	
	$boundary="";
	do
	{
		$boundary .= chr(rand(0,255)).chr(rand(0,255)).chr(rand(0,255));
	} while(strpos($data,$boundary)!==false && strpos('rahasia.txt',$boundary)!==false);

	$data = $boundary.'rahasia.txt'.$boundary.$data.$boundary;
	
	if(strlen($data)*8 > ($attributes[0]*$attributes[1])*3)
	{
		// remove images
		ImageDestroy($outpic);
		ImageDestroy($pic);
		return "Cannot fit ".'rahasia.txt'." in ".$maskfile['name'].".<br />"."rahasia.txt"." requires mask to contain at least ".(intval((strlen($data)*8)/3)+1)." pixels.<br />Maximum filesize that ".$maskfile['name']." can hide is ".intval((($attributes[0]*$attributes[1])*3)/8)." bytes";
	}

	

	// convert $data into array of true/false
	// pixels in mask are made odd if true, even if false
	for($i=0; $i<strlen($data) ; $i++)
	{
		$char = $data{$i};
		$binary = asc2bin($char);

		$binstream .= $binary;

		for($j=0 ; $j<strlen($binary) ; $j++)
		{
			$binpart = $binary{$j};
			if($binpart=="0")
			{
				$make_odd[] = true;
			} else {
				$make_odd[] = false;
			}
		}
	}

    $y=0;
	for($i=0,$x=0; $i<sizeof($make_odd) ; $i+=3,$x++)
	{
		// read RGB of pixel
		$rgb = ImageColorAt($pic, $x,$y);
		$cols = Array();
		$cols[] = ($rgb >> 16) & 0xFF;
		$cols[] = ($rgb >> 8) & 0xFF;
		$cols[] = $rgb & 0xFF;
		for($j=0 ; $j<sizeof($cols) ; $j++)
		{
			if($make_odd[$i+$j]===true && is_even($cols[$j]))
			{
				// is even, should be odd
				$cols[$j]++;
			} else if($make_odd[$i+$j]===false && !is_even($cols[$j])){
				// is odd, should be even
				$cols[$j]--;
			} // else colour is fine as is
		}
		// modify pixel
		$temp_col = ImageColorAllocate($outpic,$cols[0],$cols[1],$cols[2]);
		ImageSetPixel($outpic,$x,$y,$temp_col);
		// if at end of X, move down and start at x=0
		if($x==($attributes[0]-1))
		{
			$y++;
			// $x++ on next loop converts x to 0
			$x=-1;
		}
	}

	// output modified image as PNG (or other *LOSSLESS* format)
	$nama_gambar=rand(0,100)."encoded.jpeg";
	header("Content-Type: application/octet-stream");
	header("Content-Disposition: attachment; filename=$nama_gambar");
	header('Content-Transfer-Encoding: binary'); 
    header('Cache-Control: must-revalidate, post-check=0, pre-check=0'); 
	ImagePNG($outpic);

	// remove images
	ImageDestroy($outpic);
	ImageDestroy($pic);
	exit();
}