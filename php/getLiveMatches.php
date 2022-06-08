<?php
	
    $mysqli = new mysqli("localhost", "root", "", "basketleague");
	$query = "SELECT * FROM `match` WHERE `status` = 'live'";
	$result = $mysqli->query($query);
	
	$matches = [];
	
	while($row = $result->fetch_assoc()){ 
		
			$matches[$row['matchID']]['matchID'] = $row['matchID'];
			$matches[$row['matchID']]['homeID'] = $row['homeID'];
			$matches[$row['matchID']]['awayID'] = $row['awayID'];
			$matches[$row['matchID']]['homePts'] = $row['homePts'];
			$matches[$row['matchID']]['awayPts'] = $row['awayPts'];
			$matches[$row['matchID']]['startTime'] = $row['startTime'];
			$matches[$row['matchID']]['league'] = "Greek HEBA A1";
			$matches[$row['matchID']]['status'] = $row['status'];
			
			$q= "SELECT `badgeURI` FROM `team` WHERE `name` = '" . $row['homeID'] . "'";
			$r = $mysqli->query($q);
			$rowImage = $r->fetch_assoc();
			$homeImageURI = $rowImage['badgeURI'];
			
			$q= "SELECT `badgeURI` FROM `team` WHERE `name` = '" . $row['awayID'] . "'";
			$r = $mysqli->query($q);
			$rowImage = $r->fetch_assoc();
			$awayImageURI = $rowImage['badgeURI'];
			
			$matches[$row['matchID']]['homeImageURI'] = $homeImageURI;
			$matches[$row['matchID']]['awayImageURI'] = $awayImageURI;
		
	}
	
	header("Content-Type: application/json");
    echo json_encode($matches);
?>