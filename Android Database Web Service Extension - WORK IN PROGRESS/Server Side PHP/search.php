<?php
	$file = 'info.txt';
	$current = file_get_contents($file);
	$current .= "http://" . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI']."\n";
	file_put_contents($file, $current);
	
	$connection = new mysqli($_GET["server"], $_GET["username"], $_GET["password"]);
	mysqli_select_db($connection,$_GET["database"]);
	$table = $_GET["table"];
	
	$result = mysqli_query($connection,"SHOW COLUMNS FROM ".$_GET["table"]); 
	$column_names = array();
	while ($row = mysqli_fetch_array($result)) 
	{ 					
		array_push($column_names,$row[0]);
	}
	
	$search_query = "SELECT * FROM $table WHERE";
	
	$data_search = "";
	
	for($i = 0; $i < intval($_GET["length"]); $i++)
	{
		if($i == intval($_GET["length"])-1)
			$data_search .= " ".$column_names[$i]." LIKE \"%".$_GET["data".$i]."%\"";
		else
			$data_search .= " ".$column_names[$i]." LIKE \"%".$_GET["data".$i]."%\" AND";
	}
	
	$search_query .= $data_search;
	
	$result = mysqli_query($connection,$search_query); 
	
	$table_data = array();
	
	$bound1 = intval($_GET["bound1"]);
	$bound2 = intval($_GET["bound2"]);
	$index = 0;
	while($row = mysqli_fetch_array($result))
	{
		$index++;
		if($index > $bound2)
		{
			break;
		}
		else if($index > $bound1)
		{
			
			$column_data = array();
			for ($i = 0;$i<intval($_GET["length"]);$i++)
			{
				$current_column_data = ["DATA" => $row[$i]];
				array_push($column_data,$current_column_data);
			}
			$current_table_data = ["COLUMN" => $column_data];
			array_push($table_data,$current_table_data);
		}
	}
	
	$JSONdata = json_encode($table_data);
	echo $JSONdata;
	/*echo "<br>";
	echo $search_query;*/
	
?>