package com.amdudda;

import java.awt.*;

/**
 * Created by tk0654wm on 10/29/2015.
 */
public abstract class Paddle {

    // AMD:  variable used for scoring
    protected int score = 0;
    // variables from original version of code.
    static int paddleSize = 25;     //Actually half the paddle size - how much to draw on each side of center
    static int paddleDistanceFromSide = 10;  //How much space between each paddle and side of screen


    // no constructor

    // all Paddles need to know how to draw themselves
    // GameDisplay complains that this is non-static in a static method - not sure why?
    // workaround is to create static methods for now
    // protected abstract void draw(Graphics g);

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
