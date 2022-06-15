package com.example.basketballleague.ui.matches;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.basketballleague.R;
import com.example.basketballleague.okHttpHandlerAdmin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

public class AdminLiveCommentaryFragment extends Fragment {

    private Intent matchIntent;

    private AdminLiveCommentaryFragmentViewModel mViewModel;

    public static AdminLiveCommentaryFragment newInstance(Intent intent) {
        return new AdminLiveCommentaryFragment(intent);
    }

    public AdminLiveCommentaryFragment(Intent intent){
        matchIntent = intent;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_live_commentary, container, false);
    }

    @Override
    public void onStart(){
        super.onStart();
        loadComments();
        ScrollView scroll = (ScrollView) getActivity().findViewById(R.id.comments_scroll);
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.fullScroll(View.FOCUS_DOWN);
            }
        });

        String status = matchIntent.getExtras().getString("status");
        if(!status.equals("live")){
            RelativeLayout layout = (RelativeLayout) getActivity().findViewById(R.id.comments_layout_all);
            EditText text_field = (EditText) getActivity().findViewById(R.id.comment_text_field);
            Button send_button = (Button) getActivity().findViewById(R.id.send_button);
            layout.removeView(text_field);
            layout.removeView(send_button);
        }
    }

    public void loadComments(){
        LinearLayout comment_list = (LinearLayout) getActivity().findViewById(R.id.comment_list);
        comment_list.removeAllViews();

        okHttpHandlerAdmin http = new okHttpHandlerAdmin();
        int matchID = Integer.parseInt(matchIntent.getExtras().getString("matchID"));
        try{
            ArrayList<Comment> comments = http.loadComments(matchID);
            for(Comment comment : comments){
                LinearLayout single_comment = new LinearLayout(this.getContext());
                TextView timestamp_view = new TextView(this.getContext());
                TextView comment_view = new TextView(this.getContext());

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                single_comment.setOrientation(LinearLayout.VERTICAL);
                single_comment.setLayoutParams(params);

                String timestamp_text = parse(comment.getTimestamp());

                timestamp_view.setText(timestamp_text);
                timestamp_view.setTextColor(getResources().getColor(R.color.white));

                LinearLayout.LayoutParams comment_view_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                comment_view_params.setMargins(0,0,0,30);
                comment_view.setBackground(ContextCompat.getDrawable(this.getContext(), R.drawable.commentary_round_corners));
                comment_view.setText(comment.getContent());
                comment_view.setTextColor(getResources().getColor(R.color.white));
                comment_view.setPadding(30, 30, 30, 30);
                comment_view.setLayoutParams(comment_view_params);


                single_comment.addView(timestamp_view);
                single_comment.addView(comment_view);

                comment_list.addView(single_comment);
            }
        } catch(IOException e){
            e.printStackTrace();
        }

        Button send = (Button) getActivity().findViewById(R.id.send_button);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSendButton(view);
            }
        });
    }


    public void onClickSendButton(View view) {
        int matchID = Integer.parseInt(matchIntent.getExtras().getString("matchID"));
        okHttpHandlerAdmin http = new okHttpHandlerAdmin();
        EditText comment_edit = (EditText) getActivity().findViewById(R.id.comment_text_field);
        String content = comment_edit.getText().toString();
        if(content.equals("")){
            Toast.makeText(this.getContext(), "Please type a comment", Toast.LENGTH_SHORT);
        }
        else{
            try {
                http.addComment(matchID, content);
                loadComments();
                ScrollView scroll = (ScrollView) getActivity().findViewById(R.id.comments_scroll);
                scroll.post(new Runnable() {
                    @Override
                    public void run() {
                        scroll.fullScroll(View.FOCUS_DOWN);
                    }
                });
                comment_edit.setText("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AdminLiveCommentaryFragmentViewModel.class);
        // TODO: Use the ViewModel
    }


    private String parse(LocalDateTime timestamp){
        String output = "";
        String timestampStr = timestamp.toString();
        String[] datetime = timestampStr.split("T");
        String date = datetime[0];
        String time = datetime[1];

        String[] date_array = date.split("-");
        int year = Integer.parseInt(date_array[0]);
        int month = Integer.parseInt(date_array[1]);
        int day = Integer.parseInt(date_array[2]);

        String[] time_array = time.split(":");
        int hour = Integer.parseInt(time_array[0]);
        int minute = Integer.parseInt(time_array[1]);

        if(day >= 10){
            output = Integer.toString(day);
        }
        else{
            output = "0".concat(Integer.toString(day));
        }
        output = output.concat("/");

        if(month >= 10){
            output = output.concat(Integer.toString(month));
        }
        else{
            output = output.concat("0").concat(Integer.toString(month));
        }
        output = output.concat("/").concat(Integer.toString(year)).concat(" - ");

        if(hour >= 10){
            output = output.concat(Integer.toString(hour));
        }
        else{
            output = output.concat("0").concat(Integer.toString(hour));
        }
        output = output.concat(":");

        if(minute >= 10){
            output = output.concat(Integer.toString(minute));
        }
        else{
            output = output.concat("0").concat(Integer.toString(minute));
        }

        return output;
    }

}