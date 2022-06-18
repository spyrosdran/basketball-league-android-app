package com.example.basketballleague.ui.TeamDetails.sndFragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.basketballleague.Player;
import com.example.basketballleague.R;
import com.example.basketballleague.ui.TeamDetails.DataStatsConnection;
import com.example.basketballleague.ui.TeamDetails.TeamPlayers;
import com.example.basketballleague.ui.players.PlayerDetails;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

//the second fragment for showing roster of the team
public class RosterTeam extends Fragment {


    private ArrayList<TeamPlayers> players = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_roster_team, container, false);

        Bundle b = getActivity().getIntent().getExtras();
        String teamName = b.getString("teamName");

        //connection from db to take players from  teamgotten
        DataStatsConnection db = new DataStatsConnection(teamName);

         players = db.getplayers();

        //Sorting players by name
        Collections.sort(players, new Comparator<TeamPlayers>() {
           @Override
           public int compare(TeamPlayers teamPlayers, TeamPlayers t1) {
               return teamPlayers.getPlayername().compareTo(t1.getPlayername());
           }
       });

        //putting players in listview
        //Dynamically
        RosterAdapter rosterAdapter =  new RosterAdapter(getActivity(), R.layout.player_list , players);
        ListView listView = rootView.findViewById(R.id.listview);

        listView.setAdapter(rosterAdapter);

        //on listview click listener
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>parent , View view , int position, long id) {
                //System.out.println("Player ID: "+players.get(position).getPlayerID());

                //calling activity pa
                Intent intent = new Intent(getActivity(), PlayerDetails.class);
                intent.putExtra("playerID",players.get(position).getPlayerID());
                startActivity(intent);
            }
        });

        return  rootView;
    }
}