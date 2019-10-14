package com.example.douwenxuan.view.category;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class FragTabAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> tabName = new ArrayList<>();

    public FragTabAdapter(FragmentManager fm, ArrayList<Fragment> fragments, ArrayList<String>
            tabName) {
        super(fm);
        this.fragments = fragments;
        this.tabName = tabName;
    }

    @Override
    public Fragment getItem(int i) {
        return fragments.get(i);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabName.get(position);
    }
}
