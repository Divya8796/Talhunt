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
	echo "connection ";
	$response ;
 
if(isset($_POST['path'])) 
{
    $video = $_POST['path'];
    $query="INSERT INTO video (Video) VALUES('$video')";
    $result = mysql_query($query,$con);
 
    if ($result) 
	{
		$response="Success";
		echo json_encode($response);
    }
	else 
	{
		$response="failure";
		echo json_encode($response);
		echo "Not";
    }
} 

else 
{

		$response="fail.";
       echo json_encode($response);
	echo "skipped your if";

}
?>