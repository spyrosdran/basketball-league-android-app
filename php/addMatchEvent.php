<?php
     $host = "localhost";
     $username = "root";
     $password = "";
     $dbname = "basketleague";

     $type = $_POST['type'];
     $playerid = $_POST['playerid'];
     $matchid = $_POST['matchid'];
     $minute = $_POST['minute'];

     if(isset($type) and isset($playerid) and isset($matchid) and isset($minute)){
          $mysqli = new mysqli($host, $username, $password, $dbname);

          if($mysqli->connect_errno){
               echo "Failed to connect to Database";
               exit();
           }

           $sql = "INSERT INTO `match_event`(`playerID`, `matchID`, `type`, `minute`) VALUES ({$playerid}, {$matchid}, {$type}, {$minute});";

           header("Content-Type: text/plain");

           if($mysqli->query($sql) == true){
               echo "Match Event added successfully";
           }
           else{
                echo "Database Error";
           }
     }
?>