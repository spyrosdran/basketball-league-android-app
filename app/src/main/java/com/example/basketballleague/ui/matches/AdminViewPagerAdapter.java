package com.example.basketballleague.ui.matches;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdminViewPagerAdapter extends FragmentPagerAdapter {

    private final ArrayList<Fragment> adminFragmentsArrayList = new ArrayList<>();
    private final ArrayList<String> adminFragmentTitles = new ArrayList<>();

    public AdminViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return adminFragmentsArrayList.get(position);
    }

    @Override
    public int getCount() {
        return adminFragmentsArrayList.size();
    }

    public void addFragment(Fragment fragment, String title){
        adminFragmentsArrayList.add(fragment);
        adminFragmentTitles.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        return adminFragmentTitles.get(position);
    }
}
