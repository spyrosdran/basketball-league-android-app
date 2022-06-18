package com.example.basketballleague.ui.TeamDetails;

import com.example.basketballleague.ui.home.HomeFragment;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//all the sql code
//retrieving from database

public class SqlforDb implements Runnable{

    private Team team = new Team("","","",0);
    private ArrayList<MatchListing> match = new ArrayList<>();
    private ArrayList<TeamPlayers> players = new ArrayList<>();
    private Statsperplayer playerStats = new Statsperplayer(0 ,0,0,0,0,0,0);
    private int maxID=-1000000;
    private int minID=1000000;


    public void run() {
        System.out.println("trying to read from database");
        Connection connection;

        try{
            //here connection from other team member needed to get team name and position
            /*
               this 2 methods will be used to give the 2 needed values for line #46
               if not then done manually , line #46

            public String getTeamname(){
                return teamname;
            }

            public int getCurrentposition(){
                return teamposition;
            }
            */

            String teamGotten ="Olympiacos";// (classname).getTeamname() ;
            System.out.println(teamGotten);
            Class.forName("org.mariadb.jdbc.Driver");
            // Connection to database
            // Change ip and port for correct conncetivity

            String ip = "192.168.2.3";
            String port = "3306";

            //ip="192.168.2.2";
            //port="34659";

            connection = DriverManager.getConnection("jdbc:mariadb://"+ip +":" + port+"/basketleagueapp","root","pass");

            //team
            try{
                String sql="SELECT * FROM team WHERE NAME='"+teamGotten+ "'";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery(sql);

                while(rs.next()){
                   team= new Team(rs.getString("name"),rs.getString("city"),rs.getString("badgeURI"),rs.getInt("leagueID"));
                }
            rs.close();

            }
            catch (SQLException s){
                System.out.println("SQL Statement is not executed");
                s.printStackTrace();
            }

            //match pre team
            try {
                String sql = "SELECT * FROM `match` WHERE homeID='"+teamGotten+"' OR awayID='"+teamGotten+"';";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery(sql);

                while(resultSet.next()){
                     match.add(new MatchListing(resultSet.getInt("matchID"),resultSet.getString("homeID"),resultSet.getString("awayID"),
                             resultSet.getInt("leagueID"),resultSet.getDate("startTime"),resultSet.getInt("homePts"),resultSet.getInt("awayPts")));
                }

                preparedStatement.close();
                resultSet.close();
            }
            catch (SQLException s) {
                System.out.println("SQL Statement is not executed!!");
                s.printStackTrace();
            }

            //player of the team
            try{
                String sql="SELECT * FROM player WHERE teamID='"+teamGotten+ "'";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet res = preparedStatement.executeQuery(sql);
                while(res.next()){
                   TeamPlayers player= new TeamPlayers(res.getInt("ID"),res.getString("name"),res.getString("position"),res.getString("teamID"),
                            res.getString("photoURI"));

                   if(maxID<res.getInt("ID")){
                       maxID=res.getInt("ID");
                   }

                    if(minID>res.getInt("ID")){
                        minID=res.getInt("ID");
                    }

                    players.add(player);
                }
                res.close();
            }
            catch (SQLException s){
                System.out.println("SQL Statement is not executed!");
                s.printStackTrace();
            }
            System.out.println(maxID);
            System.out.println(minID);

            //stats of players
            try{
                String sql="SELECT * FROM statistics WHERE playerID BETWEEN "+minID+" AND "+maxID;
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resu = preparedStatement.executeQuery(sql);

                while(resu.next()){
                    playerStats = new Statsperplayer(resu.getInt("playerID"),resu.getInt("points"),resu.getInt("rebounds"),
                            resu.getInt("steals"),resu.getInt("turnovers"),resu.getInt("assists"),resu.getInt("blocks"));
                }
                resu.close();
                connection.close();
            }
            catch (SQLException s){
                System.out.println("SQL Statement is not executed!!!");
                s.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //methods that return the values taken from database
    public Team takeTeam(){
        return team;
    }

    public ArrayList<MatchListing> getMatchess(){
        return match;
    }

    public Statsperplayer getPlayerStats(){
        return playerStats;
    }

    public ArrayList<TeamPlayers> getallplayers(){
        return players;
    }
}
