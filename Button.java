/* Based on the ecs 100 template
 * Code for ??
 * Name:
 * Date:
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;


/** <description of class Button>
 */
public class Button{
    private double posX, posY;
    private final double WIDTH = 200;
    private final double POSITIONX = 100;
    private final double LIKEY = 75;
    private final double DISLIKEY = 350;
    private String button,image;
    /** takes in a boolean
     *  true = green button
     *  false = red button
     */
    public Button(boolean like){
        if (like == true) {
            setLike();
        }
        else if (like == false) {
            setDislike();
        }
        drawButton();
    }
    public void drawButton(){
        UI.setLineWidth(3);
        UI.drawRect(posY,posX,WIDTH,WIDTH);
        UI.drawImage(this.image, posY, posX, WIDTH, WIDTH);
    }
    public void setLike() {
        UI.setColor(Color.green);
        this.posX = POSITIONX;
        this.posY = LIKEY;
        this.image = "images/thumbup.png";
    }
    public void setDislike(){
        UI.setColor(Color.red);
        this.posX = POSITIONX;
        this.posY = DISLIKEY;
        this.image = "images/thumbdown.png";
    }

    public static void main(String[] args){
        
    }
    
    public boolean onButton(double x,double y) {
        
        // if (x >= this.posX && x <= this.posX + WIDTH &&
        // y >= this.posY && y <= this.posY+HEIGHT) {
            // return true;
        // } else {
               return false;
        // }
    }
}

