package com.amdudda;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by amdudda on 10/29/2015.
 */
public class Ball {

    private static double scrnsz = Main.screenSize;
    static double  ballX = scrnsz / 2;   //Imagine the ball is in a square box. These are the coordinates of the top of that box.
    static double  ballY = scrnsz / 2;   //So this starts the ball in (roughly) the center of the screen
    static int ballSize = 10;                //Diameter of ball

    static double ballSpeed = 5;   //Again, pixels moved per clock tick
    // ball needs to remember which paddle it last touched.
    protected static String lastPaddle;  // c for computer, h for human

    //An angle in radians (which range from 0 to 2xPI (0 to about 6.3).
    //This starts the ball moving down toward the human. Replace with some of the other
    //commented out versions for a different start angle
    //set this to whatever you want (but helps if you angle towards a player)
    static double ballDirection = Math.PI + 1;   //heading left and up
    //static double ballDirection = 1;
    //static double ballDirection = 0;   //heading right
    //static double ballDirection = Math.PI;   //heading left

    // Draws the ball
    public static void draw(Graphics g) {
        // DONE: return a set of values that can be used to draw a ball.  Or should I make the ball draw itself?
        g.setColor(Color.red);
        //Ball - a circle is just an oval with the height equal to the width
        g.drawOval((int) ballX, (int) ballY, ballSize, ballSize);
        g.fillOval((int) ballX, (int) ballY, ballSize, ballSize);
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

        // AMD: AHA!  Found "stuck behind wall" bug - the ball's far edge is ballX + ballSize, not just ballX.
        if (ballX <= 0 || ballX + ballSize >= scrnsz ) {
            Main.gameOver = true;
            // AMD: and, by the way, letting the ball touch the wall counts as a loss for scoring purposes.
            if (ballX <= 0) lastPaddle = "h";  // computer's wall touched
            else lastPaddle = "c";  // human's wall touched
            return;
        }
        if (ballY <= 0 || ballY >= scrnsz-ballSize) {
            hitWall = true;
        }

        //If ballX is at a paddle AND ballY is within the paddle size.
        //Hit human paddle?
        /*
        AMD: Converted booleans to variables to make this easier to read - I suspect this is where the "trapped behind human paddle" bug arises
        http://docs.oracle.com/javase/7/docs/api/java/awt/Graphics.html#drawOval%28int,%20int,%20int,%20int%29 says the oval is
        width + 1 pixels wide and height +1 high, which is not what the commonly-found descriptions imply; they imply it's _inside_ a box
        that is h*w in size.  Does adding 1 to our ballSize for the x coordinate help with the bug? No.
        FOUND THE BUG: see "AHA!" comment above.
        */

        boolean ballYBetweenHumanPaddleEnds = ballY > HumanPaddle.PaddleY - HumanPaddle.paddleSize && ballY < HumanPaddle.PaddleY + HumanPaddle.paddleSize;
        boolean ballReachedHumanPaddlePosition = ballX + ballSize >= scrnsz - (HumanPaddle.paddleDistanceFromSide);
        if (ballReachedHumanPaddlePosition && ballYBetweenHumanPaddleEnds) {
            hitHumanPaddle = true;
            // AMD: set lastPaddle value to represent human
            lastPaddle = "h";
        }

        //Hit computer paddle?
        boolean ballYBetweenComputerPaddleEnds = ballY > ComputerPaddle.PaddleY - ComputerPaddle.paddleSize && ballY < ComputerPaddle.PaddleY + ComputerPaddle.paddleSize;
        boolean ballReachedComputerPaddlePosition = ballX <= ComputerPaddle.paddleDistanceFromSide;
        boolean ballTouchHumanWall = ballX + ballSize >= scrnsz;
        if  (ballReachedComputerPaddlePosition && ballYBetweenComputerPaddleEnds) {
            hitComputerPaddle = true;
            // AMD: set lastPaddle value to represent computer
            lastPaddle = "c";
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
