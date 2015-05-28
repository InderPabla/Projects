<?php
	//Log: class for handeling all transactions, and recording them
	class Log 
	{
		private $connection; //database connection object
		
		function __construct($conn) 
		{
			$this->connection = $conn;
		}
		
		function to_string()
		{
			echo "Log Table<br>";
		}
		
		//create new log
		function log($table, $action)
		{
			$insert_query = "INSERT INTO LOG (OPERATION, TABLE_NAME,TIME_OF_ACTON) VALUES (\"$action\",\"$table\",CURRENT_TIMESTAMP)";
			$res = mysqli_query($this->connection,$insert_query);
		}
		
		
	}
?>