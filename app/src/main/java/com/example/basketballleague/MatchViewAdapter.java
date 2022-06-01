package com.example.basketballleague;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;

public class MatchViewAdapter extends RecyclerView.Adapter<MatchViewAdapter.MatchViewHolder> {

    Context context;
    private ArrayList<Match> matches = new ArrayList<>();

    public MatchViewAdapter(Context context, ArrayList<Match> matches) {
        this.context = context;
        this.matches = matches;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.match_card, parent, false);

//        View view = LayoutInflater.from(context).inflate(R.layout.match_card, parent, false);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        holder.bind(matches.get(position));
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }


    //Setting up the individual match card layout
    public class MatchViewHolder extends RecyclerView.ViewHolder {

        TextView homeName;
        TextView awayName;
        TextView homeScore;
        TextView awayScore;
        ImageView homePhoto;
        ImageView awayPhoto;
        TextView leagueName;
        Button date;

        public MatchViewHolder(@NonNull View itemView) {
            super(itemView);

            homeName = itemView.findViewById(R.id.home_team_name);
            awayName = itemView.findViewById(R.id.away_team_name);
            homeScore = itemView.findViewById(R.id.home_team_score);
            awayScore = itemView.findViewById(R.id.away_team_score);
            homePhoto = itemView.findViewById(R.id.home_team_image);
            awayPhoto = itemView.findViewById(R.id.away_team_image);
            leagueName = itemView.findViewById(R.id.league_name);
            date = itemView.findViewById(R.id.details_button);

        }

        public void bind(Match match) {
            homeName.setText(match.getHomeTeam());
            awayName.setText(match.getAwayTeam());
            homeScore.setText(Integer.toString(match.getHomeTeamScore()));
            awayScore.setText(Integer.toString(match.getAwayTeamScore()));
            leagueName.setText(match.getLeagueName());


        }
    }
}
