package com.example.basketballleague.ui.matches;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.basketballleague.R;
import com.example.basketballleague.okHttpHandlerAdmin;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

public class AdminLineUpFragment extends Fragment {

    private AdminLineUpFragmentViewModel mViewModel;
    private ArrayList<String> homeTeam;
    private ArrayList<String> awayTeam;
    private Intent intent;

    public AdminLineUpFragment(ArrayList<String> homeTeam, ArrayList<String> awayTeam, Intent intent) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.intent = intent;
    }

    public void onStart() {
        super.onStart();

        //Hiding the change confirmation views before the user does any action
        TextView playerToGoOut = (TextView) getActivity().findViewById(R.id.player_to_go_out);
        TextView arrowOut = (TextView) getActivity().findViewById(R.id.arrow_out);
        TextView playerToGoIn = (TextView) getActivity().findViewById(R.id.player_to_go_in);
        TextView arrowIn = (TextView) getActivity().findViewById(R.id.arrow_in);
        Button undoButton = (Button) getActivity().findViewById(R.id.undo_button);
        Button submitButton = (Button) getActivity().findViewById(R.id.submit_button);

        playerToGoOut.setVisibility(View.INVISIBLE);
        arrowOut.setVisibility(View.INVISIBLE);
        playerToGoIn.setVisibility(View.INVISIBLE);
        arrowIn.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.INVISIBLE);

        //Initializing the Radio Buttons with default values from the Home Team
        RadioButton home_team_radio_button = (RadioButton) getActivity().findViewById(R.id.home_team);
        RadioButton away_team_radio_button = (RadioButton) getActivity().findViewById(R.id.away_team);

        home_team_radio_button.setText(intent.getStringExtra("homeID"));
        away_team_radio_button.setText(intent.getStringExtra("awayID"));

        RadioButton playerOut1 = (RadioButton) getActivity().findViewById(R.id.player_out_1);
        RadioButton playerOut2 = (RadioButton) getActivity().findViewById(R.id.player_out_2);
        RadioButton playerOut3 = (RadioButton) getActivity().findViewById(R.id.player_out_3);
        RadioButton playerOut4 = (RadioButton) getActivity().findViewById(R.id.player_out_4);
        RadioButton playerOut5 = (RadioButton) getActivity().findViewById(R.id.player_out_5);

        playerOut1.setText(homeTeam.get(0));
        playerOut2.setText(homeTeam.get(1));
        playerOut3.setText(homeTeam.get(2));
        playerOut4.setText(homeTeam.get(3));
        playerOut5.setText(homeTeam.get(4));

        //Setting Up the onClickListener of the Team Selection Radio Buttons
        home_team_radio_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playerOut1.setText(homeTeam.get(0));
                playerOut2.setText(homeTeam.get(1));
                playerOut3.setText(homeTeam.get(2));
                playerOut4.setText(homeTeam.get(3));
                playerOut5.setText(homeTeam.get(4));

                playerOut1.setChecked(false);
                playerOut2.setChecked(false);
                playerOut3.setChecked(false);
                playerOut4.setChecked(false);
                playerOut5.setChecked(false);

                playerToGoOut.setVisibility(View.INVISIBLE);
                arrowOut.setVisibility(View.INVISIBLE);
                playerToGoIn.setVisibility(View.INVISIBLE);
                arrowIn.setVisibility(View.INVISIBLE);
            }
        });

        away_team_radio_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playerOut1.setText(awayTeam.get(0));
                playerOut2.setText(awayTeam.get(1));
                playerOut3.setText(awayTeam.get(2));
                playerOut4.setText(awayTeam.get(3));
                playerOut5.setText(awayTeam.get(4));

                playerOut1.setChecked(false);
                playerOut2.setChecked(false);
                playerOut3.setChecked(false);
                playerOut4.setChecked(false);
                playerOut5.setChecked(false);

                playerToGoOut.setVisibility(View.INVISIBLE);
                arrowOut.setVisibility(View.INVISIBLE);
                playerToGoIn.setVisibility(View.INVISIBLE);
                arrowIn.setVisibility(View.INVISIBLE);
            }
        });

        //Setting Up the onClickListener of the Players Radio Buttons
        RadioGroup changeOutRadioGroup = (RadioGroup) getActivity().findViewById(R.id.change_out_radio_group);
        changeOutRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) getActivity().findViewById(i);
                String player = radioButton.getText().toString();
                playerToGoOut.setText(player);
                playerToGoOut.setVisibility(View.VISIBLE);
                arrowOut.setVisibility(View.VISIBLE);

                //Getting the Available Players to change
                okHttpHandlerAdmin okHttpHandlerAdmin = new okHttpHandlerAdmin();
                try {
                    ArrayList<String> availablePlayers = okHttpHandlerAdmin.getChangeablePlayers(player);
                    Log.d("My App","Successful http request in Changeable Players");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public static AdminLineUpFragment newInstance(ArrayList<String> homeTeam, ArrayList<String> awayTeam, Intent intent) {
        return new AdminLineUpFragment(homeTeam, awayTeam, intent);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_line_up, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AdminLineUpFragmentViewModel.class);
        // TODO: Use the ViewModel
    }

}