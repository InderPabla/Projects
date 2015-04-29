<?php
	//Employee: class for handeling employee transactions
	class Employee 
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
			echo "Employee Table<br>";
		}
		
		//create new employee
		function insert ($EMP_NAME,$EMP_PHN_NUM,$EMP_DOB,$EMP_ADRS,$EMP_HIRE_DATE,$EMP_HRLY_WGE,$EMP_TITLE,$STORE_NUM)
		{
			$q = "SELECT MAX(EMP_NUM) AS empnum FROM EMPLOYEE";
			$res = mysqli_query($this->connection,$q) or trigger_error("Query: $q\n<br />MySQL Error: " . mysqli_error($dbc));
			$followingdata = $res->fetch_array(MYSQLI_ASSOC);
			
			if ($followingdata['empnum'][0]==null)
			{
				$insert_query = "INSERT INTO EMPLOYEE(EMP_NUM,EMP_NAME,EMP_PHN_NUM,EMP_DOB,EMP_ADRS,EMP_HIRE_DATE,EMP_HRLY_WGE,EMP_TITLE,STORE_NUM) VALUES(1,\"$EMP_NAME\",\"$EMP_PHN_NUM\",\"$EMP_DOB\",\"$EMP_ADRS\",\"$EMP_HIRE_DATE\",$EMP_HRLY_WGE,\"$EMP_TITLE\",$STORE_NUM)";
			}
			else
			{
				$num = $followingdata['empnum'][0]+1;
				$insert_query = $insert_query = "INSERT INTO EMPLOYEE(EMP_NUM,EMP_NAME,EMP_PHN_NUM,EMP_DOB,EMP_ADRS,EMP_HIRE_DATE,EMP_HRLY_WGE,EMP_TITLE,STORE_NUM) VALUES($num,\"$EMP_NAME\",\"$EMP_PHN_NUM\",\"$EMP_DOB\",\"$EMP_ADRS\",\"$EMP_HIRE_DATE\",$EMP_HRLY_WGE,\"$EMP_TITLE\",$STORE_NUM)";
			}

			$res = mysqli_query($this->connection,$insert_query) or trigger_error("Query: $insert_query\n<br />MySQL Error: " . mysqli_error($dbc));
			
			$this->log->log("EMPLOYEE","INSERT");
		
		}
	}
?>