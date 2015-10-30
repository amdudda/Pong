package com.amdudda;

import javax.swing.*;
import java.awt.*;

/**
 * Created by amdudda on 10/28/2015.
 */
public class GameDisplay extends JPanel {

    static int humanScore = 0;
    static int computerScore = 0;
    static boolean removeInstructions = false;  // Same as above
    // AMD: and a variable to track whether a game can be restarted - using gameOver
    // results in scoring-on-every-event and restart-at-all-spacebar-keyPressed bugs.
    static boolean restartable = false;

    protected static void restartGame() {
        // AMD: Restarts the game - keylistener will listen for SPACEBAR to restart.
        GameDisplay.restartable = false;
        Main.timer.start();
        Ball.ballX = Main.screenSize / 2;
        Ball.ballY = Main.screenSize / 2;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // System.out.println("* Repaint *");

        if (Main.gameOver == true) {
            g.drawString("Game over!", 20, 30);
            //AMD: commented this out to enable restart of game
            // return;
            // AMD: Add prompt to quit or restart game
            g.drawString("Press 'q' to quit or", 20, 60);
            g.drawString("press the space bar for a new game.", 20, 75);
            // AMD: update game scores and report who won.
            String winner;
            if (Ball.lastPaddle.equals("c")) {
                winner = "The computer wins!";
                computerScore++;
                // AMD: need to puzzle out why this doesn't work
                // ComputerPaddle.addPoint();
            } else {
                winner = "You win!";
                humanScore++;
            }
            g.drawString(winner, 20, 120);
            g.drawString("Current score:", 20, 135);
            g.drawString("Computer: " + computerScore + " points.", 40, 150);
            g.drawString("You: " + humanScore + " points.", 40, 165);
            // AMD: update our restartable and gameover flags
            restartable = true;
            Main.gameOver = false;
        }

        if (removeInstructions == false) {
            // AMD: set the current drawing color to green
            g.setColor(Color.green);
            g.drawString("Pong! Press up or down to move", 20, 30);
            g.drawString("Press q to quit", 20, 60);
        }

        g.setColor(Color.blue);

        //While game is playing, these methods draw the ball, paddles, using the global variables
        //Other parts of the code will modify these variables

        /* AMD:
            Set the drawing color to red, use fill oval and drawOval so both interior and boundary are colored in,
            and then reset the color back to blue.
            (Skipped reset - the color is set when the paddles are drawn.
        */
        Ball.draw(g);
        ComputerPaddle.draw(g);
        HumanPaddle.draw(g);
        /*
        AMD: Replaced the lines below with various invocations of draw methods above.
        g.setColor(Color.red);
        g.drawOval((int) Ball.ballX, (int) Ball.ballY, Ball.ballSize, Ball.ballSize);
        g.fillOval((int) Ball.ballX, (int) Ball.ballY, Ball.ballSize, Ball.ballSize);
        g.setColor(Color.blue);
        //Computer paddle
        g.drawLine(ComputerPaddle.paddleDistanceFromSide, ComputerPaddle.PaddleY - ComputerPaddle.paddleSize, ComputerPaddle.paddleDistanceFromSide, ComputerPaddle.PaddleY + ComputerPaddle.paddleSize);
        //Human paddle
        g.drawLine(Main.screenSize - HumanPaddle.paddleDistanceFromSide, HumanPaddle.PaddleY - HumanPaddle.paddleSize, Main.screenSize - HumanPaddle.paddleDistanceFromSide, HumanPaddle.PaddleY + HumanPaddle.paddleSize);
        */

    }

}
