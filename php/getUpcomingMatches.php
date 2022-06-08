<?php

	$mysqli = new mysqli("localhost", "root", "", "basketleague");
	$query = "SELECT * FROM `match` WHERE `status` = 'upcoming'";
	$result = $mysqli->query($query);
	
	$matches = [];
	
	while($row = $result->fetch_assoc()){ 
			
			$matches[$row['matchID']]['homeID'] = $row['homeID'];
			$matches[$row['matchID']]['awayID'] = $row['awayID'];
			$matches[$row['matchID']]['homePts'] = $row['homePts'];
			$matches[$row['matchID']]['awayPts'] = $row['awayPts'];
			$matches[$row['matchID']]['startTime'] = $row['startTime'];
			$matches[$row['matchID']]['league'] = "Greek HEBA A1";
		
	}
	
	header("Content-Type: application/json");
    echo json_encode($matches);
?>