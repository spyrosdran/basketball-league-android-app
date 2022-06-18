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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basketballleague.R;
import com.example.basketballleague.okHttpHandlerAdmin;

import java.io.IOException;
import java.util.ArrayList;

public class AdminLineUpFragment extends Fragment {

    private AdminLineUpFragmentViewModel mViewModel;
    private ArrayList<String> homeTeam;
    private ArrayList<String> awayTeam;
    private ArrayList<String> availablePlayers = new ArrayList<>();
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
        Button submitButton = (Button) getActivity().findViewById(R.id.submit_button);

        playerToGoOut.setVisibility(View.GONE);
        arrowOut.setVisibility(View.GONE);
        playerToGoIn.setVisibility(View.GONE);
        arrowIn.setVisibility(View.GONE);
        submitButton.setVisibility(View.GONE);

        RadioButton playerIn1 = (RadioButton) getActivity().findViewById(R.id.player_in_1);
        RadioButton playerIn2 = (RadioButton) getActivity().findViewById(R.id.player_in_2);
        RadioButton playerIn3 = (RadioButton) getActivity().findViewById(R.id.player_in_3);
        RadioButton playerIn4 = (RadioButton) getActivity().findViewById(R.id.player_in_4);
        RadioButton playerIn5 = (RadioButton) getActivity().findViewById(R.id.player_in_5);

        playerIn1.setVisibility(View.INVISIBLE);
        playerIn2.setVisibility(View.INVISIBLE);
        playerIn3.setVisibility(View.INVISIBLE);
        playerIn4.setVisibility(View.INVISIBLE);
        playerIn5.setVisibility(View.INVISIBLE);

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

                playerToGoOut.setVisibility(View.GONE);
                arrowOut.setVisibility(View.GONE);
                playerToGoIn.setVisibility(View.GONE);
                arrowIn.setVisibility(View.GONE);
                submitButton.setVisibility(View.GONE);

                playerIn1.setVisibility(View.INVISIBLE);
                playerIn2.setVisibility(View.INVISIBLE);
                playerIn3.setVisibility(View.INVISIBLE);
                playerIn4.setVisibility(View.INVISIBLE);
                playerIn5.setVisibility(View.INVISIBLE);
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

                playerToGoOut.setVisibility(View.GONE);
                arrowOut.setVisibility(View.GONE);
                playerToGoIn.setVisibility(View.GONE);
                arrowIn.setVisibility(View.GONE);
                submitButton.setVisibility(View.GONE);

                playerIn1.setVisibility(View.INVISIBLE);
                playerIn2.setVisibility(View.INVISIBLE);
                playerIn3.setVisibility(View.INVISIBLE);
                playerIn4.setVisibility(View.INVISIBLE);
                playerIn5.setVisibility(View.INVISIBLE);
            }
        });

        if((intent.getStringExtra("status")).equals("live")) {
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

                    playerIn1.setVisibility(View.INVISIBLE);
                    playerIn2.setVisibility(View.INVISIBLE);
                    playerIn3.setVisibility(View.INVISIBLE);
                    playerIn4.setVisibility(View.INVISIBLE);
                    playerIn5.setVisibility(View.INVISIBLE);

                    availablePlayers = new ArrayList<>();
                    //Getting the Available Players to change
                    okHttpHandlerAdmin okHttpHandlerAdmin = new okHttpHandlerAdmin();
                    try {
                        availablePlayers = okHttpHandlerAdmin.getChangeablePlayers(player);
                        Log.d("My App", "Successful http request in Changeable Players");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (availablePlayers.size() >= 0 && availablePlayers.size() <= 5) {
                        switch (availablePlayers.size()) {
                            case 1: {
                                playerIn1.setText(availablePlayers.get(0));
                                playerIn1.setVisibility(View.VISIBLE);
                                break;
                            }
                            case 2: {
                                playerIn1.setText(availablePlayers.get(0));
                                playerIn1.setVisibility(View.VISIBLE);
                                playerIn2.setText(availablePlayers.get(1));
                                playerIn2.setVisibility(View.VISIBLE);
                                break;
                            }
                            case 3: {
                                playerIn1.setText(availablePlayers.get(0));
                                playerIn1.setVisibility(View.VISIBLE);
                                playerIn2.setText(availablePlayers.get(1));
                                playerIn2.setVisibility(View.VISIBLE);
                                playerIn3.setText(availablePlayers.get(2));
                                playerIn3.setVisibility(View.VISIBLE);
                                break;
                            }
                            case 4: {
                                playerIn1.setText(availablePlayers.get(0));
                                playerIn1.setVisibility(View.VISIBLE);
                                playerIn2.setText(availablePlayers.get(1));
                                playerIn2.setVisibility(View.VISIBLE);
                                playerIn3.setText(availablePlayers.get(2));
                                playerIn3.setVisibility(View.VISIBLE);
                                playerIn4.setText(availablePlayers.get(3));
                                playerIn4.setVisibility(View.VISIBLE);
                                break;
                            }
                            case 5: {
                                playerIn1.setText(availablePlayers.get(0));
                                playerIn1.setVisibility(View.VISIBLE);
                                playerIn2.setText(availablePlayers.get(1));
                                playerIn2.setVisibility(View.VISIBLE);
                                playerIn3.setText(availablePlayers.get(2));
                                playerIn3.setVisibility(View.VISIBLE);
                                playerIn4.setText(availablePlayers.get(3));
                                playerIn4.setVisibility(View.VISIBLE);
                                playerIn5.setText(availablePlayers.get(4));
                                playerIn5.setVisibility(View.VISIBLE);
                                break;
                            }
                            default:
                                break;
                        }
                    }


                }
            });

            RadioGroup changeInRadioGroup = (RadioGroup) getActivity().findViewById(R.id.change_in_radio_group);
            changeInRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    RadioButton radioButton = (RadioButton) getActivity().findViewById(i);
                    String player = radioButton.getText().toString();
                    playerToGoIn.setText(player);
                    playerToGoIn.setVisibility(View.VISIBLE);
                    arrowIn.setVisibility(View.VISIBLE);
                    submitButton.setVisibility(View.VISIBLE);
                }
            });

            //Setting up the Submit Button
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (playerToGoIn.getVisibility() == View.VISIBLE && playerToGoOut.getVisibility() == View.VISIBLE) {

                        RadioGroup changeOutRadioGroup = (RadioGroup) getActivity().findViewById(R.id.change_out_radio_group);
                        int checkedOut = changeOutRadioGroup.getCheckedRadioButtonId();
                        RadioButton out = getActivity().findViewById(checkedOut);

                        RadioGroup changeInRadioGroup = (RadioGroup) getActivity().findViewById(R.id.change_in_radio_group);
                        int checkedIn = changeInRadioGroup.getCheckedRadioButtonId();
                        RadioButton in = getActivity().findViewById(checkedIn);

                        String playerGetsOut = out.getText().toString();
                        String playerGetsIn = in.getText().toString();
                        String matchID = intent.getStringExtra("matchID");

                        okHttpHandlerAdmin okHttpHandlerAdmin = new okHttpHandlerAdmin();
                        try {
                            okHttpHandlerAdmin.changePlayer(playerGetsOut, playerGetsIn, matchID, 28);

                            int changeInRadioID = changeInRadioGroup.getCheckedRadioButtonId();
                            RadioButton changeInRadio = (RadioButton) getActivity().findViewById(changeInRadioID);

                            int changeOutRadioID = changeOutRadioGroup.getCheckedRadioButtonId();
                            RadioButton changeOutRadio = (RadioButton) getActivity().findViewById(changeOutRadioID);
                            changeOutRadio.setText(changeInRadio.getText().toString());

                            Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Succesfully Changed", Toast.LENGTH_SHORT);
                            toast.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast toast = Toast.makeText(getActivity().getApplicationContext(), "There was an error while performing action", Toast.LENGTH_SHORT);
                            toast.show();
                        } finally {

                            int checkedRadioID = changeInRadioGroup.getCheckedRadioButtonId();
                            RadioButton checkedRadio = (RadioButton) getActivity().findViewById(checkedRadioID);
                            checkedRadio.setChecked(false);

                            int changeOutRadioID = changeOutRadioGroup.getCheckedRadioButtonId();
                            RadioButton changeOutRadio = (RadioButton) getActivity().findViewById(changeOutRadioID);
                            changeOutRadio.setChecked(false);

                            playerToGoOut.setVisibility(View.GONE);
                            arrowOut.setVisibility(View.GONE);
                            playerToGoIn.setVisibility(View.GONE);
                            arrowIn.setVisibility(View.GONE);
                            submitButton.setVisibility(View.GONE);

                            playerIn1.setVisibility(View.INVISIBLE);
                            playerIn2.setVisibility(View.INVISIBLE);
                            playerIn3.setVisibility(View.INVISIBLE);
                            playerIn4.setVisibility(View.INVISIBLE);
                            playerIn5.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            });
        }
        else {
            playerIn1.setText(awayTeam.get(0));
            playerIn2.setText(awayTeam.get(1));
            playerIn3.setText(awayTeam.get(2));
            playerIn4.setText(awayTeam.get(3));
            playerIn5.setText(awayTeam.get(4));

            playerIn1.setVisibility(View.VISIBLE);
            playerIn2.setVisibility(View.VISIBLE);
            playerIn3.setVisibility(View.VISIBLE);
            playerIn4.setVisibility(View.VISIBLE);
            playerIn5.setVisibility(View.VISIBLE);

            playerIn1.setClickable(false);
            playerIn2.setClickable(false);
            playerIn3.setClickable(false);
            playerIn4.setClickable(false);
            playerIn5.setClickable(false);

            playerOut1.setClickable(false);
            playerOut2.setClickable(false);
            playerOut3.setClickable(false);
            playerOut4.setClickable(false);
            playerOut5.setClickable(false);

            home_team_radio_button.setClickable(false);
            away_team_radio_button.setClickable(false);

            home_team_radio_button.setChecked(false);

        }

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