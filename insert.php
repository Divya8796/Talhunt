<?php
	
	$host="localhost";
	$username="root";
	$password="";
	$db_name="talhunt";

	$con=mysql_connect("$host", "$username", "$password") or die("cannot connect");
	mysql_select_db("$db_name") or die("cannot select DB");
	if(!$con)
	{
		die('cannot connect'.mysql_error());
	}
	
if(isset($_POST['email']) && isset($_POST['password']) && isset($_POST['name'])) 
{
    $email = $_POST['email'];
    $password = $_POST['password'];
    $name = $_POST['name'];
	
	$query="INSERT INTO user (Email,Password,Name) VALUES('$email','$password','$name')";
    $result = mysql_query($query,$con);
 
    if ($result) 
	{
		$response=array('x'=>1);
     	echo json_encode($response);
	}
	else 
	{
		$response=array('x'=>0);
    	echo json_encode($response);
    }
} 

else 
{
	$response="fail.";
    echo json_encode($response);
	
}
?>