package com.example.basketballleague.ui.TeamDetails;

//all stats per player
public class Statsperplayer {

    private int playerID;
    private int points;
    private int rebounds;
    private int steals;
    private int turnovers;
    private int assists;
    private int blocks;

    public Statsperplayer(int playerID, int points , int rebounds , int steals,int turnovers , int assists , int blocks){
        this.playerID=playerID;
        this.points=points;
        this.rebounds=rebounds;
        this.steals=steals;
        this.turnovers=turnovers;
        this.assists=assists;
        this.blocks=blocks;
    }

    public int getPlayerID(){
        return playerID;
    }

    public int getPoints(){
        return points;
    }

    public int getRebounds(){
        return rebounds;
    }

    public int getSteals(){
        return steals;
    }

    public int getAssists(){
        return assists;
    }

    public int getBlocks(){
        return blocks;
    }

    public int getTurnovers(){
        return turnovers;
    }
}
