<?php

	$matchID = $_GET['matchID'];

	$mysqli = new mysqli("localhost", "root", "", "basketleague");
	$query = "SELECT `home1`, `home2`, `home3`, `home4`, `home5`, `away1`, `away2`, `away3`, `away4`, `away5` FROM `match` WHERE `matchID` = " . $matchID . ";";
	$result = $mysqli->query($query);
	
	$row = $result->fetch_assoc();
	$playersID = array();
	
	$playersID[$row['home1']] = $row['home1'];
	$playersID[$row['home2']] = $row['home2'];
	$playersID[$row['home3']] = $row['home3'];
	$playersID[$row['home4']] = $row['home4'];
	$playersID[$row['home5']] = $row['home5'];
	$playersID[$row['away1']] = $row['away1'];
	$playersID[$row['away2']] = $row['away2'];
	$playersID[$row['away3']] = $row['away3'];
	$playersID[$row['away4']] = $row['away4'];
	$playersID[$row['away5']] = $row['away5'];
	
	$players = [];
	
	foreach($playersID as $playerID){
		
		$query = "SELECT `name` FROM `player` WHERE ID = " . $playerID;
		$result = $mysqli->query($query);
		$row = $result->fetch_assoc();
		
		$players[$row['name']] = $row['name'];
			
	}
	
	header("Content-Type: application/json");
    echo json_encode($players);
?>