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
// App làm bài tập tiếng anh cho trẻ em, tính điểm thưởng quà
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements FamilyFragment.OnDataPass{

    // Define variable to keep tracking the learning process
    ProgressBar mProgressBar ;
    public String yes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        // Testing progress bar function
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.setProgress(0);
        mProgressBar.setMax(100);





        NumbersFragmentAdapter mNumbersFragmentAdapeter = new NumbersFragmentAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(mNumbersFragmentAdapeter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

    }


    // Receive callback from fragment by overide the interface method
    // Modify progressbar

    @Override
    public void onDataPass(String data) {
        yes = data;
        Toast.makeText(this, "Hello" + yes, Toast.LENGTH_SHORT).show();
        Log.e("MainActivity","What is " + data);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        mProgressBar.incrementProgressBy(10);
    }
}
