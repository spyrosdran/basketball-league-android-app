<?php
    $host = "localhost";
    $username = "root";
    $password = "";
    $dbname = "basketleague";

    $matchid = $_GET['matchID'];
    $content = $_GET['content'];

    if(isset($content) and isset($matchid)){
        $mysqli = new mysqli($host, $username, $password, $dbname);

        if($mysqli->connect_errno){
            echo "Failed to connect to Database";
            exit();
        }

        $datetime = date('Y-m-d h:i:s');
        $sql = "INSERT INTO `comment`(`matchID`, `content`, `timestamp`) VALUES({$matchid}, {$content}, '{$datetime}')";

        header("Content-Type: text/plain");

        if($mysqli->query($sql) == true){
            echo "Comment added successfully";
        }
        else{
             echo "Database Error";
        }
    }


?>