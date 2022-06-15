package com.example.basketballleague.ui.matches;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.basketballleague.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MatchDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatchDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SECTION_NUMBER = "param1";

    public MatchDetailsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MatchDetailsFragment newInstance(int sectionNumber) {
        MatchDetailsFragment fragment = new MatchDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_match_details, container, false);
        LinearLayout linearLayout1 = view.findViewById(R.id.linearLayout1);
        LinearLayout linearLayout3 = view.findViewById(R.id.linearLayout3);
        final int N = 8; // total number of textviews to add
        final TextView[] myTextViews = new TextView[N];
        for (int i = 0; i < N; i++) {
            // create a new textview
            final TextView rowTextView = new TextView(getContext());

            // setting text
            rowTextView.setText(" #" + i);
            // setting color to dynamic textviews
            String whiteColorStr="#FFFFFF";
            int whiteColor =  Color.parseColor(whiteColorStr);
            rowTextView.setTextColor(whiteColor);
            // setting margin to dynamic textviews
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,11,0,11);
            rowTextView.setLayoutParams(params);
            // setting size to dynamic textviews
            rowTextView.setTextSize(20);
            // add the textview to the linearlayout
            linearLayout1.addView(rowTextView);

            // save a reference to the textview for later
            myTextViews[i] = rowTextView;
        }

        for (int i = 0; i < N; i++) {
            // create a new textview
            final TextView rowTextView = new TextView(getContext());

            // setting text
            rowTextView.setText(" #" + i);
            // setting color to dynamic textviews
            String whiteColorStr="#FFFFFF";
            int whiteColor =  Color.parseColor(whiteColorStr);
            rowTextView.setTextColor(whiteColor);
            // setting margin to dynamic textviews
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,11,0,11);
            rowTextView.setLayoutParams(params);
            // setting size to dynamic textviews
            rowTextView.setTextSize(20);
            // add the textview to the linearlayout
            linearLayout3.addView(rowTextView);

            // save a reference to the textview for later
            myTextViews[i] = rowTextView;
        }


        return view;
    }
}