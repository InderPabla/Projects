<?php
	$server = $_GET["server"]; 
	$username = $_GET["username"]; 
	$password = $_GET["password"];
	
	$connection = new mysqli($server, $username, $password);
	$all_data = array();
	
	$result_db = mysqli_query($connection,"SHOW DATABASES"); 
	while ($row_db = mysqli_fetch_array($result_db)) 
	{ 			
		$database_name = $row_db[0];
		mysqli_select_db($connection,$database_name);
		
		$result_table = mysqli_query($connection,"SHOW TABLES");
		$all_tables = array();
		while ($row_table = mysqli_fetch_array($result_table)) 
		{ 			
			$table_name  = $row_table[0];
			
			$result_column = mysqli_query($connection,"SHOW COLUMNS FROM ".$table_name); 
			$all_columns = array();
			while ($row_column = mysqli_fetch_array($result_column)) 
			{ 			
				$table_name  = $row_column[0];
				array_push($all_columns,$table_name);
			}
			array_push($all_tables,$all_columns);
		}
		array_push($all_data,$all_tables);
	}
	
	
	$all_data_JSON = json_encode($all_data);
	echo $all_data_JSON;
	
?>