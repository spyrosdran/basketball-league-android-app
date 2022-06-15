<?php
	

	$changeOutName = $_GET["changeOut"];
	$changeInName = $_GET["changeIn"];
	$matchID = $_GET["matchID"];
	$minute = $_GET["minute"];
	
	//Retrieving the Player who goes out
	$mysqli = new mysqli("localhost", "root", "", "basketleague");
	$query = "SELECT `ID` FROM `player` WHERE `name` = '" . $changeOutName . "';";
	$result = $mysqli->query($query);
	$row = $result->fetch_assoc();
	$changeOutPlayerID = $row["ID"];
	
	//Retrieving the Player who goes in
	$mysqli = new mysqli("localhost", "root", "", "basketleague");
	$query = "SELECT `ID` FROM `player` WHERE `name` = '" . $changeInName . "';";
	$result = $mysqli->query($query);
	$row = $result->fetch_assoc();
	$changeInPlayerID = $row["ID"];

	$host="localhost";
	$uname="root";
	$pass="";
	$dbname="basketleague";

	
	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
	mysqli_select_db($dbh, $dbname);
			
	$sql = "INSERT INTO `match_event` (`playerID`, `matchID`, `type`, `minute`) VALUES(" . $changeOutPlayerID . "," . $matchID . ",'change out', '" . $minute . "');";
	mysqli_query($dbh, $sql);
	
	$sql = "INSERT INTO `match_event` (`playerID`, `matchID`, `type`, `minute`) VALUES(" . $changeInPlayerID . "," . $matchID . ",'change in', '" . $minute . "');";
	mysqli_query($dbh, $sql);
	
	//Updating match table
	$query = "SELECT `home1`,`home2`,`home3`,`home4`,`home5`,`away1`, `away2`, `away3`, `away4`, `away5` FROM `match` WHERE `matchID` = " . $matchID . ";";
	$result = $mysqli->query($query);
	$row = $result->fetch_assoc();
	$column = "";
	
	if($row["home1"] == $changeOutPlayerID) $column = "home1";
	if($row["home2"] == $changeOutPlayerID) $column = "home2";
	if($row["home3"] == $changeOutPlayerID) $column = "home3";
	if($row["home4"] == $changeOutPlayerID) $column = "home4";
	if($row["home5"] == $changeOutPlayerID) $column = "home5";
	if($row["away1"] == $changeOutPlayerID) $column = "away1";
	if($row["away2"] == $changeOutPlayerID) $column = "away2";
	if($row["away3"] == $changeOutPlayerID) $column = "away3";
	if($row["away4"] == $changeOutPlayerID) $column = "away4";
	if($row["away5"] == $changeOutPlayerID) $column = "away5";
	
	$sql = "UPDATE `match` SET `" . $column ."` = '" . $changeInPlayerID . "' WHERE `matchID` = " . $matchID . ";";
	mysqli_query($dbh, $sql);
	
	mysqli_close($dbh);
?>