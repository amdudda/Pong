package com.amdudda;

/**
 * Created by tk0654wm on 10/29/2015.
 */
public abstract class Paddle {

    protected int score = 0;
    static int paddleSize = 25;     //Actually half the paddle size - how much to draw on each side of center
    static int paddleDistanceFromSide = 10;  //How much space between each paddle and side of screen


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
        this.score++;
    }
}
