<?php
	$file = 'info.txt';
	$current = file_get_contents($file);
	$current .= "http://" . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI']."\n";
	file_put_contents($file, $current);
	
	$connection = new mysqli($_GET["server"], $_GET["username"], $_GET["password"]);
	mysqli_select_db($connection,$_GET["database"]);
	
	$result = mysqli_query($connection,"SHOW TABLES"); 
	$stack = array();
	while ($row = mysqli_fetch_array($result)) 
	{ 			
		$data  = ["TABLE" => $row[0]];
		array_push($stack,$data);
	}
	$JSONdata = json_encode($stack);
	echo $JSONdata;
?>