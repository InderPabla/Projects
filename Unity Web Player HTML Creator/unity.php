<?php
	$fileContent = file_get_contents('template.html', true); //open template.html and put into $fileContent 
	
	//marker keys corrisponding to marker tags in template.html
	$marker = array(
    "title" => "TITLEMARKER",
    "header" => "HEADERMARKER",
    "width" => "WIDTHMARKER",
    "height" => "HEIGHTMARKER",
    "name" => "NAMEMARKER",
	"color" => "BACKGROUNDMARKER",
	"border" => "BORDERCMARKER",
	"bwidth" => "BORDERWMARKER",
	"bheight" => "BORDERHMARKER",
	"bradius" => "BORDERRMARKER");
	
	$name = $_GET["name"]; //get file name
	$color = $_GET["color"]; //get background color
	$width = $_GET["width"]; //get game width
	$height = $_GET["height"]; //get game height
	$title = $_GET["title"]; //get game title
	$header = $_GET["header"]; //get game header
	$border = $_GET["border"]; //get game border color
	$bwidth = $_GET["bwidth"]; //get game border width
	$bheight = $_GET["bheight"]; //get game border height
	$bradius = $_GET["bradius"]; //get game border radius
	$unityName = $name; //store name into unityName
	
	$name = str_replace(".unity3d",".html",$name); //replace .unity3d in the name with .html
	$name = str_replace(".unity2d",".html",$name); //replace .unity2d in the name with .html if it's a 2d game
	
	if(strcmp($name,"template.html")==0){
		$name = "template_unity.html"; //rename file to template_unity.html if $name is template.html
	}
	if(strcmp($name,"index.html")==0){
		$name = "index_unity.html"; //rename file to index_unity.html if $name is index.html
	}
	
	if (endswith($unityName, ".unity2d") || endswith($unityName, ".unity3d")){
		$fileContent = str_replace($marker["title"],$title,$fileContent); //replace title marker with chosen title
		$fileContent = str_replace($marker["width"],$width,$fileContent); //replace width marker with chosen width
		$fileContent = str_replace($marker["height"],$height,$fileContent); //replace height marker with chosen height
		$fileContent = str_replace($marker["name"],$unityName,$fileContent); //replace name marker with chosen name
		$fileContent = str_replace($marker["color"],$color,$fileContent); //replace color marker with chosen color
		$fileContent = str_replace($marker["border"],$border,$fileContent); //replace border color marker with chosen border color
		$fileContent = str_replace($marker["bwidth"],$bwidth,$fileContent); //replace border width marker with border width
		$fileContent = str_replace($marker["bheight"],$bheight,$fileContent); //replace border height marker with border height
		$fileContent = str_replace($marker["bradius"],$bradius,$fileContent); //replace border radius marker with border radius
		$fileContent = str_replace($marker["header"],$header,$fileContent); //replace header marker with chosen header
		
		file_put_contents($name, $fileContent); // create file
		$contentHeader = "Content-disposition: attachment; filename=".$name; //content header for forced download
		header($contentHeader); //apply header
		header("Content-type: application/html"); //give content type information of type html
		readfile($name); //download file
		unlink($name); //delete file after download
	} //unityName ends with .unity2d or .unity3d file extention
	
	/**
	 * endswith: true of false if strings ends with some substring
	 * @param type $string string to check for ending
	 * @param type $sub check if this string exists at end
	 * @return return true of false
	 */
	function endswith($string, $sub) {
		$stringLength = strlen($string); //get length of string
		$subLen = strlen($sub); //get length of substring
		if ($subLen > $stringLength) return false; //return false if substring is bigger than string
		return substr_compare($string, $sub, $stringLength - $subLen, $subLen) === 0; //compare position of length-sublength from string equals substring
	}
?>