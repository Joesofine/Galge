package com.example.galge;

public class userObj {
    public String name;
    public int score;


    //Constructor to user objektet.
    public userObj(String name, int score) {
        this.name = name;
        this.score = score;

    }


    public String getName() {
        return name;
    }
    public int getScore() {
        return score;
    }
    public void setName(String name) {
        this.name = name;
    }


}

