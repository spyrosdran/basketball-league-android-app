package com.example.basketballleague.ui.TeamDetails;

import android.content.Intent;

import java.util.ArrayList;

//needed for data connection
public class DataStatsConnection{

    private String teamname;
    private Team team=new Team("","",null,0);
    private ArrayList<MatchListing> match = new ArrayList<>();
    private ArrayList<TeamPlayers> players = new ArrayList<>();
    private Statsperplayer playerStats = new Statsperplayer(0,0,0,0,0,0,0);
    private int totalWins=0;
    private int totalLoses=0;
    private int totalHomepoints=0;
    private int totalAwaypoints=0;
    private int pointsPerGame=0;
    private int totalpoints=0;
    private int totalrebounds=0;
    private int totalassists=0;
    private int totalblocks=0;
    private int totalsteals=0;
    private int totalTurnovers=0;
    Intent intent;


    public DataStatsConnection(String teamName){

        //threads used for faster results
        SqlforDb dbconnection = new SqlforDb(teamName);
        Thread t = new Thread(dbconnection);//teamname);
        try{
            t.start();
            t.join();
        }catch (Exception e){
            e.printStackTrace();
        }
        team = dbconnection.takeTeam();

        match=dbconnection.getMatchess();

        playerStats = dbconnection.getPlayerStats();

        players = dbconnection.getallplayers();
    }


    //return methods for different values in app
    public Team getTeam(){
       return team;
    }

    public ArrayList<MatchListing> getMatchess(){ return match;}

    public ArrayList<TeamPlayers> getplayers(){
        return players;
    }

    //calculation of team stats
    public void calculateStats(){
        for(int i=0 ;i <match.size();i++){
          if(match.get(i).getMatchid().equals(team.getName())) {
              totalHomepoints+=match.get(i).getHomePoints();
              if(match.get(i).getHomePoints()>match.get(i).getAwaypoints()){
                  totalWins+=1;
              }
              else
                  totalLoses+=1;
          }
          else
          {
              totalAwaypoints+=match.get(i).getAwaypoints();
              if(match.get(i).getHomePoints()<match.get(i).getAwaypoints()){
                 totalWins+=1;
          }
          else
              totalLoses+=1;
          }
        }
    }

    public void calculateTeamStats(){
      totalpoints+=  playerStats.getPoints();
      totalrebounds+=  playerStats.getRebounds();
      totalassists+=  playerStats.getAssists();
      totalblocks+=  playerStats.getBlocks();
      totalsteals+=  playerStats.getSteals();
      totalTurnovers+= playerStats.getTurnovers();
    }

    public int getTotalWins(){
        return totalWins;
    }
    public int getTotalLoses(){
        return totalLoses;
    }
    public int getTotalHomepoints(){
        return totalHomepoints;
    }
    public int getTotalAwaypoints(){
        return totalAwaypoints;
    }
    public int getPointsPerGame(){
        return (totalAwaypoints+totalHomepoints)/match.size();
    }

    public String getMatchesLenght(){
        return String.valueOf(match.size());
    }

    public int getTotalpoints(){
        return totalpoints/match.size();
    }
    public int getTotalrebounds(){
        return totalrebounds/match.size();
    }
    public int getTotalassists(){
        return totalassists/match.size();
    }
    public int getTotalblocks(){
        return totalblocks/match.size();
    }
    public int getTotalsteals(){
        return totalsteals/match.size();
    }

    public int getTotalTurnovers(){
        return totalTurnovers/match.size();
    }
}
