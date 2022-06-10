package com.example.basketballleague.ui.matches;

public class PlayerInCourt {
    private int playerID;
    private String playerName;
    private String position;
    private String teamID;

    public PlayerInCourt(int id, String name) {
        playerID = id;
        playerName = name;
        teamID = "";
        position = "";
    }

    public PlayerInCourt(int playerID, String playerName, String position, String teamID) {
        this.playerID = playerID;
        this.playerName = playerName;
        this.position = position;
        this.teamID = teamID;
    }

    public int getPlayerID() {
        return playerID;
    }

    public String getPosition() {
        return position;
    }

    public String getTeamID() {
        return teamID;
    }

    public String getPlayerName() {
        return playerName;
    }
}
