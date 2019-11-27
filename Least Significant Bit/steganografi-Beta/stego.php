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
	// var_dump($make_odd);
	// exit;

	$y = 0;
    $x = 0;
    $size = sizeof($make_odd);
    for ($i = 0; $i < sizeof($make_odd); $i += 3) {
        // read RGB of pixel
        $rgb = ImageColorAt($pic, $x, $y);

        $cols = array();
        $cols[] = ($rgb >> 16) & 0xFF;
        $cols[] = ($rgb >> 8) & 0xFF;
        $cols[] = $rgb & 0xFF;

        // echo "old";
        // var_dump($cols);

        $x++;

      


        if ($size > 3) {
            for ($j = 0; $j < 3; $j++) {

				if($make_odd[$i+$j]===true && is_even($cols[$j]))
				{
					// is even, should be odd
					$cols[$j]++;
				} else if($make_odd[$i+$j]===false && !is_even($cols[$j])){
					// is odd, should be even
					$cols[$j]--;
				} // else colour is fine as is
            }
        } else if ($size == 2) {
            for ($j = 0; $j < 2; $j++) {
				if($make_odd[$i+$j]===true && is_even($cols[$j]))
				{
					// is even, should be odd
					$cols[$j]++;
				} else if($make_odd[$i+$j]===false && !is_even($cols[$j])){
					// is odd, should be even
					$cols[$j]--;
				} // else colour is fine as is
            }
        } else if ($size == 1) {
            for ($j = 0; $j < 1; $j++) {
				if($make_odd[$i+$j]===true && is_even($cols[$j]))
				{
					// is even, should be odd
					$cols[$j]++;
				} else if($make_odd[$i+$j]===false && !is_even($cols[$j])){
					// is odd, should be even
					$cols[$j]--;
				} // else colour is fine as is
            }
        }





        $size -= 3;
        // echo "new";
        // var_dump($cols);



        $temp_col = ImageColorAllocate($outpic, $cols[0], $cols[1], $cols[2]);
        ImageSetPixel($outpic, $x, $y, $temp_col);


        if ($x == ($attributes[0] - 1)) {
            $y++;
            $x = -1;
        }
    }
  

	$nama_gambar=rand(0,100)."encoded.jpeg";
	header("Content-Type: application/octet-stream");
	header("Content-Disposition: attachment; filename=$nama_gambar");
	header('Content-Transfer-Encoding: binary'); 
    header('Cache-Control: must-revalidate, post-check=0, pre-check=0'); 
	ImagePNG($outpic);

	ImageDestroy($outpic);
	ImageDestroy($pic);
	exit();
}