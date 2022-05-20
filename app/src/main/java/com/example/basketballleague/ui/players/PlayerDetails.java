package com.example.basketballleague.ui.players;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.basketballleague.OkHttpHandler;
import com.example.basketballleague.Player;
import com.example.basketballleague.R;
import com.squareup.picasso.Picasso;

public class PlayerDetails extends AppCompatActivity {

    private ImageButton backBtn;
    private ImageView playerImg;
    private TextView playerNameTextView;
    private TextView playerDescriptionTextView;

    private TextView ppgTextView;
    private TextView rpgTextView;
    private TextView apgTextView;
    private TextView spgTextView;

    private TextView freeThrowsTextView;
    private TextView points2TextView;
    private TextView points3TextView;
    private TextView reboundsTextView;
    private TextView stealsTextView;
    private TextView turnoversTextView;
    private TextView assistsTextView;
    private TextView blocksTextView;
    private TextView foulsTextView;
    private TextView cutsTextView;
    private TextView mistakesTextView;
    private TextView pivotFootMistakeTextView;
    private TextView selfPassMistakeTextView;
    private TextView highDribbleMistakeTextView;
    private TextView threeSecondViolationMistakeTextView;
    private TextView kickingTheBallMistakeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_player_detail);

        this.backBtn = (ImageButton) findViewById(R.id.backBtn);
        this.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Player creation
        Player myPlayer = new Player(getIntent().getExtras().getInt("playerID"));

        //Player Information
        this.playerImg = (ImageView) findViewById(R.id.playerImage);
        String tempPhotoURL;
        if(myPlayer.getPhotoURL().equals(",") || !myPlayer.getPhotoURL().contains("http"))
            tempPhotoURL = "https://www.fourseasonssir.com/images/NoPhotoImage/agent_no_photo_found.jpg?mw=800&mh=500";
        else
            tempPhotoURL = myPlayer.getPhotoURL();
        Picasso.with(getApplicationContext()).load(tempPhotoURL).resize(60,60).into(this.playerImg);
        this.playerImg.setVisibility(View.VISIBLE);

        this.playerNameTextView = (TextView) findViewById(R.id.playerNameTextView);
        this.playerNameTextView.setText(myPlayer.getName());

        this.playerDescriptionTextView = (TextView) findViewById(R.id.playerDescriptionTextView);
        this.playerDescriptionTextView.setText(myPlayer.getPosition() + " at " + myPlayer.getTeamID());

        //Average Values
        this.ppgTextView = (TextView) findViewById(R.id.ppgTextView);
        this.ppgTextView.setText(Double.toString(myPlayer.calculatePointPerGame()));

        this.rpgTextView = (TextView) findViewById(R.id.rpgTextView);
        this.rpgTextView.setText(Double.toString(myPlayer.calculateReboundsPerGame()));

        this.apgTextView = (TextView) findViewById(R.id.apgTextView);
        this.apgTextView.setText(Double.toString(myPlayer.calculateAssistsPerGame()));

        this.spgTextView = (TextView) findViewById(R.id.spgTextView);
        this.spgTextView.setText(Double.toString(myPlayer.calculateShootsPerGame()));

        //Statistics
        this.freeThrowsTextView = (TextView) findViewById(R.id.freeThrowsTextView);
        this.freeThrowsTextView.setText(Integer.toString(myPlayer.getFreeThrows()));

        this.points2TextView = (TextView) findViewById(R.id.points2TextView);
        this.points2TextView.setText(Integer.toString(myPlayer.getPoints2()));

        this.points3TextView = (TextView) findViewById(R.id.points3TextView);
        this.points3TextView.setText(Integer.toString(myPlayer.getPoints3()));

        this.reboundsTextView = (TextView) findViewById(R.id.reboundsTextView);
        this.reboundsTextView.setText(Integer.toString(myPlayer.getRebounds()));

        this.stealsTextView = (TextView) findViewById(R.id.stealsTextView);
        this.stealsTextView.setText(Integer.toString(myPlayer.getSteals()));

        this.turnoversTextView = (TextView) findViewById(R.id.turnoversTextView);
        this.turnoversTextView.setText(Integer.toString(myPlayer.getTurnovers()));

        this.assistsTextView = (TextView) findViewById(R.id.assistsTextView);
        this.assistsTextView.setText(Integer.toString(myPlayer.getAssists()));

        this.blocksTextView = (TextView) findViewById(R.id.blocksTextView);
        this.blocksTextView.setText(Integer.toString(myPlayer.getBlocks()));

        this.foulsTextView = (TextView) findViewById(R.id.foulsTextView);
        this.foulsTextView.setText(Integer.toString(myPlayer.getFouls()));

        this.cutsTextView = (TextView) findViewById(R.id.cutsTextView);
        this.cutsTextView.setText(Integer.toString(myPlayer.getCuts()));

        this.mistakesTextView = (TextView) findViewById(R.id.mistakesTextView);
        this.mistakesTextView.setText(Integer.toString(myPlayer.getMistakes()));

        this.pivotFootMistakeTextView = (TextView) findViewById(R.id.pivotFootMistakeTextView);
        this.pivotFootMistakeTextView.setText(Integer.toString(myPlayer.getPivotFootMistake()));

        this.selfPassMistakeTextView = (TextView) findViewById(R.id.selfPassMistakeTextView);
        this.selfPassMistakeTextView.setText(Integer.toString(myPlayer.getSelfPassMistake()));

        this.highDribbleMistakeTextView = (TextView) findViewById(R.id.highDribbleMistakeTextView);
        this.highDribbleMistakeTextView.setText(Integer.toString(myPlayer.getHighDribbleMistake()));

        this.threeSecondViolationMistakeTextView = (TextView) findViewById(R.id.threeSecondViolationMistakeTextView);
        this.threeSecondViolationMistakeTextView.setText(Integer.toString(myPlayer.getThreeSecondViolationMistake()));

        this.kickingTheBallMistakeTextView = (TextView) findViewById(R.id.kickingTheBallMistakeTextView);
        this.kickingTheBallMistakeTextView.setText(Integer.toString(myPlayer.getKickTheBallMistake()));

    }
}