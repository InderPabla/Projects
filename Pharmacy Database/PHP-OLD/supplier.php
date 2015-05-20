<?php
	//Pharmacy: class for handeling supplier transactions, and recording them
	class Supplier 
	{
		private $connection; //database connection object
		var $log; //log for logging customer action
		
		function __construct($conn) 
		{
			$this->connection = $conn;
			$this->log = new Log($this->connection);
		}
		
		function to_string()
		{
			echo "Supplier Table<br>";
		}
		
		//create new supplier
		function insert ($SUP_ADRS,$SUP_NAME,$SUP_PHN_NUM)
		{
			$q = "SELECT MAX(SUP_NUM) AS supnum FROM SUPPLIER";
			$res = mysqli_query($this->connection,$q) or trigger_error("Query: $q\n<br />MySQL Error: " . mysqli_error($dbc));
			$followingdata = $res->fetch_array(MYSQLI_ASSOC);
			
			if ($followingdata['supnum'][0]==null)
			{
				$insert_query = "INSERT INTO SUPPLIER(SUP_NUM,SUP_ADRS,SUP_NAME,SUP_PHN_NUM) VALUES(1,\"$SUP_ADRS\",\"$SUP_NAME\",\"$SUP_PHN_NUM\")";
			}
			else
			{
				$num = $followingdata['supnum'][0]+1;
				$insert_query = $insert_query = "INSERT INTO SUPPLIER(SUP_NUM,SUP_ADRS,SUP_NAME,SUP_PHN_NUM) VALUES($num,\"$SUP_ADRS\",\"$SUP_NAME\",\"$SUP_PHN_NUM\")";
			}

			$res = mysqli_query($this->connection,$insert_query) or trigger_error("Query: $insert_query\n<br />MySQL Error: " . mysqli_error($dbc));
			
			$this->log->log("SUPPLIER","INSERT");
		
		}
	}
?>