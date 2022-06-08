<?php
	

	$changeOut = $_GET["changeOut"];
	$changeIn = $_GET["changeIn"];
	$matchID = $_GET["matchID"];
	
	//Retrieving the Player who goes out
	$mysqli = new mysqli("localhost", "root", "", "basketleague");
	$query = "SELECT `ID` FROM `player` WHERE `name` = '" . $changeOut . "';";
	$result = $mysqli->query($query);
	$row = $result->fetch_assoc();
	$changeOut = $row["ID"];
	
	//Retrieving the Player who goes in
	$mysqli = new mysqli("localhost", "root", "", "basketleague");
	$query = "SELECT `ID` FROM `player` WHERE `name` = '" . $changeIn . "';";
	$result = $mysqli->query($query);
	$row = $result->fetch_assoc();
	$changeIn = $row["ID"];

	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="basketleague";
	
	$date = date('d-m-y h:i:s');
	
	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
	mysqli_select_db($dbh, $dbname);
			
	$sql = "INSERT INTO `match_event` (`playerID`, `matchID`, `type`, `minute`) VALUES('" . $changeOut . "','" . $matchID . "','change out', '" . $date . "')";
	mysqli_query($dbh, $sql);
	
	$sql = "INSERT INTO `match_event` (`playerID`, `matchID`, `type`, `minute`) VALUES('" . $changeIn . "','" . $matchID . "','change in', '" . $date . "')";
	mysqli_query($dbh, $sql);
	
	mysqli_close($dbh);
?>