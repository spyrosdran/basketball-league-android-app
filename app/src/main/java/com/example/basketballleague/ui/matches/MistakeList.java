package com.example.basketballleague.ui.matches;

import java.util.ArrayList;
import java.util.List;

public class MistakeList {
    private ArrayList<String> mistakes;

    public MistakeList(){
        mistakes = new ArrayList<String>();
        mistakes.add("-");
        mistakes.add("PIVOT FOOT");
        mistakes.add("SELF PASS");
        mistakes.add("HIGH DRIBBLE");
        mistakes.add("THREE SECOND VIOLATION");
        mistakes.add("KICKING THE BALL");
    }

    public List<String> getAllMistakes() {
        List<String> temp = new ArrayList<String>();
        for (int i=0; i<mistakes.size(); i++) {
            temp.add(mistakes.get(i));
        }
        return temp;
    }
}
