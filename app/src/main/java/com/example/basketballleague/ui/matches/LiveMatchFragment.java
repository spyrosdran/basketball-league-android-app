package com.example.basketballleague.ui.matches;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.basketballleague.R;
import com.example.basketballleague.databinding.FragmentMatchDetailsBinding;

/**
 * A placeholder fragment containing a simple view.
 */
public class LiveMatchFragment extends Fragment {
    private FragmentMatchDetailsBinding binding;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public LiveMatchFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static LiveMatchFragment newInstance(int sectionNumber) {
        LiveMatchFragment fragment = new LiveMatchFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_live_match, container, false);
        //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

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
}


