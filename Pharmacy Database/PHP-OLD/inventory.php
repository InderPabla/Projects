<?php
	//Inventory: class for handeling inventory transactions
	class Inventory 
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
			echo "Inventory Table<br>";
		}
		
		//create new inventory
		function insert ($ITEM_CST_FROM_SUP,$ITEM_CST_TO_CUST,$ITEM_DESC,$ITEM_INS_COVG,$ITEM_PRESCRIPTION,$SUP_NUM)
		{
			$q = "SELECT MAX(ITEM_NUM) AS itemnum FROM INVENTORY";
			$res = mysqli_query($this->connection,$q) or trigger_error("Query: $q\n<br />MySQL Error: " . mysqli_error($dbc));
			$followingdata = $res->fetch_array(MYSQLI_ASSOC);
			
			if ($followingdata['itemnum'][0]==null)
			{
				$insert_query = "INSERT INTO INVENTORY(ITEM_NUM,ITEM_CST_FROM_SUP,ITEM_CST_TO_CUST,ITEM_DESC,ITEM_INS_COVG,ITEM_PRESCRIPTION,SUP_NUM) VALUES(1,$ITEM_CST_FROM_SUP,$ITEM_CST_TO_CUST,\"$ITEM_DESC\",$ITEM_INS_COVG,$ITEM_PRESCRIPTION,$SUP_NUM)";
			}
			else
			{
				$num = $followingdata['itemnum'][0]+1;
				$insert_query = $insert_query = "INSERT INTO INVENTORY(ITEM_NUM,ITEM_CST_FROM_SUP,ITEM_CST_TO_CUST,ITEM_DESC,ITEM_INS_COVG,ITEM_PRESCRIPTION,SUP_NUM) VALUES($num,$ITEM_CST_FROM_SUP,$ITEM_CST_TO_CUST,\"$ITEM_DESC\",$ITEM_INS_COVG,$ITEM_PRESCRIPTION,$SUP_NUM)";
			}

			$res = mysqli_query($this->connection,$insert_query) or trigger_error("Query: $insert_query\n<br />MySQL Error: " . mysqli_error($dbc));
			
			$this->log->log("INVENTORY","INSERT");
		}
	}
?>