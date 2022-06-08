<?php

	$matchID = $_GET['matchID'];
	
	$playersID = array();	
	$team = $_GET['homeaway'];
	$mysqli = new mysqli("localhost", "root", "", "basketleague");
	if ($team == "HOME"){
		$query = "SELECT `home1`, `home2`, `home3`, `home4`, `home5` FROM `match` WHERE `matchID` = " . $matchID . ";";
		$result = $mysqli->query($query);
	
		$row = $result->fetch_assoc();
		
		$playersID[$row['home1']] = $row['home1'];
		$playersID[$row['home2']] = $row['home2'];
		$playersID[$row['home3']] = $row['home3'];
		$playersID[$row['home4']] = $row['home4'];
		$playersID[$row['home5']] = $row['home5'];
	
	}else{
		$query = "SELECT `away1`, `away2`, `away3`, `away4`, `away5` FROM `match` WHERE `matchID` = " . $matchID . ";";
		$result = $mysqli->query($query);
	
		$row = $result->fetch_assoc();
		
		$playersID[$row['away1']] = $row['away1'];
		$playersID[$row['away2']] = $row['away2'];
		$playersID[$row['away3']] = $row['away3'];
		$playersID[$row['away4']] = $row['away4'];
		$playersID[$row['away5']] = $row['away5'];
	}
	
	$players = [];
	
	foreach($playersID as $playerID){
		
		$query = "SELECT `name` FROM `player` WHERE ID = " . $playerID;
		$result = $mysqli->query($query);
		$row = $result->fetch_assoc();
		
		$players[$row['name']] = $playerID;
	}
	
	header("Content-Type: application/json");
    echo json_encode($players);
?>