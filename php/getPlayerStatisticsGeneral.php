<?php

    $host = "localhost";
    $username = "root";
    $password = "";
    $dbname = "basketleagueapp";

    $playerID = $_GET['playerid'];

    $statistics_all = [];
    $statistics_avg = [];

    
    $event_types = ['1pt', '2pt', '3pt', 'rebound', 'steal', 'turnover', 'assist', 'block', 'foul', 'cut', 'change in', 'change out', 'mistake'];
    $mistakes = ['pivot foot (mistake)', 'self pass (mistake)', 'high dribble (mistake)', 'three second violation (mistake)', 'kicking the ball (mistake)'];
    
    //initialize statistics avg
    foreach($event_types as $type){
        $statistics_avg[$type] = 0;
    }

    if(isset($playerID)){
        $mysqli = new mysqli($host, $username, $password, $dbname);

        if($mysqli->connect_errno){
            echo "Failed to connect to Database";
            exit();
        }

        $query = "SELECT * FROM match_event WHERE playerID = ". $playerID;

        $result = $mysqli->query($query);

        if($result->num_rows > 0){
            while($row = $result->fetch_assoc()){
                $matchid = $row['matchID'];
                $type = $row['type'];
                if(in_array($type, $mistakes)){
                    if(isset($statistics_all[$matchid]['mistake'])) $statistics_all[$matchid]['mistake']++;
                    else $statistics_all[$matchid]['mistake'] = 1;
                }
                else{
                    if(isset($statistics_all[$matchid][$type])) $statistics_all[$matchid][$type]++;
                    else $statistics_all[$matchid][$type] = 1;
                }
            }
        }


        //get avg of all match statistics
        $count = 0;
        foreach(array_keys($statistics_all) as $match){
            foreach($event_types as $type){
                if(isset($statistics_all[$match][$type])) $statistics_avg[$type] += $statistics_all[$match][$type];
            }
            $count++;
        }

        foreach($event_types as $type){
            $statistics_avg[$type] /= $count;
        }

    }
    else{
        exit();
    }

    header("Content-Type: application/json");
    echo json_encode($statistics_avg);

    

?>