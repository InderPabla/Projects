<html>

	<style>
		h3
		{
			margin-bottom:0px;
		}
		.error
		{
			color: Aquamarine;
			margin-bottom:0px;
			text-align: center;
		}
		body 
		{
			background-color: gray;
		}
		.button 
		{
			background-color:#44c767;
			border:1px solid #000000;
			display:inline-block;
			cursor:pointer;
			color:#ffffff;
			font-family:arial;
			font-size:17px;
			padding:5px 31px;
			text-decoration:none;
			text-shadow:1px 1px 1px #000;
			border-radius: 10px;
		}
		#customer 
		{
			background-color:#44c7ff;
		}
		#employee 
		{
			background-color:#ff0000;
		}
		#inventory 
		{
			background-color:#ff8c00;
		}
		#pharmacy 
		{
			background-color:#fa8072;
		}
		#supplier 
		{
			background-color:#9acd32;
		}
		.button:hover 
		{
			color:#000;
		}
		.button:active 
		{
			position:relative;
			top:1px;
		}
	</style>
	
	<form action="" method="POST">
		<input type="submit" class="button" name = "btnPopulate" value = "Sample Populate">
		<br><br>
		<input type="submit" id = "customer" class="button" name = "cusOper" value = "Customer Operation">
		<br><br>
		<input type="submit" id = "employee" class="button" name = "empOper" value = "Employee Operation">
		<br><br>
		<input type="submit" id = "inventory" class="button" name = "invOper" value = "Inventory Operation">
		<br><br>
		<input type="submit" id = "pharmacy" class="button" name = "phaOper" value = "Pharmacy Operation">
		<br><br>
		<input type="submit" id = "supplier" class="button" name = "supOper" value = "Supplier Operation">
	</form>
	
	<br><br>
	<a class = "error" href = "index.html">Go Back</a>
	
	
	
	<?php
		include 'PharmacyDatabase.php';
		
		$error = False;
		$table_file = "tables.txt";
		$server_name = $_GET["server_name"];
		$username = $_GET["username"];
		$password = $_GET["password"];
		$pharmacy = "pharmacy";
		
		$sql_create_database = "CREATE DATABASE pharmacy";
		$sql_drop_database = "DROP DATABASE pharmacy";
		
		
		error_reporting(0); //close automatic error
		$connection = new mysqli($server_name, $username, $password)or badAuthentication(); //connect to database, else print custom error		
		error_reporting(E_ALL);//start automatic error
		
		
		$result = mysqli_query($connection,"SHOW DATABASES"); 
		$exist = 0;
		
		//check if word "pharmacy" exists in database connection
		while ($row = mysqli_fetch_array($result)) 
		{ 
			if(strcmp($pharmacy,$row[0])==0)
			{
				$exist = 1;
				break;
			}
		}
	
		//if database does not exist, create a pharmacy database
		if($exist == 0)
		{
			if (mysqli_query($connection,$sql_create_database)) 
			{
				mysqli_select_db($connection,$pharmacy);
				$lines = file($table_file);
				
				foreach($lines as $line_num => $line)
				{
					$result = mysqli_query($connection,$line);
				}
			} 
			else 
			{
				echo "Error creating database: " . $connection->error;
			}
			
		}

		//if button pressed
		if ($_SERVER['REQUEST_METHOD'] === 'POST') 
		{
			//if customer operation selected, go to customerOperation.php
			if (isset($_POST['cusOper'])) 
			{
				header("Location: http://localhost/Pharmacy/customerOperation.php?total=Submit&server_name=$server_name&username=$username&password=$password");
			}
		}
		mysqli_close($connection); //close database connection
			
		
		function badAuthentication(){
			$error = True;
			if ($error) : ?>
				<h2 class = "error">Bad Database Authentication.</h2>
				<a class = "error" href = "index.html">Go Back</a>
			<?php endif; 
			exit();
		}
	?>
	
	
	
</html>