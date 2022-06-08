package com.example.basketballleague.ui.matches;

public class PlayerInCourt {
    private int playerID;
    private String playerName;
    private String position;
    private int teamID;

    public PlayerInCourt(int id, String name){
        playerID = id;
        playerName = name;
        teamID = -1;
        position = " ";
    }

    public String getPlayerName(){
        return playerName;
    }
}
