package com.example.basketballleague;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.basketballleague.ui.matches.AdminLineUpFragment;
import com.example.basketballleague.ui.matches.AdminLiveCommentaryFragment;
import com.example.basketballleague.ui.matches.AdminMatchDetailsFragment;
import com.example.basketballleague.ui.matches.AdminViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

import com.example.basketballleague.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding binding;

    private TabLayout adminTabLayout;
    private ViewPager adminViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
/*
        //Setting up the Admin TabLayout
        adminTabLayout = findViewById(R.id.adminTabLayout);
        adminViewPager = findViewById(R.id.adminViewPager);

        adminTabLayout.setupWithViewPager(adminViewPager);

        AdminViewPagerAdapter avpa = new AdminViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        avpa.addFragment(new AdminMatchDetailsFragment(), "Match Details");
        avpa.addFragment(new AdminLiveCommentaryFragment(), "Live Commentary");
        avpa.addFragment(new AdminLineUpFragment(), "Line Up");

        adminViewPager.setAdapter(avpa);
*/
        BottomNavigationView navView = findViewById(R.id.nav_view);
       // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_standings, R.id.navigation_matches, R.id.navigation_top5)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        //remove Action Bar
        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);

        try{
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } catch(NullPointerException e){
            e.printStackTrace();
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.backgroundColor));
    }

}