<?php

	$player = $_GET['player'];

	$mysqli = new mysqli("localhost", "root", "", "basketleague");
	$query = "SELECT `teamID`, `position` FROM `player` WHERE `name` = '" . $player . "';";
	$result = $mysqli->query($query);
	$row = $result->fetch_assoc();
	
	$teamID = $row['teamID'];
	$position = $row['position'];
	
	$query = "SELECT `name` FROM `player` WHERE `teamID` = '" . $teamID ."' AND `position` = '" . $position . "' AND NOT `name` = '" . $player . "';";
	$result = $mysqli->query($query);
	
	$players = [];
	
	while($row = $result->fetch_assoc()) {
		$players[$row['name']] = $row['name'];
	}
	
	header("Content-Type: application/json");
    echo json_encode($players);
?>