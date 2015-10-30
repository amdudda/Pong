package com.amdudda;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by amdudda on 10/29/15.
 */
public class gameClock implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {

        //gameUpdater is an inner class
        //It's containing class is Main
        //moveBall() and moveComputerPaddle belong to the outer class - Main
        //So we have to say Main.moveBall() to refer to these methods
        Ball.moveBall();
        ComputerPaddle.movePaddle();

        if (Main.gameOver) {
            Main.timer.stop();
        }
        Main.gamePanel.repaint();
    }
}
