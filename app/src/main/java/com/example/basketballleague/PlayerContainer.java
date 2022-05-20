package com.example.basketballleague;

public class PlayerContainer {

    private String name;
    private String id;
    private String position;
    private String teamID;
    private String photoURI;
    private String games;
    private String[] statsTable;

    public PlayerContainer(String name, String id, String position, String teamID, String photoURI, String games, String[] statsTable){
       this.name = name;
       this.id = id;
       this.position = position;
       this.teamID = teamID;
       this.photoURI = photoURI;
       this.games = games;
       this.statsTable = statsTable;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){ this.name = name; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPhotoURI() { return photoURI; }

    public void setPhotoURI(String photoURI) {
        this.photoURI = photoURI;
    }

    public String getGames() { return games; }

    public void setGames(String games) { this.games = games; }

    public String[] getStatsTable() {
        return statsTable;
    }

    public void setStatsTable(String[] statsTable) {
        this.statsTable = statsTable;
    }
}
