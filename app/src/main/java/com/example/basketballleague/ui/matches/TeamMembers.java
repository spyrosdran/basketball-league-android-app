package com.example.basketballleague.ui.matches;

import java.util.ArrayList;
import java.util.List;

public class TeamMembers {
    private ArrayList<String> players;

    public TeamMembers(/*String homeaway*/){
        /*
        String url= "http://"";

        try {
            OkHttpHandlerAdmin okHttpHandler = new OkHttpHandlerAdmin();
            players = okHttpHandler.getInCourtTeamPlayers(url,homeaway);
        } catch (Exception e) {
            e.printStackTrace();
        }

        */
        players = new ArrayList<String>();
        players.add("ANTETOKOUMBO");
        players.add("LEBRON");
        players.add("IRVING");
        players.add("O'NEIL");
        players.add("WESTBROOK");
    }

    public TeamMembers(String matchID){

    }

    public List<String> getAllPlayers() {
        List<String> temp = new ArrayList<String>();
        for (int i=0; i<players.size(); i++) {
            temp.add(players.get(i));
        }
        return temp;
    }
}
