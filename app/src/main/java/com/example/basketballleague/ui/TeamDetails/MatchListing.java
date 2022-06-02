package com.example.basketballleague.ui.TeamDetails;

import java.util.Date;

//class for every match of team gotten
public class MatchListing {

    private int matchid;
    private String homeid;
    private String awayid;
    private int leagueid;
    private Date starttime;
    private int homePoints;
    private int Awaypoints;

    public MatchListing(int matchid,String homeid,String awayid,int leagueid,Date starttime,int homePoints, int Awaypoints){
        this.matchid=matchid;
        this.homeid=homeid;
        this.awayid=awayid;
        this.leagueid=leagueid;
        this.starttime=starttime;
        this.homePoints=homePoints;
        this.Awaypoints=Awaypoints;
    }

    public String getMatchid(){
        return homeid;
    }

    public int getHomePoints(){
        return homePoints;
    }

    public int getAwaypoints(){
        return Awaypoints;
    }

}
