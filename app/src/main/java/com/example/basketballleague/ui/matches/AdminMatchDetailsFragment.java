package com.example.basketballleague.ui.matches;

import androidx.lifecycle.ViewModelProvider;

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
import android.widget.Toast;

import com.example.basketballleague.R;

import java.util.List;

public class AdminMatchDetailsFragment extends Fragment {

    private AdminMatchDetailsViewModel mViewModel;

    private EventList eventList;
    private TeamMembers team;
    private Button bp1, bp2, bp3, bp4, bp5;
    private Button buttonFT, button2pt, button3pt, buttonIn, buttonOut, submitButton, undoButton;
    private String selectedEvent, selectedPlayer, typeOfShoot;

    public static AdminMatchDetailsFragment newInstance() {
        return new AdminMatchDetailsFragment();
    }

    public void onClickBP1(View view) {
        selectedPlayer = bp1.getText().toString();
    }
    public void onClickBP2(View view) {
        selectedPlayer = bp2.getText().toString();
    }
    public void onClickBP3(View view) {
        selectedPlayer = bp3.getText().toString();
    }
    public void onClickBP4(View view) {
        selectedPlayer = bp4.getText().toString();
    }
    public void onClickBP5(View view) {
        selectedPlayer = bp5.getText().toString();
    }

    public void onClickUndo(View view) {
        selectedPlayer = " ";
        selectedEvent = " ";
        buttonFT.setVisibility(View.INVISIBLE);
        button2pt.setVisibility(View.INVISIBLE);
        button3pt.setVisibility(View.INVISIBLE);
        buttonIn.setVisibility(View.INVISIBLE);
        buttonOut.setVisibility(View.INVISIBLE);
        submitButton.setVisibility(View.VISIBLE);
    }

    public void onClickSubmit(View view) {
        Spinner dropDown  = (Spinner) getActivity().findViewById(R.id.events);
        selectedEvent = String.valueOf(dropDown.getSelectedItem());
        if (selectedEvent == "SHOOT"){
            buttonFT.setVisibility(View.VISIBLE);
            button2pt.setVisibility(View.VISIBLE);
            button3pt.setVisibility(View.VISIBLE);
            submitButton.setVisibility(View.INVISIBLE);
            Toast.makeText(getActivity(), "Choose the type of shoot and then press IN or OUT.", Toast.LENGTH_SHORT).show();
        }else {
            if (selectedPlayer == " "){
                Toast.makeText(getActivity(), "Please select a player before submitting an event!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(getActivity(), selectedPlayer + ", " + selectedEvent, Toast.LENGTH_SHORT).show();
                selectedEvent = " ";
                selectedPlayer = " ";
            }
        }
    }

    public void onClickFT(View view) {
        buttonIn.setVisibility(View.VISIBLE);
        buttonOut.setVisibility(View.VISIBLE);
        typeOfShoot = "FT";
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

    public void onClickIN(View view) {
        if (selectedPlayer == " "){
            Toast.makeText(getActivity(), "Please select a player before submitting the shoot!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), selectedPlayer + ", " + typeOfShoot + " shoot, IN", Toast.LENGTH_SHORT).show();
            selectedEvent = " ";
            selectedPlayer = " ";
            buttonFT.setVisibility(View.INVISIBLE);
            button2pt.setVisibility(View.INVISIBLE);
            button3pt.setVisibility(View.INVISIBLE);
            buttonIn.setVisibility(View.INVISIBLE);
            buttonOut.setVisibility(View.INVISIBLE);
            submitButton.setVisibility(View.VISIBLE);
            undoButton.setVisibility(View.VISIBLE);
        }
    }

    public void onClickOUT(View view) {
        if (selectedPlayer == " "){
            Toast.makeText(getActivity(), "Please select a player before submitting the shoot!", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), selectedPlayer + ", " + typeOfShoot + " shoot, OUT", Toast.LENGTH_SHORT).show();
            selectedEvent = " ";
            selectedPlayer = " ";
            buttonFT.setVisibility(View.INVISIBLE);
            button2pt.setVisibility(View.INVISIBLE);
            button3pt.setVisibility(View.INVISIBLE);
            buttonIn.setVisibility(View.INVISIBLE);
            buttonOut.setVisibility(View.INVISIBLE);
            submitButton.setVisibility(View.VISIBLE);
            undoButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        eventList = new EventList();
        team = new TeamMembers();
        super.onCreate(savedInstanceState);

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

        selectedPlayer = " ";
        selectedEvent = " ";
        buttonFT = (Button) getActivity().findViewById(R.id.buttonFT);
        buttonFT.setVisibility(View.INVISIBLE);
        button2pt = (Button) getActivity().findViewById(R.id.button2pt);
        button2pt.setVisibility(View.INVISIBLE);
        button3pt = (Button) getActivity().findViewById(R.id.button3pt);
        button3pt.setVisibility(View.INVISIBLE);
        buttonIn = (Button) getActivity().findViewById(R.id.buttonIN);
        buttonIn.setVisibility(View.INVISIBLE);
        buttonOut = (Button) getActivity().findViewById(R.id.buttonOUT);
        buttonOut.setVisibility(View.INVISIBLE);
        submitButton = (Button) getActivity().findViewById(R.id.submitButton);
        submitButton.setVisibility(View.VISIBLE);
        undoButton = (Button) getActivity().findViewById(R.id.undoButton);
        undoButton.setVisibility(View.VISIBLE);

        List<String> playerList = team.getAllPlayers();
        if (playerList.size() == 5){
            bp1.setText(playerList.get(0));
            bp2.setText(playerList.get(1));
            bp3.setText(playerList.get(2));
            bp4.setText(playerList.get(3));
            bp5.setText(playerList.get(4));
        }else {
            Toast.makeText(getActivity(), "ERROR!!! Not appropriate players in the court!", Toast.LENGTH_SHORT).show();
        }

        Spinner dropDown = (Spinner) getActivity().findViewById(R.id.events);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                eventList.getAllEvents());
        dropDown.setAdapter(arrayAdapter);

        return inflater.inflate(R.layout.fragment_admin_match_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AdminMatchDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

}