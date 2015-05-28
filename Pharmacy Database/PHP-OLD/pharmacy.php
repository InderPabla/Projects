<?php
	//Pharmacy: class for handeling pharmacy transactions, and recording them
	class Pharmacy 
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
			echo "Pharmacy Table<br>";
		}
		
		//create new pharmacy
		function insert ($STORE_ADRS,$STORE_HRS,$STORE_PHN_NUM)
		{
			$q = "SELECT MAX(STORE_NUM) AS storenum FROM PHARMACY";
			$res = mysqli_query($this->connection,$q) or trigger_error("Query: $q\n<br />MySQL Error: " . mysqli_error($dbc));
			$followingdata = $res->fetch_array(MYSQLI_ASSOC);
			
			if ($followingdata['storenum'][0]==null)
			{
				$insert_query = "INSERT INTO PHARMACY(STORE_NUM,STORE_ADRS,STORE_HRS,STORE_PHN_NUM) VALUES(1,\"$STORE_ADRS\",\"$STORE_HRS\",\"$STORE_PHN_NUM\")";
			}
			else
			{
				$num = $followingdata['storenum'][0]+1;
				$insert_query = $insert_query = "INSERT INTO PHARMACY(STORE_NUM,STORE_ADRS,STORE_HRS,STORE_PHN_NUM) VALUES($num,\"$STORE_ADRS\",\"$STORE_HRS\",\"$STORE_PHN_NUM\")";
			}

			$res = mysqli_query($this->connection,$insert_query) or trigger_error("Query: $insert_query\n<br />MySQL Error: " . mysqli_error($dbc));
			
			$this->log->log("PHARMACY","INSERT");
		
		}
		
	}
?>