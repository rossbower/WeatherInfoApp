package com.example.ross.weatherinfoapp.adapter;



import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.ross.weatherinfoapp.fragments.FragmentOne;
import com.example.ross.weatherinfoapp.fragments.FragmentTwo;

public class MainPagerAdapter extends FragmentPagerAdapter {
    private final String MAIN_INFO = "Main Info";
    private final String DETAILS = "Details";

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentOne();
                break;
            case 1:
                fragment = new FragmentTwo();
                break;
            default:
                fragment = new FragmentOne();
                break;
        }
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return MAIN_INFO;
            case 1:
                return DETAILS;
        }
        return MAIN_INFO;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
