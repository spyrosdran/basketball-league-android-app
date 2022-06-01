package com.example.basketballleague;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class okHttpHandlerAdmin {

    public okHttpHandlerAdmin() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public ArrayList<Match> populateHomePage(String url) throws IOException {

        ArrayList<Match> matches = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        //System.out.println("My Response: " + data);
        try {

            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            ArrayList<String> values = new ArrayList<>();

            while(keys.hasNext()) {
                String next = keys.next();
                values.add(json.get(next).toString());
            }

            for (int i=0; i<values.size()/6; i++){
                matches.add(new Match(values.get(i), values.get(i+1), Integer.parseInt(values.get(i+2)), Integer.parseInt(values.get(i+3)), values.get(i+4), values.get(i+5) ));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return matches;
    }

}
