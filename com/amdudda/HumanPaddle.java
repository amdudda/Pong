package com.amdudda;

import java.awt.*;

/**
 * Created by tk0654wm on 10/29/2015.
 */
public class HumanPaddle extends Paddle {

    static int PaddleY = Main.screenSize / 2 ;
    static int PaddleMaxSpeed = 5;   //This doesn't quite do the same thing... this is how many pixels human moves per key press TODO use this in a better way
    static int PaddleSpeed = 0;      // "speed" is pixels moved up or down per clock tick

    protected static void draw(Graphics g) {
        // draw paddle
        g.setColor(Color.blue);
        g.drawLine(Main.screenSize - HumanPaddle.paddleDistanceFromSide, HumanPaddle.PaddleY - HumanPaddle.paddleSize, Main.screenSize - HumanPaddle.paddleDistanceFromSide, HumanPaddle.PaddleY + HumanPaddle.paddleSize);

    }

    // some methods to move the paddle around - these origninally lived in KeyHandler, but they're really things
    // that HumanPaddle does.
    protected static void moveDown() {
        //Coordinates decrease as you go up the screen, that's why this looks backwards.
        if (HumanPaddle.PaddleY < Main.screenSize - HumanPaddle.paddleSize) {
            HumanPaddle.PaddleY+=HumanPaddle.PaddleMaxSpeed;
        }
    }

    protected static void moveUp() {
        //Coordinates increase as you go down the screen, that's why this looks backwards.
        if (HumanPaddle.PaddleY > HumanPaddle.paddleSize) {
            HumanPaddle.PaddleY-=HumanPaddle.PaddleMaxSpeed;
        }
    }
}
