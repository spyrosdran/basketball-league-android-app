package com.example.basketballleague.ui.matches;

import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.basketballleague.R;
import com.google.android.material.tabs.TabLayout;

public class AdminFragment extends Fragment {

    private AdminViewModel mViewModel;
    private TabLayout adminTabLayout;
    private ViewPager adminViewPager;

    public static AdminFragment newInstance() {
        return new AdminFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        /*adminTabLayout = getView().findViewById(R.id.adminTabLayout);
        adminViewPager = getView().findViewById(R.id.adminViewPager);

        adminTabLayout.setupWithViewPager(adminViewPager);

        AdminViewPagerAdapter avpa = new AdminViewPagerAdapter(getParentFragmentManager());
        avpa.addFragment(new AdminMatchDetailsFragment(), "Match Details");
        avpa.addFragment(new AdminLiveCommentaryFragment(), "Live Commentary");
        avpa.addFragment(new AdminLineUpFragment(), "Line Up");

        adminViewPager.setAdapter(avpa);*/

        return inflater.inflate(R.layout.fragment_admin, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AdminViewModel.class);
        // TODO: Use the ViewModel
    }

}