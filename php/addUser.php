<?php
 if($_SERVER['REQUEST_METHOD']=='POST'){

	 //Getting values
	 $firstname = $_POST['firstname'];
	 $othernames = $_POST['othernames'];
	 $email = $_POST['email'];
	 $gender = $_POST['gender'];
	 $idno = $_POST['idno'];
	 $dob = $_POST['dob'];
	 $imei = $_POST['imei'];
	 //set loan limi to Ksh.1000
	 $loan_limit = 1000;
	 $phone = $_POST['phone'];
	 $pin = $_POST['pin'];
	 //date of entry
	 $date_created = date('Y-m-d H:i:s');
	 $status = "pending" //account status can be pending, active, inactive

	 //Creating an sql query
	 $sql = "INSERT INTO tblUsers (firstname, othernames, email, gender, idno, dob, imei, loan_limit, phone, pin, date_created, status) VALUES('$firstname','$othernames','$email'.'$email','$gender','$idno','$dob','$imei','$loan_limit','$phone','$pin','$date_created','$status')";

	 //Importing our db connection script
	 require_once('dbConnect.php');

	 //Executing query to database
	 if(mysqli_query($con,$sql)){
	 	echo 'Account created Successfully';
	 }else{
	   echo 'Could not create Account';
	 }

	 //Closing the database
	 mysqli_close($con);
 }
