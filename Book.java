
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
    int number, rows, bookNumber;
    public static final double HEIGHT =75;
    public static final double WIDTH = 75;
    public static final double WORDHEIGHT = 20;
    public static final double SPACING = 100;
    public static final double BUTTONHEIGHT =50;
    public static final double BUTTONWIDTH = 37.5;
    
    public static double posX, posY, xSpacing, ySpacing,posXReal,posYReal;
    
    /**
     * Constructor for objects of class Book 
     */
    public Book(String name,String author,String genre, int bookNumber){
        bookName = name;
        bookAuthor = author;
        bookGenre = genre;
        number = bookNumber;
        
        
        rows = number/4;
        ySpacing = rows * (HEIGHT + 50);
        xSpacing = (number - (rows * 4)) * SPACING;
      
        this.posX = HEIGHT + xSpacing;
        this.posY = HEIGHT + ySpacing;
        UI.println(posX);
        UI.println(posY);
        
        
    }
    
    /**
     *  Draws the book object, takes in number of existing books as a parameter
     */
    public void draw() {
        posXReal = 50;
        posYReal = 50;
        
        UI.setLineWidth(3);
        // Green Button
        UI.setColor(Color.green);
        UI.drawRect(HEIGHT+xSpacing+BUTTONWIDTH+1.5,
                    HEIGHT*2+ySpacing+3,BUTTONWIDTH-1.5,
                    (BUTTONHEIGHT-28));
        UI.drawImage("images/thumbup.png", 
                    HEIGHT+xSpacing+BUTTONWIDTH-6, 
                    HEIGHT*2+ySpacing-4, 
                    BUTTONHEIGHT, 
                    BUTTONWIDTH);
        
        // Red Button
        UI.setColor(Color.red);
        UI.drawRect(HEIGHT+xSpacing,
                    HEIGHT*2+ySpacing+3,
                    BUTTONWIDTH-1.5,
                    (BUTTONHEIGHT-28));
        UI.drawImage("images/thumbdown.png", 
                    HEIGHT+xSpacing-8, 
                    HEIGHT*2+ySpacing-4, 
                    BUTTONHEIGHT, 
                    BUTTONWIDTH);
        
        // Box
        UI.setColor(Color.black);
        UI.drawRect(posX,posY,HEIGHT,WIDTH);
        UI.drawString(bookName, 
                     HEIGHT+xSpacing+10, 
                     HEIGHT+ySpacing+WORDHEIGHT);
        UI.drawString(bookAuthor, 
                     HEIGHT+xSpacing+10, 
                     HEIGHT+ySpacing+WORDHEIGHT*2);
        
       
    }
    /**
     *  Checks to see if mouse is on a book object
     */
    public boolean onButton(double x,double y) {
        
        if (x >= this.posX && x <= this.posX + WIDTH &&
        y >= this.posY && y <= this.posY+HEIGHT) {
            return true;
        } else {
            return false;
        }
    }
    
    public String placeholder() {
        UI.println(this.bookName);
        UI.println(this.posX);
        UI.println(this.posY);
       
        UI.println(this.number);
        return bookName;
    }
    
    


    

}

