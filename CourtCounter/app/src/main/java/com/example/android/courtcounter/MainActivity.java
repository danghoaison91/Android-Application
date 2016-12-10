package com.example.android.courtcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int teamAScore = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scoreInput (View v){

        teamAScore = teamAScore + 3;
        displayScore(teamAScore);

    }

    public void displayScore(int number){

        TextView  scoreView = (TextView) findViewById(R.id.scoreA_text_view);
        scoreView.setText("" + number);

    }
}
