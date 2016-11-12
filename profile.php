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
	
if(isset($_POST['email'])) 
{
    $email = $_POST['email'];
    
	$query="select * from user where Email='$email'";
    $res = mysql_query($query,$con);
	$result=array();
	while($row=mysql_fetch_array($res))
	{
		array_push($result,array('name'=>$row['Name'],'url'=>$row['Photo']));
	}
	echo json_encode(array("result"=>$result));
}
	 

else 
{
		$response="fail.";
       echo json_encode($response);
}
?>