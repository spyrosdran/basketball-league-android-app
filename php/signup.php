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
        

        $query = "SELECT * FROM `user` WHERE `user`.`username`={$uname} AND `user`.`type`=\"normal\"";

        $found = false;
        $result = $mysqli->query($query);
        $row = NULL;
        if($result->num_rows > 0){
            if($row = $result->fetch_assoc()){
                $found = true;
            }
        }

        if($found == false){
            //TODO: insert new user to db
            $sql = "INSERT INTO `user`(`username`, `password`, `type`) VALUES ({$uname}, {$pass}, \"normal\")";
            if($mysqli->query($sql) == true){
                echo "Sign up successfully";
            }
            else{
                echo "Database error";
            }
        }
        else echo "User already exists";
    }
?>