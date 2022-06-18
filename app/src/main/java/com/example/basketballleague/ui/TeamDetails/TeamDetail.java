package com.example.basketballleague.ui.TeamDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.basketballleague.R;
import com.example.basketballleague.databinding.ActivityTeamDetailBinding;
import com.example.basketballleague.ui.TeamDetails.sndFragments.RosterTeam;
import com.example.basketballleague.ui.TeamDetails.sndFragments.TeamStatisticsFragment;
import com.example.basketballleague.ui.home.HomeFragment;

public class TeamDetail extends AppCompatActivity {

    private Button statsbtn;
    private Button Rosterbtn;
    private DataStatsConnection db = new DataStatsConnection();
    private  TextView  txtPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);


        //Load for team

        //back btn
        Button backbtn = findViewById(R.id.backbtn);
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //attributes setters
        TextView txtTeamName = findViewById(R.id.NameTeam);
        String teamname= db.getTeam().getName();
        txtTeamName.setText(teamname);

        TextView  txtLocation = findViewById(R.id.Location);
        String s = db.getTeam().getLocation();
        txtLocation.setText(s);


        //here connection from other team member needed to get team name and position
        //if not then done manually
        int currentposion = 1;// (classname).getCurrentposition

        txtPosition= findViewById(R.id.CurrentPosition);
        txtPosition.setText("Current position: "+currentposion);

        switch (currentposion){
            case 1:
                txtPosition.append("st");
                break;
            case 2:
                txtPosition.append("nd");
                break;
            case 3:
                txtPosition.append("rd");
                break;
            default:txtPosition.append("th");
        }

        String IconUrl = db.getTeam().getUrl();
        ImageView Icon = findViewById(R.id.TeamIcon);
        Glide.with(TeamDetail.this).load(IconUrl).into(Icon);

        //Stats and Roster btns on click
        statsbtn = findViewById(R.id.btnStat);
        statsbtn.setBackgroundColor(getResources().getColor(R.color.orangeForeground));

        Rosterbtn = findViewById(R.id.btnroster);
        Rosterbtn.setBackgroundColor(getResources().getColor(R.color.grey));

        statsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                statsbtn.setBackgroundColor(getResources().getColor(R.color.orangeForeground));
                Rosterbtn.setBackgroundColor(getResources().getColor(R.color.grey));
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, new TeamStatisticsFragment()).commit();
            }
        });

        Rosterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Rosterbtn.setBackgroundColor(getResources().getColor(R.color.orangeForeground));
                statsbtn.setBackgroundColor(getResources().getColor(R.color.grey));

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, new RosterTeam()).commit();
            }
        });

    }
}