package com.example.basketballleague.ui.matches;

import java.util.ArrayList;
import java.util.List;

public class EventList {
    private ArrayList<String> events;

    public EventList(){
        events = new ArrayList<String>();
        events.add("SHOOT");
        events.add("ASSIST");
        events.add("REBOUND");
        events.add("BLOCK");
        events.add("STEAL");
        events.add("MISTAKE");
        events.add("FOUL");
    }

    public List<String> getAllEvents() {
        List<String> temp = new ArrayList<String>();
        for (int i=0; i<events.size(); i++) {
            temp.add(events.get(i));
        }
        return temp;
    }
}
