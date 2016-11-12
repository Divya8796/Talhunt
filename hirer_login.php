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
	
if(isset($_POST['user_id']) && isset($_POST['password'])) 
{
    $user_id = $_POST['user_id'];
    $password = $_POST['password'];
   
	$query="select * from hirer where User_id='$user_id' and Password='$password'";
    $result = mysql_query($query,$con);
	$row_count= mysql_fetch_assoc($result);
    if ($row_count>0) 
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