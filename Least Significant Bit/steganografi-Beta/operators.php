<?php
function is_odd($num)
{
    return ($num % 2 == 1);
}

ini_set("max_execution_time",10000);

function is_even($num)
{
	return ($num%2==0);
}

function asc2bin($char)
{

	return str_pad(decbin(ord($char)), 8, "0", STR_PAD_LEFT);
}

function bin2asc($bin)
{

	return chr(bindec($bin));
}

function rgb2bin($rgb)
{

	$binstream = "";
	$red = ($rgb >> 16) & 0xFF;
	$green = ($rgb >> 8) & 0xFF;
	$blue = $rgb & 0xFF;

	if(is_even($red))
	{
		$binstream .= "1";
	} else {
		$binstream .= "0";
	}
	if(is_even($green))
	{
		$binstream .= "1";
	} else {
		$binstream .= "0";
	}
	if(is_even($blue))
	{
		$binstream .= "1";
	} else {
		$binstream .= "0";
	}

	return $binstream;
}
