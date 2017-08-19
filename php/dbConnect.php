<?php
	/*
		author: Eric Wamari
		website: http://www.harlertechnologies.com
		My Database is harlerte_azimadb
	*/

	//Defining Constants
	define('HOST','localhost');
	define('USER','harlerte_azimadb');
	define('PASS','rz3P{N&oEW=z=nd2r6');
	define('DB','harlerte_azimadb');

	//Connecting to Database
		$con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');
