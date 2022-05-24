package com.example.basketballleague.ui.TeamDetails;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.basketballleague.R;
import com.example.basketballleague.ui.TeamDetails.sndFragments.RosterTeam;
import com.example.basketballleague.ui.TeamDetails.sndFragments.TeamStatisticsFragment;

public class TeamDetail extends AppCompatActivity {

    private Button statsbtn;
    private Button Rosterbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);

        statsbtn = findViewById(R.id.btnStat);
        statsbtn.setBackgroundColor(getResources().getColor(R.color.orangeForeground));

        Rosterbtn = findViewById(R.id.btnroster);

        statsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                statsbtn.setBackgroundColor(getResources().getColor(R.color.orangeForeground));
                Rosterbtn.setBackgroundColor(getResources().getColor(R.color.backgroundColor));
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, new TeamStatisticsFragment()).commit();
            }
        });

        Rosterbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Rosterbtn.setBackgroundColor(getResources().getColor(R.color.orangeForeground));
                statsbtn.setBackgroundColor(getResources().getColor(R.color.backgroundColor));

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, new RosterTeam()).commit();
               // statsbtn.setVisibility(View.GONE);
            }
        });
    }
}