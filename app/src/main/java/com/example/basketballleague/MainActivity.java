package com.example.basketballleague;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.basketballleague.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding binding;

    private TabLayout adminTabLayout;
    private ViewPager adminViewPager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        okHttpHandlerAdmin okHttpHandlerAdmin = new okHttpHandlerAdmin();
        String myIP = "192.168.1.4";

        //Fragment Home Setup
        RecyclerView liveMatches = findViewById(R.id.live_matches);
        RecyclerView pastMatches = findViewById(R.id.past_matches);

        //Getting Matches Data
        ArrayList<Match> matches = new ArrayList<>();

        try {
            matches = okHttpHandlerAdmin.populateHomePage("http://" + myIP + "/basketleague/getMatches.php");
            Log.d("My App","Successful http request");
        } catch (IOException e) {
            e.printStackTrace();

        }
        if(matches.isEmpty())
            Log.d("MyApp","Matches is empty");
        //Adapting The Match Data
        MatchViewAdapter matchViewAdapter = new MatchViewAdapter(this, matches);
        pastMatches.setAdapter(matchViewAdapter);
        pastMatches.setLayoutManager(new LinearLayoutManager(this));

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

        try {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        getWindow().setStatusBarColor(getResources().getColor(R.color.backgroundColor));
    }


}