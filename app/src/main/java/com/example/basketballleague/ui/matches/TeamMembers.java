package com.example.basketballleague.ui.matches;

import java.util.ArrayList;
import java.util.List;

import com.example.basketballleague.okHttpHandlerAdmin;

public class TeamMembers {
    private ArrayList<PlayerInCourt> players;

    public TeamMembers(String matchID, String homeaway){
        try {
            okHttpHandlerAdmin okHttpHandler = new okHttpHandlerAdmin();
            players = okHttpHandler.getInCourtTeamPlayers(matchID, homeaway);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*players = new ArrayList<String>();
        players.add("ANTETOKOUMBO");
        players.add("LEBRON");
        players.add("IRVING");
        players.add("O'NEIL");
        players.add("WESTBROOK");*/
    }


    public List<PlayerInCourt> getAllPlayers() {
        List<PlayerInCourt> temp = new ArrayList<PlayerInCourt>();
        for (int i=0; i<players.size(); i++) {
            temp.add(players.get(i));
        }
        return temp;
    }
}
