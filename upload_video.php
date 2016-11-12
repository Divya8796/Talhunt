<?php
	if($_SERVER['REQUEST_METHOD']=='POST' && isset($_FILES['myFile']))
	{
		$file_name=$_FILES['myFile']['name'];
		$file_size=$_FILES['myFile']['size'];
		$file_type=$_FILES['myFile']['type'];
		$temp_name=$_FILES['myFile']['tmp_name'];
		$location="uploads/video/";
		move_uploaded_file($temp_name,$location.$file_name);
		echo "file uploaded successfully.";
	}
	else
	{
		echo "Error";
	}
?>