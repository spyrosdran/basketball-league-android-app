package com.example.basketballleague.ui.TeamDetails.sndFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basketballleague.R;
import com.example.basketballleague.ui.TeamDetails.DataStatsConnection;


//the first fragment showing team stats

public class TeamStatisticsFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_team_statistics, container, false);
        DataStatsConnection db = new DataStatsConnection();

        TextView txtGp = rootView.findViewById(R.id.GPtxt);
        String t = db.getMatchesLenght();
        txtGp.setText("Games Played: "+t);

        //calculation of team stats from team
        db.calculateStats();
        db.calculateTeamStats();

        //Text views values set Total Wins/loses/home/away points/points per game/etc
        TextView wtxt= rootView.findViewById(R.id.WinTxt);
        int wintext=db.getTotalWins();
        wtxt.setText("Total wins: "+String.valueOf(wintext));

        TextView ltxt = rootView.findViewById(R.id.LoseTxt);
        int losestxt = db.getTotalLoses();
        ltxt.setText("Total loses: "+ String.valueOf(losestxt));

        TextView htxt = rootView.findViewById(R.id.hometxt);
        int hometxt=db.getTotalHomepoints();
        htxt.setText("Home points: "+ String.valueOf(hometxt));

        TextView gtxt = rootView.findViewById(R.id.GuestTxt);
        int guesttxt=db.getTotalAwaypoints();
        gtxt.setText("Guest points: "+String.valueOf(guesttxt));

        TextView ptxt = rootView.findViewById(R.id.PointsTxt);
        int pointsPerGame=db.getPointsPerGame();
        ptxt.setText("Points Per Game: "+String.valueOf(pointsPerGame));

        TextView atxt = rootView.findViewById(R.id.AssistsTxt);
        int assiststxt=db.getTotalassists();
        atxt.setText("Assists/game: "+String.valueOf(assiststxt));

        TextView rbtxt = rootView.findViewById(R.id.ReboundsTxt);
        int rebtxt=db.getTotalrebounds();
        rbtxt.setText("Rebounds/game: "+String.valueOf(rebtxt));

        TextView btxt = rootView.findViewById(R.id.BlocksTxt);
        int bltxt=db.getTotalblocks();
        btxt.setText("Blocks/game: "+String.valueOf(bltxt));

        TextView stxt = rootView.findViewById(R.id.turnovers);
        int stealstxt=db.getTotalsteals();
        stxt.setText("Steals/game: "+String.valueOf(stealstxt));

        TextView tutxt = rootView.findViewById(R.id.turnovers);
        int turntxt=db.getTotalTurnovers();
        tutxt.setText("Total turnovers: "+String.valueOf(turntxt));

        return rootView;
    }
}