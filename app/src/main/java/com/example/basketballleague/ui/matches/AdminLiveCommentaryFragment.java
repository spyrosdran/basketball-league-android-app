package com.example.basketballleague.ui.matches;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.basketballleague.R;

public class AdminLiveCommentaryFragment extends Fragment {

    private AdminLiveCommentaryFragmentViewModel mViewModel;

    public static AdminLiveCommentaryFragment newInstance() {
        return new AdminLiveCommentaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_live_commentary, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AdminLiveCommentaryFragmentViewModel.class);
        // TODO: Use the ViewModel
    }

}