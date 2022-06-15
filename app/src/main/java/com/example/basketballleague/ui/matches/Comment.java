package com.example.basketballleague.ui.matches;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private String content;
    private LocalDateTime timestamp;
    private int matchID;

    public Comment(int id, int matchID, String content, LocalDateTime timestamp){
        this.id = id;
        this.matchID = matchID;
        this.timestamp = timestamp;
        this.content = content;

    }


    public int getId(){ return this.id; }
    public int getMatchId(){ return this.matchID; }
    public String getContent(){ return this.content; }
    public LocalDateTime getTimestamp(){ return this.timestamp; }

}
