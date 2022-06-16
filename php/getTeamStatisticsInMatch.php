<?php

    $host = "localhost";
    $username = "root";
    $password = "";
    $dbname = "basketleague";

    $matchid = $_GET['matchid'];
    $teamid = $_GET['teamid'];

    $statistics = [];

    $pt1 = 0;
    $pt1missed = 0;
    $pt2 = 0;
    $pt2missed = 0;
    $pt3 = 0;
    $pt3missed = 0;

    //initialize counters
    $event_types = ['1pt', '2pt', '3pt', '1pt missed', '2pt missed', '3pt missed', 'rebound', 'steal', 'turnover', 'assist', 'block', 'foul', 'cut', 'change in', 'change out', 'mistake'];
    $mistakes = ['pivot foot (mistake)', 'self pass (mistake)', 'high dribble (mistake)', 'three second violation (mistake)', 'kicking the ball (mistake)'];

    foreach($event_types as $type){
        if($type != "1pt missed" and $type != "2pt missed" and $type != "3pt missed") $statistics[$type] = 0;
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
                else if($type == '1pt'){
                    $pt1++;
                }
                else if($type == '1pt missed'){
                    $pt1missed++;
                }
                else if($type == '2pt'){
                    $pt2++;
                }
                else if($type == '2pt missed'){
                    $pt2missed++;
                }
                else if($type == '3pt'){
                    $pt3++;
                }
                else if($type == '3pt missed'){
                    $pt3missed++;
                }
                else{
                    $statistics[$type]++;
                }
            }

            if($pt1 > 0 or $pt1missed > 0) $statistics['1pt'] = $pt1/($pt1 + $pt1missed) * 100;
            if($pt2 > 0 or $pt2missed > 0) $statistics['2pt'] = $pt2/($pt2 + $pt2missed) * 100;
            if($pt3 > 0 or $pt3missed > 0) $statistics['3pt'] = $pt3/($pt3 + $pt3missed) * 100;
        }
    }
    else{
        exit();
    }

    header("Content-Type: application/json");
    echo json_encode($statistics);

    

?>