package com.example.conestogaeats.AdapterClasses;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.conestogaeats.Fragments.LoginFrament;
import com.example.conestogaeats.Fragments.SignupFrament;

import java.util.ArrayList;

public class MyTablayoutAdapter extends FragmentPagerAdapter {

    Context context;
    ArrayList<String> tabList;

    public MyTablayoutAdapter(FragmentManager fm, ArrayList<String> tabList, Context context) {
        super(fm);
        this.context = context;
        this.tabList = tabList;
    }

    @Override
    public Fragment getItem(int i) {

        if (tabList.get(i).equalsIgnoreCase("Log in")) {
            return new LoginFrament();
        } else if (tabList.get(i).equalsIgnoreCase("Sign up")) {
            return new SignupFrament();
        }

        return null;
    }

    @Override
    public int getCount() {
        return tabList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabList.get(position);
    }
}
