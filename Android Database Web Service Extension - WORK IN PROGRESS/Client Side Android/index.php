<?php
	
	$connection = new mysqli("localhost", "root", "pass1"); 
	$result = mysqli_query($connection,"SHOW DATABASES"); 
	$stack = array();
	while ($row = mysqli_fetch_array($result)) 
	{ 			
		$data  = ["name" => $row[0]];
		array_push($stack,$data);
	}
  $someJSON = json_encode($stack);
  echo $someJSON;
?>