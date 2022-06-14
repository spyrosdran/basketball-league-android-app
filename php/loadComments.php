<?php
    $host = "localhost";
    $username = "root";
    $password = "";
    $dbname = "basketleague";

    $matchid = $_GET['matchID'];

    $comments = []; 

    if(isset($matchid)){
        $mysqli = new mysqli($host, $username, $password, $dbname);

        if($mysqli->connect_errno){
            echo "Failed to connect to Database";
            exit();
        }

        $query = "SELECT * FROM `comment` WHERE `matchID`={$matchid};";

        $result = $mysqli->query($query);

        if($result->num_rows > 0){
            while($row = $result->fetch_assoc()){
                $content = $row['content'];
                $timestamp = $row['timestamp'];
                $id = $row['id'];
                $comments[$id]['timestamp'] = $timestamp;
                $comments[$id]['content'] = $content;
            }
            
            header("Content-Type: application/json");
            echo json_encode($comments);
        }
        else{
            header("Content-Type: text/plain");
            echo "No comments for this match";
        }
    }
?>