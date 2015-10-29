package com.amdudda;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amdudda on 10/29/2015.
 */
public class Ball {

    static double scrnsz = Main.screenSize;
    static double  ballX = scrnsz / 2;   //Imagine the ball is in a square box. These are the coordinates of the top of that box.
    static double  ballY = scrnsz / 2;   //So this starts the ball in (roughly) the center of the screen
    static int ballSize = 10;                //Diameter of ball

    static double ballSpeed = 5;   //Again, pixels moved per clock tick


    //An angle in radians (which range from 0 to 2xPI (0 to about 6.3).
    //This starts the ball moving down toward the human. Replace with some of the other
    //commented out versions for a different start angle
    //set this to whatever you want (but helps if you angle towards a player)
    static double ballDirection = Math.PI + 1;   //heading left and up
    //static double ballDirection = 1;
    //static double ballDirection = 0;   //heading right
    //static double ballDirection = Math.PI;   //heading left


    // fetches location of a ball
    public void getLocation() {
        // TODO: return a set of values that can be used to draw a ball.

    }

    //Checks to see if the ball has hit a wall or paddle
    //If so, bounce off the wall/paddle
    //And then move ball in the correct direction
    protected static void moveBall() {
        // AMD: commented out: System.out.println("move ball");
        //move in current direction
        //bounce off walls and paddle
        //TODO Take into account speed of paddles

        //Work in double

        boolean hitWall = false;
        boolean hitHumanPaddle = false;
        boolean hitComputerPaddle = false;

        if (ballX <= 0 || ballX >= scrnsz ) {
            Main.gameOver = true;
            return;
        }
        if (ballY <= 0 || ballY >= scrnsz-ballSize) {
            hitWall = true;
        }

        //If ballX is at a paddle AND ballY is within the paddle size.
        //Hit human paddle?
        if (ballX >= scrnsz-(Main.paddleDistanceFromSide+(ballSize)) && (ballY > Main.humanPaddleY-Main.paddleSize && ballY < Main.humanPaddleY+Main.paddleSize)) {
            hitHumanPaddle = true;
            // AMD: set lastPaddle value to represent human
            Main.lastPaddle = "h";
        }

        //Hit computer paddle?
        if (ballX <= Main.paddleDistanceFromSide && (ballY > Main.computerPaddleY-Main.paddleSize && ballY < Main.computerPaddleY+Main.paddleSize)) {
            hitComputerPaddle = true;
            // AMD: set lastPaddle value to represent computer
            Main.lastPaddle = "c";
        }

        if (hitWall == true) {
            //bounce
            ballDirection = ( (2 * Math.PI) - ballDirection );
            System.out.println("ball direction " + ballDirection);
        }

        //Bounce off computer paddle - just need to modify direction
        if (hitComputerPaddle == true) {
            ballDirection = (Math.PI) - ballDirection;
            //TODO factor in speed into new direction
            //TODO So if paddle is moving down quickly, the ball will angle more down too

        }

        //Bounce off computer paddle - just need to modify direction
        if (hitHumanPaddle == true) {
            ballDirection = (Math.PI) - ballDirection;
            //TODO consider speed of paddle
        }

        //Now, move ball correct distance in the correct direction

        // ** TRIGONOMETRY **

        //distance to move in the X direction is ballSpeed * cos(ballDirection)
        //distance to move in the Y direction is ballSpeed * sin(ballDirection)

        ballX = ballX + (ballSpeed * Math.cos(ballDirection));
        ballY = ballY + (ballSpeed * Math.sin(ballDirection));

        // ** TRIGONOMETRY END **

    }
}
