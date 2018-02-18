package com.example.kong.accountingapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.LinkedList;

/**
 * Created by Kong on 2018/2/18.
 */

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    LinkedList<MainFragment> fragments = new LinkedList<>();
    LinkedList<String> dates = new LinkedList<>();

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        initFragments();
    }

    private void initFragments() {
        dates.add("2017-06-01");
        dates.add("2017-06-02");
        dates.add("2017-06-03");
        for (String date : dates) {
            MainFragment fragment = new MainFragment(date);
            fragments.add(fragment);
        }
    }

    public int getLastIndex() {
        return fragments.size() - 1;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

}
