package com.example.basketballleague;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basketballleague.ui.TeamDetails.TeamDetail;
import com.example.basketballleague.ui.players.PlayerDetails;

import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class MatchViewAdapter extends RecyclerView.Adapter<MatchViewAdapter.MatchViewHolder> {

    Context context;
    private ArrayList<Match> matches = new ArrayList<>();
    View view;
    Intent local_intent;

    public MatchViewAdapter(Context context, ArrayList<Match> matches, Intent intent) {
        this.context = context;
        this.matches = matches;
        this.local_intent = intent;
    }

    @NonNull
    @Override
    public MatchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.match_card, parent, false);

        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchViewHolder holder, int position) {
        holder.bind(matches.get(position));

        //Setting up the OnClickListener
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = local_intent.getExtras().getString("type");
                if(type.equals("admin")){
                    Intent intent = new Intent(context, MatchAdministrationActivity.class);
                    Match match = matches.get(position);
                    intent.putExtra("matchID", match.getMatchID());
                    intent.putExtra("homeID", match.getHomeTeam());
                    intent.putExtra("awayID", match.getAwayTeam());
                    intent.putExtra("homeScore",Integer.toString(match.getHomeTeamScore()));
                    intent.putExtra("awayScore", Integer.toString(match.getAwayTeamScore()));
                    intent.putExtra("homeImageURI", match.getHomeImageURI());
                    intent.putExtra("awayImageURI", match.getAwayImageURI());
                    intent.putExtra("matchDate", match.getMatchDate());
                    intent.putExtra("matchLeague", match.getLeagueName());
                    intent.putExtra("status", match.getStatus());
                    view.getContext().startActivity(intent);
                } else if(type.equals("normal")){
                    Match match = matches.get(position);
                    String status = match.getStatus();
                    if(status.equals("live")){
                        //TODO: show quest live match details activity
                    } else if(status.equals("upcomming")){
                        //do nothing
                    } else if(status.equals("ended")){
                        //TODO: show quest match details activity
                    }

                    //for debugging
                    Toast.makeText(context, "You are a normal user, not an admin", Toast.LENGTH_SHORT).show();

                }

            }
        });
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
        TextView date;

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

            if (match.getStatus().equals("upcoming") || match.getStatus().equals("ended"))
                date.setText(match.getMatchDate());
            else {
                date.setText("Now Live >");
                date.setTextColor(Color.parseColor("#FF0000"));
            }

            try {
                URL imgURL = new URL(match.getHomeImageURI());
                Bitmap icon_val = BitmapFactory.decodeStream(imgURL.openConnection().getInputStream());
                homePhoto.setImageBitmap(Bitmap.createScaledBitmap(icon_val,40,40,false));
            } catch (Exception e) {
                e.printStackTrace();
            }

            homePhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent  = new Intent(view.getContext(), TeamDetail.class);
                    intent.putExtra("teamName", homeName.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });

            try {
                URL imgURL = new URL(match.getAwayImageURI());
                Bitmap icon_val = BitmapFactory.decodeStream(imgURL.openConnection().getInputStream());
                awayPhoto.setImageBitmap(Bitmap.createScaledBitmap(icon_val,40,40,false));
            } catch (Exception e) {
                e.printStackTrace();
            }

            awayPhoto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent  = new Intent(view.getContext(), TeamDetail.class);
                    intent.putExtra("teamName", awayName.getText().toString());
                    view.getContext().startActivity(intent);
                }
            });

        }
    }
}
