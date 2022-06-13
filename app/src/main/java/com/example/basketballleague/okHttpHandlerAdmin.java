package com.example.basketballleague;

import android.os.StrictMode;

import com.example.basketballleague.ui.matches.PlayerInCourt;

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

    String IP = "192.168.1.129";

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
                String homeImageURI = matchJSON.getString("homeImageURI");
                String awayImageURI = matchJSON.getString("awayImageURI");
                String status = matchJSON.getString("status");
                String startTime = matchJSON.getString("startTime");
                String league = matchJSON.getString("league");

                Match match = new Match(matchID, homeTeam, awayTeam, Integer.parseInt(homeScore), Integer.parseInt(awayScore), homeImageURI, awayImageURI, startTime, league, status);
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
        Request request = new Request.Builder().url("http://" + IP + "/basketleague/changePlayers.php?changeOut=" + changeOut + "&changeIn=" + changeIn +"&matchID=" + matchID).method("POST", body).build();
        Response response = client.newCall(request).execute();
    }

    public void changeMatchStatus(String status, String matchID) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url("http://" + IP + "/basketleague/startEndMatch.php?status=" + status + "&matchID=" + matchID).method("POST", body).build();
        Response response = client.newCall(request).execute();
    }

    public ArrayList<PlayerInCourt> getInCourtTeamPlayers(String matchID, String homeaway) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url("http://" + IP + "/basketleague/getInCourtTeamPlayers.php?matchID=" + matchID + "&homeaway=" + homeaway).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        ArrayList<PlayerInCourt> inCourtTeamPlayers = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();

            while (keys.hasNext()) {
                String playerName = keys.next();
                int playerID = Integer.parseInt(json.getString(playerName));
                inCourtTeamPlayers.add(new PlayerInCourt(playerID,playerName));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return inCourtTeamPlayers;
    }

    public void submitEvent(int matchID, int playerID, String type, int minute) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url("http://" + IP + "/basketleague/addMatchEvent.php?matchid=" + matchID + "&playerid=" + playerID + "&type=\"" + type + "\"" + "&minute=" + minute).method("POST", body).build();
        Response response = client.newCall(request).execute();
    }

    public ArrayList<PlayerInCourt> getAllTeamPlayers(String team) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = RequestBody.create("", MediaType.parse("text/plain"));
        Request request = new Request.Builder().url("http://" + IP + "/basketleague/getAllTeamPlayers.php?team=" + team).method("POST", body).build();
        Response response = client.newCall(request).execute();
        String data = response.body().string();

        ArrayList<PlayerInCourt> players = new ArrayList<>();

        try {
            JSONObject json = new JSONObject(data);
            Iterator<String> keys = json.keys();

            while (keys.hasNext()) {
                String playerID = keys.next();
                int playerIDInt = Integer.parseInt(playerID);

                JSONObject playerJSON = json.getJSONObject(playerID);

                String playerName = playerJSON.getString("name");
                String playerPosition = playerJSON.getString(("position"));
                String playerTeam = playerJSON.getString("teamID");

                players.add(new PlayerInCourt(playerIDInt, playerName, playerPosition, playerTeam));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return players;
    }

}
