package com.example.basketballleague;

import java.util.Date;

public class Match {

    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private String matchDate;
    private String leagueName;

    public Match(String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore, String matchDate, String leagueName) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.matchDate = matchDate;
        this.leagueName = leagueName;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

//    public String getHomeTeamPhoto() {
//        return homeTeamPhoto;
//    }
//
//    public String getAwayTeamPhoto() {
//        return awayTeamPhoto;
//    }

    public String getMatchDate() {
        return matchDate;
    }

    public String getLeagueName(){
        return leagueName;
    }
}
