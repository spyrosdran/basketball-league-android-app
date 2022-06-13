package com.example.basketballleague.ui.matches;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basketballleague.R;
import com.example.basketballleague.okHttpHandlerAdmin;
import com.google.android.material.button.MaterialButton;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class AdminMatchDetailsFragment extends Fragment {

    private AdminMatchDetailsViewModel mViewModel;

    private EventList eventList;
    private MistakeList mistakeList;
    private TeamMembers team;
    private Button buttonH,buttonA;
    private Button bp1, bp2, bp3, bp4, bp5;
    private Button buttonFT, button2pt, button3pt, buttonIn, buttonOut, submitButton, undoButton;
    private String selectedTeam, selectedEvent, typeOfShoot;
    private List<PlayerInCourt> playerList;
    private PlayerInCourt selectedPlayer;
    private Spinner eventDropDown,mistakeDropDown;
    private Intent matchIntent;

    public static AdminMatchDetailsFragment newInstance(Intent intent) {
        return new AdminMatchDetailsFragment(intent);
    }

    public AdminMatchDetailsFragment(Intent intent) {
        matchIntent = intent;
    }

    public void onClickHome(View view) {        //resetting team players according to HOME team
        selectedTeam = "HOME";
         team = new TeamMembers(matchIntent.getStringExtra("matchID"),selectedTeam);

        //connecting players to player buttons
        playerList = team.getAllPlayers();
        if (playerList.size() == 5){
            bp1.setText(playerList.get(0).getPlayerName());
            bp2.setText(playerList.get(1).getPlayerName());
            bp3.setText(playerList.get(2).getPlayerName());
            bp4.setText(playerList.get(3).getPlayerName());
            bp5.setText(playerList.get(4).getPlayerName());
        }else {
            Toast.makeText(getActivity(), "ERROR!!! Not appropriate players in the court!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickAway(View view) {        //resetting team players according to HOME team
        selectedTeam = "AWAY";
        team = new TeamMembers(matchIntent.getStringExtra("matchID"),selectedTeam);

        //connecting players to player buttons
        playerList = team.getAllPlayers();
        if (playerList.size() == 5){
            bp1.setText(playerList.get(0).getPlayerName());
            bp2.setText(playerList.get(1).getPlayerName());
            bp3.setText(playerList.get(2).getPlayerName());
            bp4.setText(playerList.get(3).getPlayerName());
            bp5.setText(playerList.get(4).getPlayerName());
        }else {
            Toast.makeText(getActivity(), "ERROR!!! Not appropriate players in the court!", Toast.LENGTH_SHORT).show();
        }
    }


    //selecting player according to the button pressed
    public void onClickBP1(View view) {selectedPlayer = playerList.get(0);}
    public void onClickBP2(View view) {selectedPlayer = playerList.get(1);}
    public void onClickBP3(View view) {selectedPlayer = playerList.get(2);}
    public void onClickBP4(View view) {selectedPlayer = playerList.get(3);}
    public void onClickBP5(View view) {selectedPlayer = playerList.get(4);}

    public void onClickUndo(View view) {    //reinitializing submit form if UNDO is pressed
        selectedPlayer = new PlayerInCourt();
        selectedEvent = " ";
        buttonFT.setVisibility(View.INVISIBLE);
        button2pt.setVisibility(View.INVISIBLE);
        button3pt.setVisibility(View.INVISIBLE);
        buttonIn.setVisibility(View.INVISIBLE);
        buttonOut.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.VISIBLE);
    }

    public void onClickSubmit(View view) throws IOException {   //submitting event or revealing the submit buttons for SHOOT submission
        if (selectedPlayer.getPlayerName().equals("")){         //checking if a player is selected
            Toast.makeText(getActivity(), "Please select a player before submitting an event!", Toast.LENGTH_SHORT).show();
        }else {
            int minute = Integer.parseInt(((TextView) getActivity().findViewById(R.id.minuteNumberView)).getText().toString());

            if(minute > 0 && minute <= 40){                     //checking if minute has been inputted correctly
                eventDropDown = (Spinner) getActivity().findViewById(R.id.events);
                selectedEvent = String.valueOf(eventDropDown.getSelectedItem());    //get event
                if (selectedEvent.equals("SHOOT")) {            //reveal the right buttons if SHOOT is selected
                    buttonFT.setVisibility(View.VISIBLE);
                    button2pt.setVisibility(View.VISIBLE);
                    button3pt.setVisibility(View.VISIBLE);
                    submitButton.setVisibility(View.INVISIBLE);
                    Toast.makeText(getActivity(), "Choose the type of shoot and then press IN or OUT.", Toast.LENGTH_SHORT).show();
                }else if (selectedEvent.equals("MISTAKE")) {
                    String selectedMistake = String.valueOf(mistakeDropDown.getSelectedItem()); //get mistake type
                    if (selectedMistake.equals("-")){           //checking if a mistake type was selected
                        Toast.makeText(getActivity(), "Please select the type of mistake before submitting it!", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), selectedPlayer.getPlayerName() + ", " + selectedMistake + " (" + selectedEvent + ")", Toast.LENGTH_SHORT).show();

                        okHttpHandlerAdmin http = new okHttpHandlerAdmin();

                        //submitting event to database
                        String matchIDstr = matchIntent.getExtras().getString("matchID");
                        http.submitEvent(Integer.parseInt(matchIDstr), selectedPlayer.getPlayerID(), selectedMistake.toLowerCase(Locale.ROOT) + " (" + selectedEvent.toLowerCase(Locale.ROOT) + ")", minute);

                        //reinitializing the event submit form
                        selectedEvent = " ";
                        selectedPlayer = new PlayerInCourt();
                    }

                }else {     //submit any other event
                    Toast.makeText(getActivity(), selectedPlayer.getPlayerName() + ", " + selectedEvent, Toast.LENGTH_SHORT).show();

                    okHttpHandlerAdmin http = new okHttpHandlerAdmin();

                    //submitting event to database
                    String matchIDstr = matchIntent.getExtras().getString("matchID");
                    http.submitEvent(Integer.parseInt(matchIDstr), selectedPlayer.getPlayerID(), selectedEvent.toLowerCase(Locale.ROOT), minute);

                    //reinitializing the event submit form
                    selectedEvent = " ";
                    selectedPlayer = new PlayerInCourt();

                }
            }else {     //error message if not right minute input has been given
                Toast.makeText(getActivity(), "Please enter minute between 1 and 40", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //selecting type of SHOOT according to the button pressed and revealing outcome buttons IN/OUT
    public void onClickFT(View view) {
        buttonIn.setVisibility(View.VISIBLE);
        buttonOut.setVisibility(View.VISIBLE);
        typeOfShoot = "1pt";
    }
    public void onClick2pt(View view) {
        buttonIn.setVisibility(View.VISIBLE);
        buttonOut.setVisibility(View.VISIBLE);
        typeOfShoot = "2pt";
    }
    public void onClick3pt(View view) {
        buttonIn.setVisibility(View.VISIBLE);
        buttonOut.setVisibility(View.VISIBLE);
        typeOfShoot = "3pt";
    }

    //submitting successful SHOOT
    public void onClickIN(View view) throws IOException {
        int minute = Integer.parseInt(((TextView) getActivity().findViewById(R.id.minuteNumberView)).getText().toString());

        if(minute > 0 && minute <= 40){ //rechecking time input
            Toast.makeText(getActivity(), selectedPlayer.getPlayerName() + ", " + typeOfShoot + " shoot, IN", Toast.LENGTH_SHORT).show();

            okHttpHandlerAdmin http = new okHttpHandlerAdmin();

            //submitting successful SHOOT to database
            String matchIDstr = matchIntent.getExtras().getString("matchID");
            http.submitEvent(Integer.parseInt(matchIDstr), selectedPlayer.getPlayerID(), typeOfShoot, minute);
        }
        else{     //error message if not right minute input has been given
            Toast.makeText(getActivity(), "Please enter minute between 1 and 40", Toast.LENGTH_SHORT).show();
        }

        //reinitializing the event submit form
        selectedEvent = " ";
        selectedPlayer = new PlayerInCourt();
        buttonFT.setVisibility(View.INVISIBLE);
        button2pt.setVisibility(View.INVISIBLE);
        button3pt.setVisibility(View.INVISIBLE);
        buttonIn.setVisibility(View.INVISIBLE);
        buttonOut.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        undoButton.setVisibility(View.VISIBLE);
    }

    //submitting unsuccessful SHOOT
    public void onClickOUT(View view) throws IOException {
        int minute = Integer.parseInt(((TextView) getActivity().findViewById(R.id.minuteNumberView)).getText().toString());

        if(minute > 0 && minute <= 40){ //rechecking time input
            Toast.makeText(getActivity(), selectedPlayer.getPlayerName() + ", " + typeOfShoot + " shoot, OUT", Toast.LENGTH_SHORT).show();

            okHttpHandlerAdmin http = new okHttpHandlerAdmin();

            //submitting unsuccessful SHOOT to database
            String matchIDstr = matchIntent.getExtras().getString("matchID");
            http.submitEvent(Integer.parseInt(matchIDstr), selectedPlayer.getPlayerID(), typeOfShoot + " missed", minute);
        }
        else{     //error message if not right minute input has been given
            Toast.makeText(getActivity(), "Please enter minute between 1 and 40", Toast.LENGTH_SHORT).show();
        }

        //reinitializing the event submit form
        selectedEvent = " ";
        selectedPlayer = new PlayerInCourt();
        buttonFT.setVisibility(View.INVISIBLE);
        button2pt.setVisibility(View.INVISIBLE);
        button3pt.setVisibility(View.INVISIBLE);
        buttonIn.setVisibility(View.INVISIBLE);
        buttonOut.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.VISIBLE);
        undoButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStart() {     //initializing form
        super.onStart();

        //get hardcoded event and mistake types
        eventList = new EventList();
        mistakeList = new MistakeList();

        //initialize using the HOME team
        selectedTeam = "HOME";
        team = new TeamMembers(matchIntent.getStringExtra("matchID"),selectedTeam);

        //initialize selected player and event
        selectedPlayer = new PlayerInCourt();
        selectedEvent = " ";

        //connect HOME and AWAY buttons
        //set visibility of HOME and AWAY buttons for the basic submit form
        buttonH =  (Button) getActivity().findViewById(R.id.homeTeamBtn);
        buttonH.setVisibility(View.VISIBLE);
        buttonA = (Button) getActivity().findViewById(R.id.awayTeamBtn);
        buttonA.setVisibility(View.VISIBLE);

        //connect player buttons
        //set visibility of player buttons for the basic submit form
        bp1 = (Button) getActivity().findViewById(R.id.player1);
        bp1.setVisibility(View.VISIBLE);
        bp2 = (Button) getActivity().findViewById(R.id.player2);
        bp2.setVisibility(View.VISIBLE);
        bp3 = (Button) getActivity().findViewById(R.id.player3);
        bp3.setVisibility(View.VISIBLE);
        bp4 = (Button) getActivity().findViewById(R.id.player4);
        bp4.setVisibility(View.VISIBLE);
        bp5 = (Button) getActivity().findViewById(R.id.player5);
        bp5.setVisibility(View.VISIBLE);

        //connect SHOOT buttons
        //set visibility of SHOOT buttons for the basic submit form
        buttonFT = (Button) getActivity().findViewById(R.id.buttonFT);
        buttonFT.setVisibility(View.INVISIBLE);
        button2pt = (Button) getActivity().findViewById(R.id.button2pt);
        button2pt.setVisibility(View.INVISIBLE);
        button3pt = (Button) getActivity().findViewById(R.id.button3pt);
        button3pt.setVisibility(View.INVISIBLE);

        //connect submission buttons
        //set visibility of submission buttons for the basic submit form
        buttonIn = (Button) getActivity().findViewById(R.id.buttonIN);
        buttonIn.setVisibility(View.INVISIBLE);
        buttonOut = (Button) getActivity().findViewById(R.id.buttonOUT);
        buttonOut.setVisibility(View.INVISIBLE);
        submitButton = (Button) getActivity().findViewById(R.id.submitButton);
        submitButton.setVisibility(View.VISIBLE);

        //connect UNDO button
        //set visibility of UNDO buttons for the basic submit form
        undoButton = (Button) getActivity().findViewById(R.id.undoButton);
        undoButton.setVisibility(View.VISIBLE);

        //connecting players to player buttons
        playerList = team.getAllPlayers();
        if (playerList.size() == 5){
            bp1.setText(playerList.get(0).getPlayerName());
            bp2.setText(playerList.get(1).getPlayerName());
            bp3.setText(playerList.get(2).getPlayerName());
            bp4.setText(playerList.get(3).getPlayerName());
            bp5.setText(playerList.get(4).getPlayerName());
        }else {
            Toast.makeText(getActivity(), "ERROR!!! Not appropriate players in the court!", Toast.LENGTH_SHORT).show();
        }

        //initializing Spinners according to event and mistake types
        eventDropDown = (Spinner) getActivity().findViewById(R.id.events);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                eventList.getAllEvents());
        eventDropDown.setAdapter(arrayAdapter);

        mistakeDropDown = (Spinner) getActivity().findViewById(R.id.mistakeType);
        ArrayAdapter<String> mistakeArrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                mistakeList.getAllMistakes());
        mistakeDropDown.setAdapter(mistakeArrayAdapter);


        //SETTING onClick LISTENERS AND CONNECTING EACH BUTTON TO ITS ON CLICK METHOD
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onClickSubmit(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickUndo(view);
            }
        });
        buttonIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onClickIN(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        buttonOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    onClickOUT(view);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        buttonFT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickFT(view);
            }
        });
        button2pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick2pt(view);
            }
        });
        button3pt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClick3pt(view);
            }
        });
        bp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBP1(view);
            }
        });
        bp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBP2(view);
            }
        });
        bp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBP3(view);
            }
        });
        bp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBP4(view);
            }
        });
        bp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickBP5(view);
            }
        });
        buttonH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickHome(view);
            }
        });
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickAway(view);
            }
        });

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_admin_match_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AdminMatchDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

}