<?php

    $host = "localhost";
    $username = "root";
    $password = "";
    $dbname = "basketleagueapp";

    $matchid = $_GET['matchid'];
    $teamid = $_GET['teamid'];

    $statistics = [];

    //initialize counters
    $event_types = ['1pt', '2pt', '3pt', 'rebound', 'steal', 'turnover', 'assist', 'block', 'foul', 'cut', 'change in', 'change out', 'mistake'];
    $mistakes = ['pivot foot (mistake)', 'self pass (mistake)', 'high dribble (mistake)', 'three second violation (mistake)', 'kicking the ball (mistake)'];

    foreach($event_types as $type){
        $statistics[$type] = 0;
    }

    if(isset($matchid) and isset($teamid)){
        $mysqli = new mysqli($host, $username, $password, $dbname);

        if($mysqli->connect_errno){
            echo "Failed to connect to Database";
            exit();
        }

        $query = "SELECT * FROM `match_event` INNER JOIN `player` ON `match_event`.playerID = `player`.ID WHERE `player`.teamID = " . $teamid .  "AND `match_event`.matchID = " . $matchid;

        $result = $mysqli->query($query);

        if($result->num_rows > 0){
            while($row = $result->fetch_assoc()){
                $type = $row['type'];
                if(in_array($type, $mistakes)){
                    $statistics['mistake']++;
                }
                else{
                    $statistics[$type]++;
                }
            }
        }
    }
    else{
        exit();
    }

    header("Content-Type: application/json");
    echo json_encode($statistics);

    

?>