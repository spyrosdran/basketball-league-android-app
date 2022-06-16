<?php

    $host = "localhost";
    $username = "root";
    $password = "";
    $dbname = "basketleague";

    $playerID = $_GET['playerid'];

    $statistics_all = [];
    $statistics_avg = [];

    
    $event_types = ['1pt', '2pt', '3pt', '1pt missed', '2pt missed', '3pt missed', 'rebound', 'steal', 'turnover', 'assist', 'block', 'foul', 'cut', 'change in', 'change out', 'mistake'];
    $mistakes = ['pivot foot (mistake)', 'self pass (mistake)', 'high dribble (mistake)', 'three second violation (mistake)', 'kicking the ball (mistake)'];
    
    //initialize statistics avg
    foreach($event_types as $type){
        if($type != "1pt missed" and $type != "2pt missed" and $type != "3pt missed") $statistics_avg[$type] = 0;
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

                $pt1 = 0;
                $pt1missed = 0;
                $pt2 = 0;
                $pt2missed = 0;
                $pt3 = 0;
                $pt3missed = 0;

                if(in_array($type, $mistakes)){
                    if(isset($statistics_all[$matchid]['mistake'])) $statistics_all[$matchid]['mistake']++;
                    else $statistics_all[$matchid]['mistake'] = 1;
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
                    if(isset($statistics_all[$matchid][$type])) $statistics_all[$matchid][$type]++;
                    else $statistics_all[$matchid][$type] = 1;
                }

                if($pt1 > 0 or $pt1missed > 0) $statistics_all[$matchid]['1pt'] = $pt1/($pt1 + $pt1missed) * 100;
                if($pt2 > 0 or $pt2missed > 0) $statistics_all[$matchid]['2pt'] = $pt2/($pt2 + $pt2missed) * 100;
                if($pt3 > 0 or $pt3missed > 0) $statistics_all[$matchid]['3pt'] = $pt3/($pt3 + $pt3missed) * 100;
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
            if(isset($statistics_avg[$type])){
                $statistics_avg[$type] /= $count;
            }
        }

    }
    else{
        exit();
    }

    header("Content-Type: application/json");
    echo json_encode($statistics_avg);

    

?>