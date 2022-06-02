package com.example.basketballleague.ui.TeamDetails;

import android.net.Uri;

//class for players from gotten team
public class TeamPlayers {

    private int playerID;
    private String playername;
    private String playerposition;
    private String teamID;
    private String photoUrl;

    public TeamPlayers(int playerID, String playername, String playerposition , String teamID , String photoUrl){
        this.playerID=playerID;
        this.playername=playername;
        this.playerposition=playerposition;
        this.teamID=teamID;
        this.photoUrl=photoUrl;
    }

    public int getPlayerID(){
        return playerID;
    }

    public String getPlayername() {
        return playername;
    }

    public String getPlayerposition() {
        return playerposition;
    }

    public String getTeamID() {
        return teamID;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }
}
