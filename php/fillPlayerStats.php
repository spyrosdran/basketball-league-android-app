<?php
	$data = array();

	$data_file_contents = file_get_contents("../data/credentials.txt");
	$u_p = explode(" ", $data_file_contents);
	
	$host=$u_p[0];
	$uname=$u_p[1];
	$pass=$u_p[2];
	$dbname=$u_p[3];
	
	$selectedID = $_GET['playerID'];
	
	$dbh = mysqli_connect($host,$uname,$pass) or die("cannot connect");
	mysqli_select_db($dbh, $dbname);
			
	$sqlCheck = "SELECT * FROM match_event WHERE playerID = " . $selectedID;
	$resultCheck = mysqli_query($dbh, $sqlCheck);
	
	if(mysqli_num_rows($resultCheck)==0){
		$sql = "SELECT * FROM player WHERE ID = " . $selectedID;
		$result = mysqli_query($dbh, $sql);
		while ($row = mysqli_fetch_array($result)) { 
			$temp = array();
			$temp['id'] = $row['ID'];
			$temp['position'] = $row['position'];
			$temp['teamID'] = $row['teamID'];
			$temp['photoURI'] = $row['photoURI'];
			$temp['games'] = 0;
			$temp['types'] = "";
			$data[$row['name']] = $temp;
		}
	}
	else{
		$sql = "SELECT B.ID, B.name, B.position, B.teamID, B.photoURI, A.games, B.types FROM (SELECT player.ID, player.name, COUNT(DISTINCT match_event.matchID) as games FROM match_event INNER JOIN player ON player.ID=match_event.playerID GROUP BY player.ID) as A INNER JOIN (SELECT A.ID, A.name, A.position, A.teamID, A.photoURI, GROUP_CONCAT(A.type,';',A.count) AS types FROM 	(SELECT player.ID, player.name, player.position, player.teamID, player.photoURI, match_event.type, COUNT(*) as count FROM match_event INNER JOIN player ON player.ID=match_event.playerID GROUP BY player.ID, match_event.type) as A GROUP BY A.name) as B ON A.ID = B.ID WHERE B.ID = " . $selectedID;
		$result = mysqli_query($dbh, $sql);
		while ($row = mysqli_fetch_array($result)) { 
			$temp = array();
			$temp['id'] = $row['ID'];
			$temp['position'] = $row['position'];
			$temp['teamID'] = $row['teamID'];
			$temp['photoURI'] = $row['photoURI'];
			$temp['games'] = $row['games'];
			$temp['types'] = $row['types'];
			$data[$row['name']] = $temp;
		}
	}
	

	header("Content-Type: application/json");
	echo json_encode($data);	
	mysqli_close($dbh);
?>