package com.example.basketballleague;

import android.os.*;
import android.util.Log;

import org.json.*;
import java.util.*;
import okhttp3.*;

public class OkHttpHandler {

    public OkHttpHandler() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public PlayerContainer loadPlayer(String url) throws Exception{
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url(url).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();
        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();
            while(keys.hasNext()) {
                String name = keys.next();
                String id = json.getJSONObject(name).get("id").toString();
                String position = json.getJSONObject(name).get("position").toString();
                String teamID = json.getJSONObject(name).get("teamID").toString();
                String photoURI = json.getJSONObject(name).get("photoURI").toString();
                String games = json.getJSONObject(name).get("games").toString();
                String statsString = json.getJSONObject(name).get("types").toString();
                String[] statsTable = statsString.split(",");
                return new PlayerContainer(name, id, position, teamID, photoURI, games, statsTable);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}
