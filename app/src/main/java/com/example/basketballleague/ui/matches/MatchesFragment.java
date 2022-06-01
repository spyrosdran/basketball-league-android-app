package com.example.basketballleague.ui.matches;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basketballleague.R;
import com.example.basketballleague.databinding.FragmentMatchesBinding;
import com.example.basketballleague.databinding.FragmentNotificationsBinding;
import com.example.basketballleague.ui.notifications.NotificationsViewModel;

public class MatchesFragment extends Fragment {

    private FragmentMatchesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MatchesViewModel matchesViewModel =
                new ViewModelProvider(this).get(MatchesViewModel.class);

        binding = FragmentMatchesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();



        return root;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);

        binding.details.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MatchesFragment.this).navigate(R.id.action_navigation_matches_to_liveMatchActivity5);
            }

        });

        binding.admin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MatchesFragment.this).navigate(R.id.action_navigation_matches_to_navigation_admin);
            }

        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}