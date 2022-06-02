package com.example.basketballleague.ui.TeamDetails.sndFragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.basketballleague.R;
import com.example.basketballleague.ui.TeamDetails.TeamDetail;
import com.example.basketballleague.ui.TeamDetails.TeamPlayers;
import com.example.basketballleague.ui.players.PlayerDetails;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

//sub class used for every player in the listview
public class RosterAdapter extends ArrayAdapter<TeamPlayers> {

    private Context context;
    private int result;

    public RosterAdapter( Context context,int result, ArrayList<TeamPlayers> players) {
        super(context,result,players);
        this.context=context;
        this.result=result;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater =  LayoutInflater.from(context);

        convertView =  layoutInflater.inflate(result, parent, false);


        ImageView playerImage = convertView.findViewById(R.id.player_image);
        Glide.with(context).load(getItem(position).getPhotoUrl()).into(playerImage);

        TextView playername =  convertView.findViewById(R.id.playernametxt);
        playername.setText(getItem(position).getPlayername());

        TextView pposition =  convertView.findViewById(R.id.playerposition);
        pposition.setText(" Position: "+getItem(position).getPlayerposition());


        return convertView;
    }

}
