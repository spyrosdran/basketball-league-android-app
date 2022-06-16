<?php
    $host = "localhost";
    $username = "root";
    $password = "";
    $dbname = "basketleague";

    $leagueid = $_GET['leagueid'];

    if(isset($leagueid)){
        $standings = [];

        $mysqli = new mysqli($host, $username, $password, $dbname);

        if($mysqli->connect_errno){
            echo "Failed to connect to Database";
            exit();
        }

        //initialize data
        $query1 = "SELECT * FROM `team` WHERE leagueID = " . $leagueid;

        $result1 = $mysqli->query($query1);

        if($result1->num_rows > 0){
            while($row = $result1->fetch_assoc()){
                $standings[$row['name']]['Pos'] = 1;
                $standings[$row['name']]['badgeURI'] = $row['badgeURI'];
                $standings[$row['name']]['GP'] = 0;
                $standings[$row['name']]['Wins'] = 0;
                $standings[$row['name']]['Loses'] = 0;
                $standings[$row['name']]['WinPer'] = 0;
                $standings[$row['name']]['Pts'] = 0;
            }
        }

        //calculate Wins and Loses
        $query2 = "SELECT * FROM `match` WHERE `leagueID` = " . $leagueid . " AND `status` = \"ended\";";

        $result2 = $mysqli->query($query2);

        if($result2->num_rows > 0){
            while($row = $result2->fetch_assoc()){
                if($row['homePts'] > $row['awayPts']){
                    $standings[$row['homeID']]['GP']++;
                    $standings[$row['homeID']]['Wins']++;
                    $standings[$row['awayID']]['GP']++;
                    $standings[$row['awayID']]['Loses']++;
                }
                else{
                    $standings[$row['homeID']]['GP']++;
                    $standings[$row['homeID']]['Loses']++;
                    $standings[$row['awayID']]['GP']++;
                    $standings[$row['awayID']]['Wins']++;
                }
            }
        }
        

        //calculate WinPer and Pts
        foreach(array_keys($standings) as $name){
            if($standings[$name]['GP'] > 0){
                $standings[$name]['WinPer'] = $standings[$name]['Wins'] / $standings[$name]['GP'] * 100;
            }
            else{
                $standings[$name]['WinPer'] = 0;
            }

            $standings[$name]['Pts'] = (2 * $standings[$name]['Wins']) + $standings[$name]['Loses'];
        }

        //sort with Pts
        stable_uasort($standings, 'cmp');

        //update positions
        $pos = 1;
        foreach(array_keys($standings) as $name){
            $standings[$name]['Pos'] = $pos;
            $pos++;
        }
        

        header("Content-Type: application/json");
        echo json_encode($standings);

        $mysqli->close();
    }
    else{
        exit();
    }


    function stable_uasort(&$array, $cmp_function) {
        if(count($array) < 2) {
            return;
        }
        $halfway = count($array) / 2;
        $array1 = array_slice($array, 0, $halfway, TRUE);
        $array2 = array_slice($array, $halfway, NULL, TRUE);
    
        stable_uasort($array1, $cmp_function);
        stable_uasort($array2, $cmp_function);
        if(call_user_func($cmp_function, end($array1), reset($array2)) < 1) {
            $array = $array1 + $array2;
            return;
        }
        $array = array();
        reset($array1);
        reset($array2);
        while(current($array1) && current($array2)) {
            if(call_user_func($cmp_function, current($array1), current($array2)) < 1) {
                $array[key($array1)] = current($array1);
                next($array1);
            } else {
                $array[key($array2)] = current($array2);
                next($array2);
            }
        }
        while(current($array1)) {
            $array[key($array1)] = current($array1);
            next($array1);
        }
        while(current($array2)) {
            $array[key($array2)] = current($array2);
            next($array2);
        }
        return;
    }


    function cmp($a, $b) {
        if($a['Pts'] == $b['Pts']) {
            return 0;
        }
        return ($a['Pts'] > $b['Pts']) ? -1 : 1;
    }

?>