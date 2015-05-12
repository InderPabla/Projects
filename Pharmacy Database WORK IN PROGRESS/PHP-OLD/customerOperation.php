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
		#new 
		{
			background-color:#44c7ff;
		}
		#update 
		{
			background-color:#ff0000;
		}
		#search
		{
			background-color:#ff8c00;
		}
		#query 
		{
			background-color:#fa8072;
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
	
	<script language="javascript">	
		var server_name = getUrlVars()["server_name"];
		var username = getUrlVars()["username"];
		var password = getUrlVars()["password"];
		
		
		function customerAction(type)
		{	
			var newCust,input1,input2,input3;
			var allowed = 0;
			
			
			if(type=="New")
			{
				var name = document.getElementById("CUST_NAME").value;
				var phone = document.getElementById("CUST_PHN_NUM").value;
				var email = document.getElementById("CUST_EMAIL").value;
				var dob = document.getElementById("CUST_DOB").value;
				var add = document.getElementById("CUST_ADRS").value;
				
				if(dob==""||name==""||phone==""||add=="")
				{
					allowed = 0;
				}
				else 
				{	
					if(isDateValid(dob))
					{
						allowed = 1;
					}
				}	
			}
			else if(type=="Update")
			{
				var num = parseInt(document.getElementById("CUST_NUM").value);
				if(num>=1)
				{
					allowed = 1;
				}
			}
			
			if(allowed==1)
			{
				newCust = document.getElementById("form");
				input1 = document.createElement("input");
				input1.setAttribute("type","text");
				input1.setAttribute("name","server_name");
				input1.value = server_name;
				newCust.appendChild(input1);
				
				input2 = document.createElement("input");
				input2.setAttribute("type","text");
				input2.setAttribute("name","username");
				input2.value = username;
				newCust.appendChild(input2);
				
				input3 = document.createElement("input");
				input3.setAttribute("type","text");
				input3.setAttribute("name","password");
				input3.value = password;
				newCust.appendChild(input3);
				document.getElementById("information").form.submit();
			}			
		}
		
		//text fields for new customer
		function newCustomer()
		{ 
			var newCust,input1,input2,input3,input4,input5,input6;
			
			removeInitialDivisions();
			newCust = document.getElementById("form");
			
			appendInput("CUST_NAME","CUST_NAME, Customer Name<br>");
			appendInput("CUST_PHN_NUM","CUST_PHN_NUM, Customer Phone Number<br>");
			appendInput("CUST_EMAIL","CUST_EMAIL, Customer Email<br>");
			appendInput("CUST_DOB","CUST_DOB, yyyy/mm/dd<br>");
			appendInput("CUST_ADRS","CUST_ADRS, Customer Address<br>");
			appendInput("CUST_APRVD_DRUG","CUST_APRVD_DRUG, Customer Approved Drug<br>");
			
			newCust.appendChild(submitInformation("New"));
			newCust.appendChild(submitButton("Add Customer","New"));
			newCust.setAttribute("action","customerOperation.php");
		}
		
		//text fields for updating customer 
		function updateCustomer()
		{
			var newCust;
			
			removeInitialDivisions();
			newCust = document.getElementById("form");
			
			appendInput("CUST_NUM","CUST_NUM, Customer Number<br>");
			appendInput("CUST_NAME","CUST_NAME, Customer Name<br>");
			appendInput("CUST_PHN_NUM","CUST_PHN_NUM, Customer Phone Number<br>");
			appendInput("CUST_EMAIL","CUST_EMAIL, Customer Email<br>");
			appendInput("CUST_DOB","CUST_DOB, yyyy/mm/dd<br>");
			appendInput("CUST_ADRS","CUST_ADRS, Customer Address<br>");
			appendInput("CUST_APRVD_DRUG","CUST_APRVD_DRUG, Customer Approved Drug<br>");

			newCust.appendChild(submitInformation("Update"));
			newCust.appendChild(submitButton("Update Customer","Update"));
			newCust.setAttribute("action","customerOperation.php");
		}
		
		//create and append input text fields to forms
		function appendInput(id,label)
		{
			var input,newCust;
			newCust = document.getElementById("form");
			input = document.createElement("input");		
			input.setAttribute("type","text");
			input.setAttribute("id",id);
			input.setAttribute("name",id);
			newCust.innerHTML += label;
			newCust.appendChild(input);
			newCust.innerHTML += "<br><br>";
		}
		
		//remove initial html
		function removeInitialDivisions()
		{
			document.getElementById("div_new").remove();
			document.getElementById("div_update").remove();
			document.getElementById("div_search").remove();
			document.getElementById("div_query").remove();
		}
		
		//submit information type 
		function submitInformation(name)
		{
			var information = document.createElement("input");
			information.setAttribute("type","hidden");
			information.setAttribute("id","information");
			information.setAttribute("name","information");
			information.setAttribute("value",name);
			return information;
		}
		
		//submit button
		function submitButton(name,id)
		{
			var submit = document.createElement("input");
			submit.setAttribute("type","button");
			submit.setAttribute("class","button");
			submit.setAttribute("id",id);
			submit.setAttribute("value",name);
			submit.setAttribute("onclick","customerAction("+"'"+id+"'"+")");
			return submit;
		}
		
		//parse url for database information (server name, username, password)
		function getUrlVars() 
		{
			var vars = {};
			var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function(m,key,value) {
				vars[key] = value;
			});
			return vars;
		}
		
		//check data validation
		function isDateValid(dob)
		{
			var maxDaysOfMonths = [31,29,31,30,31,30,31,31,30,31,30,31];
			var dob_array = dob.split("/");
			var year = parseInt(dob_array[0]);
			var month = parseInt(dob_array[1]);
			var day = parseInt(dob_array[2]);

			if(year>1900 && month>=1 && month<=12 && day>=1 && day<=maxDaysOfMonths[month-1])
			{
				return true;
			}
			return false;
		}
	</script>
	
	<?php
		include 'PharmacyDatabase.php';
		
		//check for information tag in url data
		if (isset($_GET['information'])) 
		{
			$server_name = $_GET["server_name"];
			$username = $_GET["username"];
			$password = $_GET["password"];
			$pharmacy = "pharmacy";
			
			$connection = new mysqli($server_name, $username, $password)or badAuthentication();
			mysqli_select_db($connection,$pharmacy);
			$db = new PharmacyDatabase($connection);
			
			//if information is for inserting new customer
			if($_GET['information']=="New")
			{
				echo "<h3>Added to customer table</h3>";
				$db->customer->insert($_GET["CUST_NAME"],$_GET["CUST_PHN_NUM"],$_GET["CUST_EMAIL"],$_GET["CUST_DOB"],$_GET["CUST_ADRS"],$_GET["CUST_APRVD_DRUG"]);	//insert customer			  
				
				//print customer data just inserted in table format
				echo "<table border=\"1\" style=\"width:100%\">";
				echo "<tr>";
				echo "<td>".$_GET["CUST_NAME"]."</td>";
				echo "<td>".$_GET["CUST_PHN_NUM"]."</td>";
				echo "<td>".$_GET["CUST_EMAIL"]."</td>";
				echo "<td>".$_GET["CUST_DOB"]."</td>";
				echo "<td>".$_GET["CUST_ADRS"]."</td>";
				echo "<td>".$_GET["CUST_APRVD_DRUG"]."</td>";
				echo "</tr>";
				echo "</table>";
				echo "<br>";
				
			}
			//if information is for updating old customer
			else if($_GET['information']=="Update")
			{
				$db->customer->update($_GET["CUST_NUM"],$_GET["CUST_NAME"],$_GET["CUST_PHN_NUM"],$_GET["CUST_EMAIL"],$_GET["CUST_DOB"],$_GET["CUST_ADRS"],$_GET["CUST_APRVD_DRUG"]);				  	
			} 
			
			echo "<a href = \"customerOperation.php?server_name=".$server_name."&username=".$username."&password=".$password."\">GoBack</a>";
		}
		else
		{
			//intial HTML display
			?>
				<form action="" method="GET" id = "form">
					<div id = "div_new">
						<input type="button" id = "new" class="button" name = "new" value = "New Customer" title="Insert new customer to table" onclick = "newCustomer()">
						<br><br>
					</div>
					
					<div id = "div_update">
						<input type="button" id = "update" class="button" name = "update" value = "Update Customer" title = "Update old customer from table" onclick = "updateCustomer()">
						<br><br>
					</div>

					<div id = "div_search">
						<input type="button" id = "search" class="button" name = "search" value = "Search Customer" title = "Search for customer information">
						<br><br>
					</div>
					
					<div id = "div_query">
						<input type="button" id = "query" class="button" name = "query" value = "SQL Query" title = "Do a SQL query">
						<br><br>
					</div>
				</form>
			<?php
			echo "<a href = \"index.html\">Go Back</a>";
		}
	?>
</html>