package com.example.basketballleague.ui.matches;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.basketballleague.R;
import com.example.basketballleague.databinding.FragmentMatchDetailsBinding;
import com.google.android.material.tabs.TabLayout;


public class MatchDetailsFragment extends Fragment {

    private MatchDetailsViewModel mViewModel;
    TabLayout tabLayout;
    ViewPager viewPager;
    private FragmentMatchDetailsBinding binding;

    public static MatchDetailsFragment newInstance() {
        return new MatchDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_match_details, container, false);
        TextView teamPoints1 = view.findViewById(R.id.teamPoints1);
        teamPoints1.setText("T1");
        TextView teamPoints2 = view.findViewById(R.id.teamPoints2);
        teamPoints2.setText("T2");
        TextView teamName1 = view.findViewById(R.id.teamName1);
        teamName1.setText("name1");
        TextView teamName2 = view.findViewById(R.id.teamName2);
        teamName2.setText("name2");
        binding = FragmentMatchDetailsBinding.inflate(inflater, container, false);
        View root1 = binding.getRoot();


        return root1;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MatchDetailsFragment.this).navigate(R.id.action_navigation_match_details_to_navigation_matches);
            }

        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(MatchDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}