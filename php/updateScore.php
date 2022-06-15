<?php
    $host = "localhost";
    $username = "root";
    $password = "";
    $dbname = "basketleague";

    $matchid = $_GET['matchID'];
    $teamScored = $_GET['teamScored'];
    $points = $_GET['points'];

    if(isset($matchid) and isset($teamScored) and isset($points)){
        $mysqli = new mysqli($host, $username, $password, $dbname);

        if($mysqli->connect_errno){
            echo "Failed to connect to Database";
            exit();
        }

        if($teamScored == "HOME"){
            $changeCol = "homePts";
        }else{
            $changeCol = "awayPts";
        }

        $sql = "UPDATE `match` SET `{$changeCol}`=`{$changeCol}` + {$points} WHERE `matchID`={$matchid};";

        header("Content-Type: text/plain");

        if($mysqli->query($sql) == true){
            echo "Score updated successfully";
        } else{
            echo "Database error";
        }
    }
?>