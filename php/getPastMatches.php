<?php
	
    $mysqli = new mysqli("localhost", "root", "", "basketleague");
	$query = "SELECT * FROM `match` WHERE `status` = 'ended'";
	$result = $mysqli->query($query);
	
	$matches = [];
	
	while($row = $result->fetch_assoc()){ 
		
			$matches[$row['matchID']]['matchID'] = $row['matchID'];
			$matches[$row['matchID']]['homeID'] = $row['homeID'];
			$matches[$row['matchID']]['awayID'] = $row['awayID'];
			$matches[$row['matchID']]['homePts'] = $row['homePts'];
			$matches[$row['matchID']]['awayPts'] = $row['awayPts'];
			$matches[$row['matchID']]['startTime'] = $row['startTime'];
			$matches[$row['matchID']]['leagueID'] = "Greek HEBA A1";
			$matches[$row['matchID']]['status'] = $row['status'];
		
	}
	
	header("Content-Type: application/json");
    echo json_encode($matches);
?>