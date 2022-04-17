package com.example.basketballleague.ui.top5;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basketballleague.R;
import com.example.basketballleague.databinding.FragmentTop5Binding;
import com.example.basketballleague.ui.top5.Top5ViewModel;

public class Top5Fragment extends Fragment {

    private FragmentTop5Binding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Top5ViewModel Top5ViewModel =
                new ViewModelProvider(this).get(Top5ViewModel.class);

        binding = FragmentTop5Binding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textTop5;
        Top5ViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}