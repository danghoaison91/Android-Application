package com.example.android.miwok;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by danghoaison91 on 01/01/2017.
 */

public class NumbersFragmentAdapter extends FragmentPagerAdapter {

    private String pageTitle[] = new String[] {"Numbers","Colors","Family", "Phrases"};

    // Rebuild the constructor for Our customized adapter class
    public NumbersFragmentAdapter (FragmentManager fm){
        super(fm);
    }


    // Override two method, getItem to return correct fragment when
    // it's callbacked
    // getCount return number of fragment, fixed 4 fragment
    @Override
    public Fragment getItem(int position) {
        Fragment currentFragment = null;
        switch (position) {
            case 0:
                currentFragment = new NumbersFragment();
            case 1:
                currentFragment = new ColorsFragment();
            case 2:
                currentFragment = new NumbersFragment();
            case 3:
                currentFragment = new ColorsFragment();
        }
        return currentFragment;
    }

        @Override
        public int getCount () {
            return 4;
        }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle[position];
    }
}
