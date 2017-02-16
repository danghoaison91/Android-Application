package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danghoaison91 on 20/12/2016.
 */

public class NumbersAdapter extends ArrayAdapter {
    private int mColorId ;
    public NumbersAdapter(Activity context, ArrayList<Numbers> objects, int colorId){
        super(context,0,objects);
        mColorId = colorId;

    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.element, parent, false);
        }

        final Numbers numbers = (Numbers) getItem(position);

        ImageView visualView = (ImageView) listItemView.findViewById(R.id.image);


        TextView miWokView = (TextView) listItemView.findViewById(R.id.element1);
        miWokView.setText(numbers.getmMiWok());



        TextView defaultView = (TextView) listItemView.findViewById(R.id.element2);
        defaultView.setText(numbers.getmDefault());

        // Set the theme color for the list item
        View linearTextView =  listItemView.findViewById(R.id.text_container);
        // Find the color that the resource Id map to
        int color = ContextCompat.getColor(getContext(),mColorId);
        // Set the background color of the container text view
        linearTextView.setBackgroundColor(color);



        // Check the exist of Image corresponding to words
        // If it didnot exist, dont display the image view
        if (numbers.hasImage()){
            visualView.setImageResource(numbers.getmImageResource());
            visualView.setVisibility(View.VISIBLE);
        }else {
            visualView.setVisibility(View.GONE);
        }


        return listItemView;

    }
}

