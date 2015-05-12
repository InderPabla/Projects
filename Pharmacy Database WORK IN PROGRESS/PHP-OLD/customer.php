<?php
	//Customer: class for handeling customer transactions
	class Customer
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
			echo "Customer Table<br>";
		}
		
		//create new customer
		function insert ($CUST_NAME,$CUST_PHN_NUM,$CUST_EMAIL,$CUST_DOB,$CUST_ADRS,$CUST_APRVD_DRUG)
		{
			$q = "SELECT MAX(CUST_NUM) AS custnum FROM CUSTOMER";
			$res = mysqli_query($this->connection,$q) or trigger_error("Query: $q\n<br />MySQL Error: " . mysqli_error($dbc));
			$followingdata = $res->fetch_array(MYSQLI_ASSOC);
			
			if ($followingdata['custnum'][0]==null)
			{
				$insert_query = "INSERT INTO CUSTOMER(CUST_NUM,CUST_NAME,CUST_PHN_NUM,CUST_EMAIL,CUST_DOB,CUST_ADRS,CUST_APRVD_DRUG) VALUES (1,\"$CUST_NAME\",\"$CUST_PHN_NUM\",\"$CUST_EMAIL\",\"$CUST_DOB\",\"$CUST_ADRS\",\"$CUST_APRVD_DRUG\")";
			}
			else
			{
				$num = $followingdata['custnum'][0]+1;
				$insert_query = "INSERT INTO CUSTOMER(CUST_NUM,CUST_NAME,CUST_PHN_NUM,CUST_EMAIL,CUST_DOB,CUST_ADRS,CUST_APRVD_DRUG) VALUES ($num,\"$CUST_NAME\",\"$CUST_PHN_NUM\",\"$CUST_EMAIL\",\"$CUST_DOB\",\"$CUST_ADRS\",\"$CUST_APRVD_DRUG\")";
			}

			$res = mysqli_query($this->connection,$insert_query) or trigger_error("Query: $insert_query\n<br />MySQL Error: " . mysqli_error($dbc));
			
			$this->log->log("CUSTOMER","INSERT");
		
		}
		
		function update ($CUST_NUM,$CUST_NAME,$CUST_PHN_NUM,$CUST_EMAIL,$CUST_DOB,$CUST_ADRS,$CUST_APRVD_DRUG)
		{
			
		}
	}
?>