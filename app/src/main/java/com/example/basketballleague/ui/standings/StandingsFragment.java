package com.example.basketballleague.ui.standings;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.basketballleague.R;
import com.example.basketballleague.databinding.FragmentStandingsBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class StandingsFragment extends Fragment {

    private FragmentStandingsBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StandingsViewModel standingsViewModel =
                new ViewModelProvider(this).get(StandingsViewModel.class);

        binding = FragmentStandingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//        final TextView textView = binding.textDashboard;
//        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadStandings();

    }

    public void loadStandings(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        JSONObject teams;
        try{
            String url = "http://192.168.2.3/basketleague/getStandings.php?leagueid=1";
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            RequestBody body = RequestBody.create("", MediaType.parse("application/json"));
            Request request = new Request.Builder().url(url).method("POST", body).build();
            Response response = client.newCall(request).execute();

            teams = new JSONObject(response.body().string());

        } catch(Exception e){
            e.printStackTrace();
            return;
        }

        LinearLayout layout = (LinearLayout) this.getActivity().findViewById(R.id.standings_table);

        Iterator<String> keys = teams.keys();

        do{
            String name, position, badgeURI, GP, Wins, Loses, WinPer, Pts;
            try {
                name = keys.next();
                JSONObject team = teams.getJSONObject(name);
                position = String.valueOf(team.getInt("Pos")).concat(".");
                badgeURI = team.getString("badgeURI");
                GP = String.valueOf(team.getInt("GP"));
                Wins = String.valueOf(team.getInt("Wins"));
                Loses = String.valueOf(team.getInt("Loses"));
                WinPer = String.valueOf(team.getInt("WinPer")).concat("%");
                Pts = String.valueOf(team.getInt("Pts"));

            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }


            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(0,0,0,15);
            params.width = LinearLayout.LayoutParams.MATCH_PARENT;

            LinearLayout layout_row = new LinearLayout(this.getActivity());
            layout_row.setBackground(getResources().getDrawable(R.drawable.standings_record_background));
            layout_row.setGravity(Gravity.CENTER_VERTICAL);
            layout_row.setPadding(12,0, 12, 0);
            layout_row.setLayoutParams(params);


            TextView positiontxt = new TextView(this.getActivity());
            positiontxt.setText(position);
            positiontxt.setTextColor(getResources().getColor(R.color.white));
            positiontxt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.001f));

            ImageView badgeimg = new ImageView(this.getActivity());
            badgeimg.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f));
            try {
                URL imgURL = new URL(badgeURI);
                Bitmap icon_val = BitmapFactory.decodeStream(imgURL.openConnection().getInputStream());
                badgeimg.setImageBitmap(Bitmap.createScaledBitmap(icon_val,40,40,false));
            } catch (Exception e) {
                e.printStackTrace();
            }

            TextView nametxt = new TextView(this.getActivity());
            nametxt.setText(name);
            nametxt.setTextColor(getResources().getColor(R.color.white));
            nametxt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 10f));
            nametxt.setMaxWidth(this.getActivity().findViewById(R.id.name_title_txt).getWidth()-badgeimg.getWidth());


            TextView GPtxt = new TextView(this.getActivity());
            GPtxt.setText(GP);
            GPtxt.setGravity(Gravity.CENTER);
            GPtxt.setTextColor(getResources().getColor(R.color.white));
            GPtxt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

            TextView Wintxt = new TextView(this.getActivity());
            Wintxt.setText(Wins);
            Wintxt.setGravity(Gravity.CENTER);
            Wintxt.setTextColor(getResources().getColor(R.color.white));
            Wintxt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

            TextView Losestxt = new TextView(this.getActivity());
            Losestxt.setText(Loses);
            Losestxt.setGravity(Gravity.CENTER);
            Losestxt.setTextColor(getResources().getColor(R.color.white));
            Losestxt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

            TextView WinPertxt = new TextView(this.getActivity());
            WinPertxt.setText(WinPer);
            WinPertxt.setGravity(Gravity.CENTER);
            WinPertxt.setTextColor(getResources().getColor(R.color.white));
            WinPertxt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
            TextView temp = new TextView(this.getActivity());
            temp.setText("100%");
            WinPertxt.setWidth(temp.getWidth()+50);

            TextView Ptstxt = new TextView(this.getActivity());
            Ptstxt.setText(Pts);
            Ptstxt.setGravity(Gravity.CENTER);
            Ptstxt.setTextColor(getResources().getColor(R.color.white));
            Ptstxt.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));

            layout_row.addView(positiontxt);
            layout_row.addView(badgeimg);
            layout_row.addView(nametxt);
            layout_row.addView(GPtxt);
            layout_row.addView(Wintxt);
            layout_row.addView(Losestxt);
            layout_row.addView(WinPertxt);
            layout_row.addView(Ptstxt);

            layout.addView(layout_row);
        } while(keys.hasNext());
    }

}