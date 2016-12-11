package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.button;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

public class MainActivity extends AppCompatActivity {

    Score teamAScore = new Score(0);
    Score teamBScore = new Score(0);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void scoreAInput (View v){

        Button button = (Button) findViewById(v.getId());
        teamAScore.getTypeOfShot((String) button.getText());
        displayAScore(teamAScore.getScore());

    }

    public void scoreBInput (View v){

        Button button = (Button) findViewById(v.getId());
        teamBScore.getTypeOfShot((String) button.getText());
        displayBScore(teamBScore.getScore());

    }

    public void reset(View v){

        scoreAInput(v);
        scoreBInput(v);
    }

    public void displayAScore(int number){

        TextView  scoreView;
        scoreView = (TextView) findViewById(R.id.scoreA_text_view);
        scoreView.setText("" + number);

    }

    public void displayBScore(int number){

        TextView  scoreView;
        scoreView = (TextView) findViewById(R.id.scoreB_text_view);
        scoreView.setText("" + number);

    }

}
