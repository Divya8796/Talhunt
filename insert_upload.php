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

if(isset($_POST['path']) && isset($_POST['tag']) && isset($_POST['username'])) 
{
    $path = $_POST['path'];
	$username=$_POST['username'];
	$tag=$_POST['tag'];
	
	$query1="select User_id from user  where Email='$username'";
    $res = mysql_query($query1,$con);
	while($row=mysql_fetch_array($res))
	{
		$id=$row['User_id'];
	}
	
	$path="$id"."_"."$path";
	$query="INSERT INTO video (Path,User_id,Tag) VALUES('$path','$id','$tag')";
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


}
?>