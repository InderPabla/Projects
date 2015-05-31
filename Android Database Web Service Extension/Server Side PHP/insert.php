<?php
	$file = 'info.txt';
	$current = file_get_contents($file);
	$current .= "http://" . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI']."\n";
	file_put_contents($file, $current);
	
	$connection = new mysqli($_GET["server"], $_GET["username"], $_GET["password"]);
	mysqli_select_db($connection,$_GET["database"]);
	$table = $_GET["table"];
	
	$insert_query = "INSERT INTO $table VALUES (";
	$data = "";
	
	for($i = 0; $i < intval($_GET["length"]); $i++)
	{
		if($i == intval($_GET["length"])-1)
			$data.="\"".$_GET["data".$i]."\"";
		else
			$data.="\"".$_GET["data".$i]."\"".",";
	}
	$insert_query .= $data.")";
	
	$res = mysqli_query($connection,$insert_query);
	
	if($res === false)
		echo "ERROR";
	echo "SUCCESS";
?>