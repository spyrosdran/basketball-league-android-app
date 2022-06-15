package com.example.basketballleague;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.basketballleague.ui.matches.Comment;


import java.util.ArrayList;



public class LiveCommentary extends Fragment {

    private ArrayList<Comment> comments;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_live_commentary, container, false);
        LinearLayout linearLayout = (LinearLayout)view.findViewById(R.id.table);

        try{

            okHttpHandlerAdmin okHttpHandler = new okHttpHandlerAdmin();
            comments = okHttpHandler.loadNewComments(2, 12);

            for(int i = 0;i<comments.size();i++){
                //textView for timestamp
                TextView timpstampView = new TextView(this.getContext());
                //timpstampView.setId(comments.get(i).getId());??/
                timpstampView.setWidth(72);
                timpstampView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                params.setMargins(14,0,0,0); // setMargins(left, top, right, bottom)
                timpstampView.setLayoutParams(params);
                timpstampView.setText(new Integer(String.valueOf(comments.get(i).getTimestamp()));
                timpstampView.setTextColor(Integer.parseInt("@color/white"));
                linearLayout.addView(timpstampView);

                //textView for content
                TextView contentView = new TextView(this.getContext());
                //contentView.setId()
                contentView.setWidth(37);
                contentView.setLayoutParams(params);
                contentView.setTextAlignment(View.TEXT_ALIGNMENT_VIEW_START);
                contentView.setText(comments.get(i).getContent());
                contentView.setBackground(Drawable.createFromPath("@drawable/ic_lv_template"));
                contentView.setTextColor(Integer.parseInt("@color/white"));
                linearLayout.addView(contentView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }
}