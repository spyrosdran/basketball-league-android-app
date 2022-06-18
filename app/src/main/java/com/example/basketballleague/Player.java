package com.example.basketballleague;

public class Player {

    private int id;
    private String name;
    private String position;
    private String teamID;
    private String photoURL;
    private int freeThrows;
    private int points2;
    private int points3;
    private int rebounds;
    private int assists;
    private int shoots;
    private int games;
    private int steals;
    private int turnovers;
    private int blocks;
    private int fouls;
    private int cuts;
    private int mistakes;
    private int pivotFootMistake;
    private int selfPassMistake;
    private int highDribbleMistake;
    private int threeSecondViolationMistake;
    private int kickingTheBallMistake;

    public Player(int id, String name, String position, String teamID, String photoURL){
        this.id = id;
        this.name = name;
        this.position = position;
        this.teamID = teamID;
        this.photoURL = photoURL;
        this.freeThrows = 0;
        this.points2 = 0;
        this.points3 = 0;
        this.rebounds = 0;
        this.assists = 0;
        this.shoots = 0;
        this.games = 0;
        this.steals = 0;
        this.turnovers = 0;
        this.blocks = 0;
        this.fouls = 0;
        this.cuts = 0;
        this.mistakes = 0;
        this.pivotFootMistake = 0;
        this.selfPassMistake = 0;
        this.highDribbleMistake = 0;
        this.threeSecondViolationMistake = 0;
        this.kickingTheBallMistake = 0;
    }

    public Player(int id){
        //Local database
        String databaseIP = "192.168.1.129";
        //Load player
        String url = "http://" + databaseIP + "/basketleague/fillPLayerStats.php?playerID=" + id;

        try {
            OkHttpHandler okHttpHandler = new OkHttpHandler();
            PlayerContainer myPlayerContainer = okHttpHandler.loadPlayer(url);
            this.setUpPlayer(myPlayerContainer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setUpPlayer(PlayerContainer playerContainer){
        //Setup player's info
        this.id = Integer.parseInt(playerContainer.getId());
        this.name = playerContainer.getName();
        this.position = playerContainer.getPosition();
        this.teamID = playerContainer.getTeamID();
        this.photoURL = playerContainer.getPhotoURI();
        this.games = Integer.parseInt(playerContainer.getGames());

        //Setup player's stats
        String[] playersStats = playerContainer.getStatsTable();

        String type = "";
        String value = "";

        for(int i=0; i<playersStats.length; i++){
            type = playersStats[i].split(";")[0];
            value = playersStats[i].split(";")[1];

            switch (type){
                case "1pt":
                    this.freeThrows = Integer.parseInt(value);
                    this.shoots ++;
                    break;
                case "2pt":
                    this.points2 = Integer.parseInt(value);
                    this.shoots ++;
                    break;
                case "3pt":
                    this.points3 = Integer.parseInt(value);
                    this.shoots ++;
                    break;
                case "rebound":
                    this.rebounds = Integer.parseInt(value);
                    break;
                case "steal":
                    this.steals = Integer.parseInt(value);
                    break;
                case "turnover":
                    this.turnovers = Integer.parseInt(value);
                    break;
                case "assist":
                    this.assists = Integer.parseInt(value);
                    break;
                case "block":
                    this.blocks = Integer.parseInt(value);
                    break;
                case "foul":
                    this.fouls = Integer.parseInt(value);
                    break;
                case "cut":
                    this.cuts = Integer.parseInt(value);
                    break;
                case "mistake":
                    this.mistakes = Integer.parseInt(value);
                    break;
                case "pivot foot (mistake)":
                    this.pivotFootMistake = Integer.parseInt(value);
                    this.mistakes++;
                    break;
                case "self pass (mistake)":
                    this.selfPassMistake = Integer.parseInt(value);
                    this.mistakes++;
                    break;
                case "high dribble (mistak":
                    //Cropped name because type VARCHAR(20) on database
                    this.highDribbleMistake = Integer.parseInt(value);
                    this.mistakes++;
                    break;
                case "three second violati":
                    //Cropped name because type VARCHAR(20) on database
                    this.threeSecondViolationMistake = Integer.parseInt(value);
                    this.mistakes++;
                    break;
                case "kicking the ball (mi":
                    //Cropped name because type VARCHAR(20) on database
                    this.kickingTheBallMistake = Integer.parseInt(value);
                    this.mistakes++;
                    break;
                default:
                    break;
            }
        }
    }

    public double calculatePointPerGame(){
        return ((double) (this.freeThrows + this.points2*2 + this.points3*3))/this.games;
    }

    public double calculateReboundsPerGame(){
        return ((double) this.rebounds)/this.games;
    }

    public double calculateAssistsPerGame(){
        return ((double) this.assists)/this.games;
    }

    public double calculateShootsPerGame(){
        return ((double) this.shoots)/this.games;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void setMistakes(int mistakes) {
        this.mistakes = mistakes;
    }

    public int getPivotFootMistake() {
        return pivotFootMistake;
    }

    public void setPivotFootMistake(int pivotFootMistake) {
        this.pivotFootMistake = pivotFootMistake;
    }

    public int getSelfPassMistake() {
        return selfPassMistake;
    }

    public void setSelfPassMistake(int selfPassMistake) {
        this.selfPassMistake = selfPassMistake;
    }

    public int getHighDribbleMistake() {
        return highDribbleMistake;
    }

    public void setHighDribbleMistake(int highDribbleMistake) {
        this.highDribbleMistake = highDribbleMistake;
    }

    public int getThreeSecondViolationMistake() {
        return threeSecondViolationMistake;
    }

    public void setThreeSecondViolationMistake(int threeSecondViolationMistake) {
        this.threeSecondViolationMistake = threeSecondViolationMistake;
    }

    public int getKickTheBallMistake() {
        return kickingTheBallMistake;
    }

    public void setKickTheBallMistake(int kickTheBallMistake) {
        this.kickingTheBallMistake = kickTheBallMistake;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTeamID() {
        return teamID;
    }

    public void setTeamID(String teamID) {
        this.teamID = teamID;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public int getFreeThrows() {
        return freeThrows;
    }

    public void setFreeThrows(int freeThrows) {
        this.freeThrows = freeThrows;
    }

    public int getPoints2() {
        return points2;
    }

    public void setPoints2(int points2) {
        this.points2 = points2;
    }

    public int getPoints3() {
        return points3;
    }

    public void setPoints3(int points3) {
        this.points3 = points3;
    }

    public int getRebounds() {
        return rebounds;
    }

    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getShoots() {
        return shoots;
    }

    public void setShoots(int shoots) {
        this.shoots = shoots;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getFouls() {
        return fouls;
    }

    public void setFouls(int fouls) {
        this.fouls = fouls;
    }

    public int getCuts() {
        return cuts;
    }

    public void setCuts(int cuts) {
        this.cuts = cuts;
    }
}
