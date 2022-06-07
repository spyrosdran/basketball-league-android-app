package com.example.basketballleague;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.basketballleague.ui.matches.AdminLineUpFragment;
import com.example.basketballleague.ui.matches.AdminLiveCommentaryFragment;
import com.example.basketballleague.ui.matches.AdminMatchDetailsFragment;
import com.example.basketballleague.ui.matches.TeamMembers;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.util.ArrayList;

public class MatchAdministrationActivity extends AppCompatActivity {

    private ArrayList<String> homeTeam = new ArrayList<>();
    private ArrayList<String> awayTeam = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_admin);

        Intent intent = getIntent();
        String score = intent.getStringExtra("homeScore") + " - " + intent.getStringExtra("awayScore");
        TextView scoreView = findViewById(R.id.score);
        scoreView.setText(score);

        okHttpHandlerAdmin okHttpHandlerAdmin = new okHttpHandlerAdmin();
        String matchID = intent.getStringExtra("matchID");
        //String myIP = "192.168.1.2";
        //String url = "http://" + myIP + "/basketleague/getPlayersData.php?matchID=" + matchID;
        ArrayList<String> allPlayers = new ArrayList<>();

        try {
            allPlayers = okHttpHandlerAdmin.getPlayersData(intent.getStringExtra("matchID"));
            Log.d("My App","Successful http request for players");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0; i<5; i++){
            homeTeam.add(allPlayers.get(i));
        }

        for(int i=5; i<10; i++){
            awayTeam.add(allPlayers.get(i));
        }



        TabLayout tabLayout = findViewById(R.id.adminTabLayout);
        FrameLayout simpleFrameLayout = findViewById(R.id.simpleFrameLayout);

        Fragment fragment = new AdminMatchDetailsFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.simpleFrameLayout, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            // get the current selected tab's position and replace the fragment accordingly
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new AdminMatchDetailsFragment();
                        break;
                    case 1:
                        fragment = new AdminLiveCommentaryFragment();
                        break;
                    case 2:
                        fragment = new AdminLineUpFragment(homeTeam, awayTeam, getIntent());
                        break;
                }

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.simpleFrameLayout, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}

