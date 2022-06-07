<?php

    $host = "localhost";
    $username = "root";
    $password = "";
    $dbname = "basketleagueapp";

    $matchID = $_GET['matchid'];
    $playerID = $_GET['playerid'];

    $statistics = [];

    //initialize counters
    $event_types = ['1pt', '2pt', '3pt', 'rebound', 'steal', 'turnover', 'assist', 'block', 'foul', 'cut', 'change in', 'change out', 'mistake'];
    $mistakes = ['pivot foot (mistake)', 'self pass (mistake)', 'high dribble (mistake)', 'three second violation (mistake)', 'kicking the ball (mistake)'];

    foreach($event_types as $type){
        $statistics[$type] = 0;
    }

    if(isset($matchID) and isset($playerID)){
        $mysqli = new mysqli($host, $username, $password, $dbname);

        if($mysqli->connect_errno){
            echo "Failed to connect to Database";
            exit();
        }

        $query = "SELECT * FROM match_event WHERE matchID = " . $matchID . " AND playerID = ". $playerID;

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