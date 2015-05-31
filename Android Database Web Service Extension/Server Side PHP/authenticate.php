<?php
	$file = 'info.txt';
	$current = file_get_contents($file);
	$current .= "http://" . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI']."\n";
	file_put_contents($file, $current);
	
	$connection = new mysqli($_GET["server"], $_GET["username"], $_GET["password"]);
	
	echo "SUCCESS";
?>