package com.example.basketballleague;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.basketballleague.ui.matches.AdminLineUpFragment;
import com.example.basketballleague.ui.matches.AdminLiveCommentaryFragment;
import com.example.basketballleague.ui.matches.AdminMatchDetailsFragment;
import com.google.android.material.tabs.TabLayout;

public class MatchAdministrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_admin);

        Intent intent = getIntent();
        String score = intent.getStringExtra("homeScore") + " - " + intent.getStringExtra("awayScore");
        TextView scoreView = findViewById(R.id.score);
        scoreView.setText(score);

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
                        fragment = new AdminLineUpFragment();
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

