<?php
	$file = 'info.txt';
	$current = file_get_contents($file);
	$current .= "http://" . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI']."\n";
	file_put_contents($file, $current);
	
	$connection = new mysqli($_GET["server"], $_GET["username"], $_GET["password"]);
	mysqli_select_db($connection,$_GET["database"]);
	
	$result = mysqli_query($connection,"SHOW COLUMNS FROM ".$_GET["table"]); 
	$column_names = array();
	while ($row = mysqli_fetch_array($result)) 
	{ 			
		$data  = ["NAME" => $row[0]];		
		array_push($column_names,$data);
	}
	
	
	$result = mysqli_query($connection,
	"SELECT COLUMN_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE table_name = \"".$_GET['table']."\""); 	
	$column_types = array();
	while($row = mysqli_fetch_array($result))
	{
		$data  = ["TYPE" => $row[0]];		
		array_push($column_types,$data);
	}
	
	
	$column_total = array();
	
	$data1 = ["COLUMN_NAME" => $column_names];
	$data2 = ["COLUMN_TYPE" => $column_types];
	array_push($column_total,$data1,$data2);
	
	$JSONdata = json_encode($column_total);
	echo $JSONdata;
	//http://192.168.0.12:80/a/column_retrieve.php?server=localhost&username=root&password=pass1&database=pharmacy&table=employee
?>

