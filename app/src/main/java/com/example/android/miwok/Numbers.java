package com.example.android.miwok;


import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by danghoaison91 on 20/12/2016.
 */

public class Numbers {


    // Store english language words
    private String mDefault;
    // Store Miwok language words
    private String mMiWok;

    // Store the word's audio
    private int mSong;

    // Store Drawable Object of Image Resource
    private int mImageResource = NO_IMAGE_PROVIDED;

    // Constant to check Image exist

    private static final int NO_IMAGE_PROVIDED = -1;

    // Get the English Translation
    public String getmDefault() {
        return mDefault;
    }
    // Get the Vietnamese Translation
    public String getmMiWok() {
        return mMiWok;
    }
    // Get the Image Id for input of ImageVIew
    public int getmImageResource(){return mImageResource;}
    // Get the Audio Id for input of MediaPlayer
    public int getmSong() {
        return mSong;
    }

    // Constructor with two String input for Pharse Activity
    public Numbers(String mDefault,String mMiWok){

        this.mDefault = mDefault;
        this.mMiWok = mMiWok;
    }

    // Constructor with two String and 1 int input for Numbers, Family, Colors Activity
    public Numbers(String mDefault,String mMiWok, int imageDrawable){

        this(mDefault,mMiWok);
        this.mImageResource = imageDrawable;
    }

    //
    public Numbers(String mDefault,String mMiWok, int imageDrawable,int mSong){

        this(mDefault,mMiWok,imageDrawable);
        this.mSong = mSong;
    }

    // Check the exist of Image to display properly all cases

    public boolean hasImage(){

        return mImageResource != NO_IMAGE_PROVIDED;
    }

    @Override
    public String toString() {
        return "" + mMiWok + "\n" + mDefault ;
    }
}
