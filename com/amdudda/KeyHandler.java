package com.amdudda;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by amdudda on 10/28/2015.
 */
public class KeyHandler implements KeyListener {
    //Listen for user pressing a key, and moving human paddle in response

    @Override
    public void keyTyped(KeyEvent ev) {
        char keyPressed = ev.getKeyChar();
        char q = 'q';
        if( keyPressed == q){
            System.exit(0);    //quit if user presses the q key.
        }
    }

    @Override
    public void keyReleased(KeyEvent ev) {}   //Don't need this one, but required to implement it.

    @Override
    public void keyPressed(KeyEvent ev) {

        GameDisplay.removeInstructions = true;   //game has started

        if (ev.getKeyCode() == KeyEvent.VK_DOWN) {
            // AMD: commented out: System.out.println("down key");
            HumanPaddle.moveDown();
        }
        if (ev.getKeyCode() == KeyEvent.VK_UP) {
            // AMD: commented out: System.out.println("up key");
            HumanPaddle.moveUp();
        }
        // AMD: added handler to detect spacebar
        if (ev.getKeyCode() == KeyEvent.VK_SPACE) {
            //restart the game if restartable is true
            if (GameDisplay.restartable) {
                GameDisplay.restartGame();
            }
        }

        //ev.getComponent() returns the GUI component that generated this event
        //In this case, it will be GameDisplay JPanel
        ev.getComponent().repaint();   //This calls paintComponent(Graphics g) again
    }

}
