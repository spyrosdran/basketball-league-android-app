package com.example.basketballleague;

import java.util.ArrayList;
import java.util.Date;

public class Match {

    private String matchID;
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private String matchDate;
    private String leagueName;
    private String status;
    private ArrayList<String> homePlayers;
    private ArrayList<String> awayPlayers;

    public Match(String matchID, String homeTeam, String awayTeam, int homeTeamScore, int awayTeamScore, String matchDate, String leagueName, String status, ArrayList<String> homePlayers, ArrayList<String> awayPlayers) {
        this.matchID = matchID;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.matchDate = matchDate;
        this.leagueName = leagueName;
        this.status = status;
        this.homePlayers = homePlayers;
        this.awayPlayers = awayPlayers;
    }

    public Match(String matchID, String homeTeam, String awayTeam, int parseInt, int homeTeamScore, String startTime, String league) {
        this.matchID = matchID;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
        this.matchDate = matchDate;
        this.leagueName = leagueName;
        this.status = status;
    }

    public String getMatchID() {
        return matchID;
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

    public String getMatchDate() {
        return matchDate;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public String getStatus() {
        return status;
    }

    public ArrayList<String> getHomePlayers() {
        return homePlayers;
    }

    public ArrayList<String> getAwayPlayers() {
        return awayPlayers;
    }
}
