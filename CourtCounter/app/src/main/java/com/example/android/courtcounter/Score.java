package com.example.android.courtcounter;

/**
 * Created by danghoaison91 on 10/12/2016.
 */

public class Score {
    private int mScore;
    private String mTeam;
    final private int THREE_POINT_SHOT = 3;
    final private int TWO_POINT_SHOT = 2;
    final private int FREE_POINT_SHOT = 1;


    public Score (int number){

        mScore = number;

    }

    public int getScore(){

        return mScore;
    }


    public void getTypeOfShot(String shot){

        if (shot.contains("3")){

            mScore = mScore + THREE_POINT_SHOT;
        }else if (shot.contains("2")){

            mScore = mScore + TWO_POINT_SHOT;
        }
        else if (shot.toLowerCase().contains("free")) {

            mScore = mScore + FREE_POINT_SHOT;
        }else {

            mScore = 0;
        }

    }



}
