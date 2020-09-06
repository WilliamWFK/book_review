
/* Based on the ecs 100 template
 * Code for ??
 * Name:
 * Date:
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/**
 *  Button class that detects whether book has been liked/disliked.
 *
 */
public class Button {
    /**
     *  Position of this button.
     */
    private double posX, posY;
    /**
     * Width used in making square buttons.
     */
    private final double WIDTH = 200;
    /**
     * Y position of button to be set.
     */
    private final double POSITIONY = 100;
    /**
     * X position of Like button.
     */
    private final double LIKEY = 75;
    /**
     * Y position of dislike button.
     */
    private final double DISLIKEY = 350;
    /**
     * Boolean storing whether button is a like or dislike.
     */
    private boolean like;
    /**
     * String storing directory of button image.
     */
    private String image;
    /** Constructor of button class.
     *  @param  LIKE_VALUE  Which state the button is, true = like
     */
    public Button(final boolean LIKE_VALUE) {
        like = LIKE_VALUE;
        buttonInitialize(like);
        drawButton();
    }
    /**
     *  Sets-up the button.
     *  @param  LIKE_VALUE  Which state the button is, true = like
     */
    public void buttonInitialize(final boolean LIKE_VALUE) {
        if (LIKE_VALUE) {
            UI.setColor(Color.green);
            this.posX = LIKEY;
            this.posY = POSITIONY;
            this.image = "images/thumbup.png";
        }
        if (!LIKE_VALUE) {
            UI.setColor(Color.red);
            this.posX = DISLIKEY;
            this.posY = POSITIONY;
            this.image = "images/thumbdown.png";
        }
    }
    /**
     * Draws button on canvas.
     */
    public void drawButton() {
        buttonInitialize(like);
        UI.setLineWidth(3);
        UI.drawRect(this.posX, this.posY, WIDTH, WIDTH);
        UI.drawImage(this.image, this.posX, this.posY, WIDTH, WIDTH);
    }
    /**
     *  Checks to see if mouse is on button.
     *  @param X x-positon of mouse
     *  @param Y y-positoon of mouse
     *  @return Whether or not mouse is on button, true = yes
     */
    public boolean onButton(final double X, final double Y) {
        return (X >= this.posX && X <= this.posX + WIDTH
        && Y >= this.posY && Y <= this.posY + WIDTH);
    }
}


