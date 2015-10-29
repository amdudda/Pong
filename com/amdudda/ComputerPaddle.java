package com.amdudda;

/**
 * Created by tk0654wm on 10/29/2015.
 */
public class ComputerPaddle extends Paddle {

    static int PaddleSpeed = 0;   //  // "speed" is pixels moved up or down per clock tick
    static int PaddleMaxSpeed = 3;   //Max number of pixels that computer paddle can move clock tick. Higher number = easier for computer
    static int PaddleY = Main.screenSize / 2 ;    //location of the center of the paddles on the Y-axis of the screen



    //Uses the current position of ball and paddle to move the computer paddle towards the ball
    protected static void movePaddle(){

        //if ballY = 100 and paddleY is 50, difference = 50, need to adjust
        //paddleY by up to the max speed (the minimum of difference and maxSpeed)

        //if ballY = 50 and paddleY = 100 then difference = -50
        //Need to move paddleY down by the max speed

        int ballPaddleDifference = PaddleY - (int) Ball.ballY;
        int distanceToMove = Math.min(Math.abs(ballPaddleDifference), PaddleMaxSpeed);

        // AMD: commented out: System.out.println("computer paddle speed = " + computerPaddleSpeed);

        if (ballPaddleDifference > 0 ) {   //Difference is positive - paddle is below ball on screen
            PaddleY -= distanceToMove;

        } else if (ballPaddleDifference < 0){
            PaddleY += distanceToMove;

        } else {
            //Ball and paddle are aligned. Don't need to move!
            PaddleSpeed = 0;
        }

    }
}
