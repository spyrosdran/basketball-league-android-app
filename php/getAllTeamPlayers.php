<?php

	$team = $_GET['team'];
	
	$mysqli = new mysqli("localhost", "root", "", "basketleague");
	$query = "SELECT * FROM `player` WHERE `teamID` = '" . $team . "';";
	$result = $mysqli->query($query);
	
	$players = [];
	
	while($row = $result->fetch_assoc()){
		$players[$row['ID']]['name'] = $row['name'];
		$players[$row['ID']]['position'] = $row['position'];
		$players[$row['ID']]['teamID'] = $row['teamID'];
	}
	
	header("Content-Type: application/json");
    echo json_encode($players);
	
?>