<!DOCTYPE HTML>
<html>
	<?php
		
		if( $_GET["markert"]!=null)
		{
			$info = $_GET["markert"];
			getInformation ($info);
		} // if GET is not null, then send to execute python
		else
		{
			//echo html code to let user know nothing was inputed 
			echo "<div class = \"correct\">";
			echo "<div class = \"outterDiv\">"."<h3 class = \"innerDiv\">"."Soo...nothing..."."</h3>"."</div>";
			echo "</div>";
		} 

		/**
		 * Executes tmxmoneyScrap.py with $info as argument. 
		 * Then gets output from python script and creates html accordingly 
		 *
		 * @param type $info All IDs of companies, ex: SU BMO.
		 */
		function getInformation ($info) 
		{
			$pyscript = 'tmxmoneyScrap.py'; //python file
			$python = 'C:\\Python27\\python.exe'; //python.exe path
			$cmd = "$python $pyscript $info"; //command line instruction to execute 
			exec("$cmd", $output); //execute and put python prints into output
			$list = explode(" ",$info); //turn contents of $info into an array, ex: "SU BMO ^JX" = ["SU","BMO","^JX"]
			$index = 0; //index for $list array
			for ($x = 0; $x < count($output); $x+=2)
			{
				$site = "http://web.tmxmoney.com/quote.php?qm_symbol=".$list[$index]."&locale=EN&src=topq&mobile=false"; //site for href
				$checkFloat = str_replace(',', '', $output[$x]); //number format
				
				if((string)(float)($checkFloat)==$checkFloat)
				{
					//echo html code to let user know stock price, company name and link to the url used
					echo "<div class = \"correct\">";
					echo "<div class = \"outterDiv\">"."<h3 class = \"innerDiv\">"."Company: ".$output[$x+1]."</h3>"."</div>";
					echo "<div class = \"outterDiv\">"."<h3 class = \"innerDiv\">"."Price: ".$output[$x]."</h3>"."</div>";
					echo "<a class = \"links\" href=".$site.">Go To Site</a>";
					echo "</div>";
				} //if string is a float
				else
				{
					//python script must have recognized the error and given appropriate output
					$error = explode(" ",$output[$x]); //turn contents of $output into an array, ex: "error companyName error" = ["error","companyName","error"]
					//echo html code to let user know error has occoured 
					echo "<div class = \"wrong\">";
					echo "<div class = \"outterDiv\">"."<h3 class = \"innerDiv\">".$error[1]." is not a company."."</h3>"."</div>";
					echo "</div>";
				} //else unable to convert string to float, thus it must be an error
				$index+=1; //increment index
			} //loop through all elements of $output 
		}
	?>
	
	<form class = "outterDiv" action = "index.html">
		<input class = "innerDiv" type="submit" value="Go Back">
	</form>
	
	<style>
		body {background-color:grey}
		h3   {color:yellow}
		.outterDiv {
			text-align: center; 
		} 
		.innerDiv {
			position: absolute:
			top: 50%;
		}
		.correct {
			background-color:black;
			color:white;
			margin:10px;
			padding:10px;
		} 
		.wrong {
			background-color:red;
			color:white;
			margin:10px;
			padding:10px;
		} 
		.links {
			color:white;
		} 
	</style>
</html>