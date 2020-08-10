 /* Based on the VUW ecs100 template
 *
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;

/** 
 * Write a description of class Book here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Book{
    // fields for book details
    String bookGenre,bookAuthor,bookName; 
    public static final double HEIGHT =75;
    public static final double WIDTH = 75;
    public static final double WORDHEIGHT = 20;
    public static final double SPACING = 100;
    public static final double BUTTONHEIGHT =50;
    public static final double BUTTONWIDTH = 37.5;
    
    public static double posX, posY;
    
    /**
     * Constructor for objects of class Book 
     */
    public Book(String name,String author,String genre){
        bookName = name;
        bookAuthor = author;
        bookGenre = genre;
    }
    
    public void draw(int bookTotal) {
        UI.setLineWidth(3);
        int rows = bookTotal/4;
        double ySpacing = rows * (HEIGHT + 50);
        double xSpacing = (bookTotal - (rows * 4)) * SPACING;
      
        this.posX = HEIGHT+xSpacing;
        this.posY = HEIGHT+ySpacing;
        
        
        // Green Button
        UI.setColor(Color.green);
        UI.drawRect(HEIGHT+xSpacing+BUTTONWIDTH+1.5,HEIGHT*2+ySpacing+3,BUTTONWIDTH-1.5,(BUTTONHEIGHT-28));
        UI.drawImage("images/thumbup.png", HEIGHT+xSpacing+BUTTONWIDTH-6, HEIGHT*2+ySpacing-4, BUTTONHEIGHT, BUTTONWIDTH);
        // Red Button
        UI.setColor(Color.red);
        UI.drawRect(HEIGHT+xSpacing,HEIGHT*2+ySpacing+3,BUTTONWIDTH-1.5,(BUTTONHEIGHT-28));
        UI.drawImage("images/thumbdown.png", HEIGHT+xSpacing-8, HEIGHT*2+ySpacing-4, BUTTONHEIGHT, BUTTONWIDTH);
        
        UI.setColor(Color.black);
        UI.drawRect(posX,posY,HEIGHT,WIDTH);
        UI.drawString(bookName, HEIGHT+xSpacing+10, HEIGHT+ySpacing+WORDHEIGHT);
        UI.drawString(bookAuthor, HEIGHT+xSpacing+10, HEIGHT+ySpacing+WORDHEIGHT*2);
        
       
    }
    public boolean onButton(double x,double y) {
        if (x >= this.posX && x<= this.posX+HEIGHT &&
        y >= this.posY && y <= this.posY+HEIGHT) {
            return true;
        } else {
            return false;
        }
    }
    
    public void placeholder() {
        UI.println("Test");
    }


    

}

