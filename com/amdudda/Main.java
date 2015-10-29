package com.amdudda;

/*
    code forked from https://github.com/minneapolis-edu/Pong
    Added code commented and prefixed with AMD:
*/


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//TODO have paddle speed affect ball's direction
//TODO known issue - sometimes ball gets stuck behind human paddle

public class Main {

    static int screenSize = 300;    //and width - screen is square
    static int paddleSize = 25;     //Actually half the paddle size - how much to draw on each side of center
    static int paddleDistanceFromSide = 10;  //How much space between each paddle and side of screen
    // AMD: changed gamespeed from 75 to 25 to speed up testing.
    static int gameSpeed = 25;  //How many milliseconds between clock ticks? Reduce this to speed up game
    
   static int humanPaddleY = screenSize / 2 ;
    
    static int humanPaddleMaxSpeed = 5;   //This doesn't quite do the same thing... this is how many pixels human moves per key press TODO use this in a better way
    
    static int humanPaddleSpeed = 0;      // "speed" is pixels moved up or down per clock tick

    


    static Timer timer;    //Ticks every *gameSpeed* milliseconds. Every time it ticks, the ball and computer paddle move

    static GameDisplay gamePanel;   //draw the game components here
    
    static boolean gameOver;      //Used to work out what message, if any, to display on the screen
    static boolean removeInstructions = false;  // Same as above

    // AMD: additional global variables used for scoring
    static String lastPaddle;  // c for computer, h for human

    // AMD: and a variable to track whether a game can be restarted - using gameOver
    // results in scoring-on-every-event and restart-at-all-spacebar-keyPressed bugs.
    static boolean restartable = false;


    public static void main(String[] args) {
        
        gamePanel = new GameDisplay();

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(gamePanel, BorderLayout.CENTER);
        
        JFrame window = new JFrame();
        window.setUndecorated(true);   //Hides the title bar.

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   //Quit the program when we close this window
        window.setContentPane(content);
        window.setSize(screenSize, screenSize);
        window.setLocation(100,100);    //Where on the screen will this window appear?
        window.setVisible(true);

        KeyHandler listener = new KeyHandler();
        window.addKeyListener(listener);

        //Below, we'll create and start a timer that notifies an ActionListener every time it ticks
        //First, need to create the listener:
        ActionListener gameUpdater = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //gameUpdater is an inner class
                //It's containing class is Main
                //moveBall() and moveComputerPaddle belong to the outer class - Main
                //So we have to say Main.moveBall() to refer to these methods
                Ball.moveBall();
                ComputerPaddle.movePaddle();

                if (gameOver) {
                    timer.stop();
                }
                gamePanel.repaint();
            }
        };
        
        timer = new Timer(gameSpeed, gameUpdater);
        timer.start();    //Every time the timer ticks, the actionPerformed method of the ActionListener is called
    }

    /*//Uses the current position of ball and paddle to move the computer paddle towards the ball
    protected static void moveComputerPaddle(){

        //if ballY = 100 and paddleY is 50, difference = 50, need to adjust
        //paddleY by up to the max speed (the minimum of difference and maxSpeed)

        //if ballY = 50 and paddleY = 100 then difference = -50
        //Need to move paddleY down by the max speed

        int ballPaddleDifference = computerPaddleY - (int) Ball.ballY;
        int distanceToMove = Math.min(Math.abs(ballPaddleDifference), computerPaddleMaxSpeed);

        // AMD: commented out: System.out.println("computer paddle speed = " + computerPaddleSpeed);

        if (ballPaddleDifference > 0 ) {   //Difference is positive - paddle is below ball on screen
            computerPaddleY -= distanceToMove;

        } else if (ballPaddleDifference < 0){
            computerPaddleY += distanceToMove;

        } else {
            //Ball and paddle are aligned. Don't need to move!
            computerPaddleSpeed = 0;
        }

    }
*/



}



