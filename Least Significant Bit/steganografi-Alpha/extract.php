<?php
function steg_recover($gambar)
{
	// recovers file hidden in a PNG image

	$binstream = "";
	$filename = "";

	// get image and width/height
	$attributes = getImageSize($gambar['tmp_name']);
	$pic = ImageCreateFromPNG($gambar['tmp_name']);

	if(!$pic || !$attributes)
	{
		return "could not read image";
	}

	// get boundary
	$bin_boundary = "";
	$boundary="";
	for($x=0 ; $x<8 ; $x++)
	{
		$bin_boundary .= rgb2bin(ImageColorAt($pic, $x,0));
	}
	
	

	// convert boundary to ascii
	for($i=0 ; $i<strlen($bin_boundary) ; $i+=8)
	{
		$binchunk = substr($bin_boundary,$i,8);
		$boundary .= bin2asc($binchunk);

	}


	// now convert RGB of each pixel into binary, stopping when we see $boundary again

	// do not process first boundary
	$start_x = 8;
	$ascii="";
	for($y=0 ; $y<$attributes[1] ; $y++)
	{
		for($x=$start_x ; $x<$attributes[0] ; $x++)
		{
			// generate binary
			$binstream .= rgb2bin(ImageColorAt($pic, $x,$y));
			// convert to ascii
			if(strlen($binstream)>=8)
			{
				$binchar = substr($binstream,0,8);
				$ascii .= bin2asc($binchar);
				$binstream = substr($binstream,8);
			}

			// test for boundary
			if(strpos($ascii,$boundary)!==false)
			{
				// remove boundary
				$ascii = substr($ascii,0,strlen($ascii)-3);

				if(empty($filename))
				{
					$filename = $ascii;
					$ascii = "";
				} else {
					// final boundary; exit both 'for' loops
					break 2;
				}
			}
		}
		// on second line of pixels or greater; we can start at x=0 now
		$start_x = 0;
	}

	// remove image from memory
	ImageDestroy($pic);

	/* and output result (retaining original filename)
	header("Content-type: text/plain");
	header("Content-Disposition: attachment; filename=".$filename);*/
	// var_dump($ascii);
	// exit;
	return $ascii;
}
