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
	
if(isset($_POST['email']) && isset($_POST['password']) && isset($_POST['name']) && isset($_POST['uploadImage'])) 
{
    $email = $_POST['email'];
    $password = $_POST['password'];
    $name = $_POST['name'];
	$photo=$_POST['uploadImage'];
	
	$query1="select User_id from user";
    $res = mysql_query($query1,$con);
	while($row=mysql_fetch_array($res))
	{
		$id=$row['User_id'];
	}

	$path="$id"+1; //change path
	
	$query="INSERT INTO user (Email,Password,Name,Photo) VALUES('$email','$password','$name','$path')";
    $result = mysql_query($query,$con);
 
	$path="uploads\\image\\"."$path";
    if ($result) 
	{
		file_put_contents($path,base64_decode($photo));
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