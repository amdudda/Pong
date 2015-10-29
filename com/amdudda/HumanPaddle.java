package com.amdudda;

/**
 * Created by tk0654wm on 10/29/2015.
 */
public class HumanPaddle extends Paddle {

    static int PaddleY = Main.screenSize / 2 ;
    static int PaddleMaxSpeed = 5;   //This doesn't quite do the same thing... this is how many pixels human moves per key press TODO use this in a better way
    static int PaddleSpeed = 0;      // "speed" is pixels moved up or down per clock tick

}
