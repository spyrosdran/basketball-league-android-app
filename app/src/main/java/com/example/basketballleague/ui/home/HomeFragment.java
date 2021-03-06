package com.example.basketballleague.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basketballleague.Match;
import com.example.basketballleague.MatchViewAdapter;
import com.example.basketballleague.R;
import com.example.basketballleague.TournamentCard;
import com.example.basketballleague.databinding.FragmentHomeBinding;
import com.example.basketballleague.okHttpHandlerAdmin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.*;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public void onStart() {
        super.onStart();

        okHttpHandlerAdmin okHttpHandlerAdmin = new okHttpHandlerAdmin();

        //Fragment Home Setup - R1 (showing matches)

        //Getting Upcoming Matches Data
        RecyclerView liveMatches = getActivity().findViewById(R.id.live_matches);
        ArrayList<Match> liveMatchesArray = new ArrayList<>();

        try {
            liveMatchesArray = okHttpHandlerAdmin.getMatchesData("getLiveMatches.php");
            Log.d("My App","Successful http request");
        } catch (IOException e) {
            e.printStackTrace();

        }

        if(liveMatchesArray.isEmpty())
            Log.d("MyApp","Upcoming Matches Array is empty");

        //Adapting The Match Data
        MatchViewAdapter liveMatchViewAdapter = new MatchViewAdapter(this.getActivity(), liveMatchesArray, getActivity().getIntent());
        liveMatches.setAdapter(liveMatchViewAdapter);
        liveMatches.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        //Getting Upcoming Matches Data
        RecyclerView upcomingMatches = getActivity().findViewById(R.id.upcoming_matches);
        ArrayList<Match> upcomingMatchesArray = new ArrayList<>();

        try {
            upcomingMatchesArray = okHttpHandlerAdmin.getMatchesData("getUpcomingMatches.php");
            Log.d("My App","Successful http request");
        } catch (IOException e) {
            e.printStackTrace();

        }

        if(upcomingMatchesArray.isEmpty())
            Log.d("MyApp","Upcoming Matches Array is empty");

        //Adapting The Match Data
        MatchViewAdapter upcomingMatchViewAdapter = new MatchViewAdapter(this.getActivity(), upcomingMatchesArray, getActivity().getIntent());
        upcomingMatches.setAdapter(upcomingMatchViewAdapter);
        upcomingMatches.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        //Getting Past Matches Data
        RecyclerView pastMatches = this.getActivity().findViewById(R.id.past_matches);
        ArrayList<Match> matches = new ArrayList<>();

        try {
            matches = okHttpHandlerAdmin.getMatchesData("getPastMatches.php");
            Log.d("My App","Successful http request");
        } catch (IOException e) {
            e.printStackTrace();

        }

        if(matches.isEmpty())
            Log.d("MyApp","Matches is empty");

        //Adapting The Match Data
        MatchViewAdapter matchViewAdapter = new MatchViewAdapter(this.getActivity(), matches, getActivity().getIntent());
        pastMatches.setAdapter(matchViewAdapter);
        pastMatches.setLayoutManager(new LinearLayoutManager(this.getActivity()));

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}