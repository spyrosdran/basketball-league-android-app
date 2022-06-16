<?php
    $uname = $_POST['uname'];
    $pass = $_POST['pass'];
    $host = "localhost";
    $username = "root";
    $password = "";
    $dbname = "basketleague";


    if(isset($uname) and isset($pass)){
        $mysqli = new mysqli($host, $username, $password, $dbname);

        if($mysqli->connect_errno){
            echo "Failed to connect to Database";
            exit();
        }
        

        $query = "SELECT * FROM `user` WHERE `user`.`username`={$uname} AND `user`.`password`={$pass}";
        
        $found = false;
        $result = $mysqli->query($query);
        $row = NULL;
        if($result->num_rows > 0){
            if($row = $result->fetch_assoc()){
                $found = true;
            }
        }

        if($found) echo $row['type'];
        else echo "Not an existing user";
    }
?>