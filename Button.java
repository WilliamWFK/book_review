
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
    private final double POSITIONY = 100;
    private final double LIKEY = 75;
    private final double DISLIKEY = 350;
    
    private boolean like;
    private String button,image;
    /** takes in a boolean
     *  true = green button
     *  false = red button
     */
    public Button(boolean likeValue){
        like = likeValue;
        buttonInitialize(like);
        drawButton();
    }
    public void buttonInitialize(boolean likeValue) {
        if (likeValue == true) {
            UI.setColor(Color.green);
            this.posX = LIKEY;
            this.posY = POSITIONY;
            this.image = "images/thumbup.png";
        }
        if (likeValue == false) {
            UI.setColor(Color.red);
            this.posX = DISLIKEY;
            this.posY = POSITIONY;
            this.image = "images/thumbdown.png";
        }
        
    }
    public void drawButton(){
        buttonInitialize(like);
        UI.setLineWidth(3);
        UI.drawRect(this.posX,this.posY,WIDTH,WIDTH);
        UI.drawImage(this.image, this.posX,this.posY, WIDTH, WIDTH);
    }
    // public void setLike() {
        // UI.setColor(Color.green);
        // this.posX = POSITIONY;
        // this.posY = LIKEY;
        // this.image = "images/thumbup.png";
    // }
    // public void setDislike(){
        // UI.setColor(Color.red);
        // this.posX = POSITIONY;
        // this.posY = DISLIKEY;
        // this.image = "images/thumbdown.png";
    // }

    public static void main(String[] args){
        
    }
    
    public boolean onButton(double x,double y) {
        
        if (x >= this.posX && x <= this.posX + WIDTH &&
        y >= this.posY && y <= this.posY+WIDTH) {
            return true;
        } else {
              return false;
        }
    }
    
    public void placeholder(){
        UI.println(this.posX);
        UI.println(this.posY);
        UI.println(like);
        
    }
}


