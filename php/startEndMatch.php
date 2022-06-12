<?php
	$status = $_GET["status"];
	$matchID = $_GET['matchID'];
	
	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="basketleague";
	
	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
	mysqli_select_db($dbh, $dbname);
	
	if($status == "upcoming"){
		$status = "live";
	}
	else if ($status == "live") {
		$status = "ended";
	}
	
	$sql = "UPDATE `match` SET `status` = '" . $status . "' WHERE `matchID` = " . $matchID . ";";
	mysqli_query($dbh, $sql);
	
	mysqli_close($dbh);
?>