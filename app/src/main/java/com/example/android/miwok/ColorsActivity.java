/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    // Declare global variable to store media file
    private MediaPlayer mWordAudio;

    // Declare an variable of OnCompletionListener object
    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    // Declare an audio manager object to request audio focus of the context
    private AudioManager mListenAudioFocus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);


        final ArrayList<Numbers> numbers = new ArrayList<Numbers>();

        // Set audio focus of the context to variable
        mListenAudioFocus = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // Create a variable to keep track the index position of the list
        int index = 0;

        // Add numbers in english to the list
        numbers.add(new Numbers("red","đỏ"));
        numbers.add(new Numbers("green","xanh"));
        numbers.add(new Numbers("yellow","vàng"));
        numbers.add(new Numbers("brown","nâu"));
        numbers.add(new Numbers("gray","xám"));
        numbers.add(new Numbers("black","đen"));
        numbers.add(new Numbers("white","trắng"));
        numbers.add(new Numbers("pink","hồng"));
        numbers.add(new Numbers("silver","bạc"));


        // Find the root view of the whole layout
        // LinearLayout rootview = (LinearLayout) findViewById(R.id.rootview);

       /* // Create for loop to create text view for each numbers
        while (index < numbers.size()) {
            TextView english_numbers = new TextView(this);
            english_numbers.setText(numbers.get(index));

            // Add view to the child of root view
            rootview.addView(english_numbers);

            // Update counter variable
            index++;
        }*/

        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.

        NumbersAdapter itemsAdapter = new NumbersAdapter(this,numbers,R.color.category_colors);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(itemsAdapter);

        // Set a function to play audio when an event listview clicking occured

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                // Release old audio to play a new one
                releaseMediaPlayer();
                // Request Audio Focus for playback
                int result  = mListenAudioFocus.requestAudioFocus(iWantToListenAudioFocusChange,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);
                // If we granted the audio focus, just play the soound
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    // Based on the position of item on list view, determine
                    // array item to call exact audio file
                    Numbers number = numbers.get(position);
                    // Input Id of correct audio file in the MediaPlayer Instance
                    mWordAudio= MediaPlayer.create(ColorsActivity.this,number.getmSong());
                    mWordAudio.start();
                    mWordAudio.setOnCompletionListener(mOnCompletionListener);
                }


            }
        });
    }


    /** Create an onStop method to ensure our activity behave correct
     * in its lifecyle
     */

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, the music should be stopped playing
        releaseMediaPlayer();
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mWordAudio != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mWordAudio.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mWordAudio = null;
            // No more listen to audio focus
            mListenAudioFocus.abandonAudioFocus(iWantToListenAudioFocusChange);
        }
    }

    private AudioManager.OnAudioFocusChangeListener iWantToListenAudioFocusChange = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            if (i == AudioManager.AUDIOFOCUS_GAIN){
                // This mean we regain our audio focus an can resume the playback
                mWordAudio.setVolume(1f,1f);
                mWordAudio.start();
            }else if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                // THis mean we lost our focus for a short period but still play
                // at a lower volume
                mWordAudio.setVolume(0.5f,0.5f);

            }else if (i == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
                // THis mean we lost our focus for a short period and
                // able to resume our song at the point we loss
                mWordAudio.pause();
            }else if (i == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer();
            }
        }
    };

}
