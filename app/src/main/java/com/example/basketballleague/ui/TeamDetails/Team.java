package com.example.basketballleague.ui.TeamDetails;

import android.media.Image;

import com.example.basketballleague.OkHttpHandler;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.io.InputStream;
import java.net.URL;

//team gotten attributes
public class Team {

    private String name;
    private String city;
    private String badgeURL;
    private int leagueID;

    public Team(String name , String city, String  badgeURL , int leagueID ){
        this.name=name;
        this.city=city;
        this.badgeURL=badgeURL;
        this.leagueID=leagueID;
    }

    public String getName(){
        return name;
    }
    public void setName(String n){name=n;}
    public String getLocation(){
        return city;
    }
    public String getUrl(){
        return badgeURL;
    }
    public int getlid(){
        return leagueID;
    }

}
