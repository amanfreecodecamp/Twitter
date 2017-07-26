package com.example.aman.twitter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Aman on 26-07-2017.
 */

public class PageAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment> fragments=new ArrayList<>();
    public PageAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }
    @Override
    public int getCount() {
        return fragments.size();
    }
    //ADD PAGE
    public void addFragment(Fragment f)
    {
        fragments.add(f);
    }
    //set title
    @Override
    public CharSequence getPageTitle(int position) {
        String title= fragments.get(position).toString();
        String newTitle = "";
        for(int i = 0; i < title.length();i++) {
            if(title.charAt(i) == '{') {
                break;
            } else {
                newTitle = newTitle + title.charAt(i);
            }

        }
        Log.i("Title",newTitle);
        return newTitle.toString();
    }
}
