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
// AMD: the ball also occasionally gets stuck behind the computer paddle, although this is MUCH less common.

public class Main {

    static int screenSize = 300;    //and width - screen is square
    // AMD: changed gamespeed from 75 to 25 to speed up testing.
    static int gameSpeed = 25;  //How many milliseconds between clock ticks? Reduce this to speed up game
    static Timer timer;    //Ticks every *gameSpeed* milliseconds. Every time it ticks, the ball and computer paddle move

    static GameDisplay gamePanel;   //draw the game components here

    static boolean gameOver;      //Used to work out what message, if any, to display on the screen


    public static void main(String[] args) {

        gamePanel = new GameDisplay();

        initializeGame();

        // AMD: the remaining three lines of code kick off the game.
        //Below, we'll create and start a timer that notifies an ActionListener every time it ticks
        //First, need to create the listener:
        gameClock gameUpdater = new gameClock();

        timer = new Timer(gameSpeed, gameUpdater);
        timer.start();    //Every time the timer ticks, the actionPerformed method of the ActionListener is called
        // AMD: ActionListener cited above now = gameClock object.
        // AMD: one nagging problem I see is that the game exit condition is currently buried in the GameDisplay object.
    }

    public static void initializeGame() {
        // initialize game parameters not linked directly with other objects.

        JPanel content = new JPanel();
        content.setLayout(new BorderLayout());
        content.add(gamePanel, BorderLayout.CENTER);

        JFrame window = new JFrame();
        window.setUndecorated(true);   //Hides the title bar.

        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);   //Quit the program when we close this window
        window.setContentPane(content);
        window.setSize(screenSize, screenSize);
        window.setLocation(100, 100);    //Where on the screen will this window appear?
        window.setVisible(true);

        KeyHandler listener = new KeyHandler();
        window.addKeyListener(listener);
    }

}



