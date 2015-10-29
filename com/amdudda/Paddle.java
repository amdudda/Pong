package com.amdudda;

/**
 * Created by tk0654wm on 10/29/2015.
 */
public abstract class Paddle {

    protected int score = 0;

    // no constructor

    // getter and setter for score

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    // and an incrementer for score
    public void addPoint() {
        ++this.score;
    }
}
