package com.example.basketballleague;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.basketballleague.ui.matches.AdminLineUpFragment;
import com.example.basketballleague.ui.matches.AdminLiveCommentaryFragment;
import com.example.basketballleague.ui.matches.AdminMatchDetailsFragment;
import com.example.basketballleague.ui.matches.PlayerInCourt;
import com.example.basketballleague.ui.matches.TeamMembers;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class MatchAdministrationActivity extends AppCompatActivity {

    private ArrayList<String> homeTeam = new ArrayList<>();
    private ArrayList<String> awayTeam = new ArrayList<>();
    private ArrayList<PlayerInCourt> home = new ArrayList<>();
    private ArrayList<PlayerInCourt> away = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_admin);

        okHttpHandlerAdmin okHttpHandlerAdmin = new okHttpHandlerAdmin();
        Intent intent = getIntent();
        String score = intent.getStringExtra("homeScore") + " - " + intent.getStringExtra("awayScore");
        ImageView homePhoto = findViewById(R.id.homePhoto);
        ImageView awayPhoto = findViewById(R.id.awayPhoto);
        TextView scoreView = findViewById(R.id.score);
        scoreView.setText(score);
        Button startButton = findViewById(R.id.start_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    okHttpHandlerAdmin.changeMatchStatus(intent.getStringExtra("status"), intent.getStringExtra("matchID"));

                    String previousStatus = intent.getStringExtra("status");
                    String newStatus = "";

                    if(previousStatus.equals("upcoming")){
                        newStatus = "live";
                    }
                    else if(previousStatus.equals("live")){
                        newStatus = "ended";
                    }

                    intent.removeExtra("status");
                    intent.putExtra("status", newStatus);

                    finish();
                    startActivity(intent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        if((intent.getStringExtra("status")).equals("ended")){
            startButton.setVisibility(View.GONE);
        }
        else if((intent.getStringExtra("status")).equals("live")){
            startButton.setText("END MATCH");
        }

        try {
            URL imgURL = new URL(intent.getStringExtra("homeImageURI"));
            Bitmap icon_val = BitmapFactory.decodeStream(imgURL.openConnection().getInputStream());
            homePhoto.setImageBitmap(Bitmap.createScaledBitmap(icon_val,40,40,false));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            URL imgURL = new URL(intent.getStringExtra("awayImageURI"));
            Bitmap icon_val = BitmapFactory.decodeStream(imgURL.openConnection().getInputStream());
            awayPhoto.setImageBitmap(Bitmap.createScaledBitmap(icon_val,40,40,false));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String matchID = intent.getStringExtra("matchID");
        ArrayList<String> allPlayers = new ArrayList<>();

        //Getting each team's playing players and separating their names in 2 different arrays
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

        //Getting All Home Players with their details
        try {
            home = okHttpHandlerAdmin.getAllTeamPlayers(getIntent().getStringExtra("homeID"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Getting All Away Players with their details
        try {
            away = okHttpHandlerAdmin.getAllTeamPlayers(getIntent().getStringExtra("awayID"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        TabLayout tabLayout = findViewById(R.id.adminTabLayout);
        FrameLayout simpleFrameLayout = findViewById(R.id.simpleFrameLayout);

        Fragment fragment = new AdminMatchDetailsFragment(getIntent());
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
                        fragment = new AdminMatchDetailsFragment(getIntent());
                        break;
                    case 1:
                        fragment = new AdminLiveCommentaryFragment(getIntent());
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

