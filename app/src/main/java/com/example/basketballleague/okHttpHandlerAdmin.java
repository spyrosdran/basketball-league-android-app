package com.example.basketballleague;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class okHttpHandlerAdmin {

    String IP = "192.168.1.2";

    public okHttpHandlerAdmin() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public ArrayList<Match> getMatchesData(String matchType) throws IOException {

        ArrayList<Match> matches = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url("http://" + IP + "/basketleague/" + matchType).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        //System.out.println("My Response: " + data);
        try {

            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();

            do {
                String matchID = keys.next();

                JSONObject matchJSON = json.getJSONObject(matchID);
                String homeTeam = matchJSON.getString("homeID");
                String awayTeam = matchJSON.getString("awayID");
                String homeScore = matchJSON.getString("homePts");
                String awayScore = matchJSON.getString("awayPts");
                String startTime = matchJSON.getString("startTime");
                String league = matchJSON.getString("leagueID");

                Match match = new Match(matchID, homeTeam, awayTeam, Integer.parseInt(homeScore), Integer.parseInt(awayScore), startTime, league);
                matches.add(match);
            } while (keys.hasNext());


        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        return matches;
    }

    public ArrayList<String> getPlayersData(String matchID) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url("http://" + IP + "/basketleague/getPlayersData.php?matchID=" + matchID).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        ArrayList<String> players = new ArrayList<>();

        try {

            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();

            while (keys.hasNext()) {
                String player = keys.next();
                players.add(player);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return players;
    }

    public ArrayList<String> getChangeablePlayers(String player) throws IOException {

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url("http://" + IP + "/basketleague/getChangeablePlayers.php?player=" + player).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        ArrayList<String> players = new ArrayList<>();

        try {

            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();

            while (keys.hasNext()) {
                player = keys.next();
                players.add(player);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return players;

    }

    public void changePlayer(String changeOut, String changeIn, String matchID) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url("http://" + IP + "/basketleague/changePlayers.php?changeOut=" + changeOut + "&changeIn=" + changeIn +"matchID=" + matchID).method("POST", body).build();
        Response response = client.newCall(request).execute();
    }
}
