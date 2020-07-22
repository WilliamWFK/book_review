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
public class Book extends BookReview{
    // fields for book details
    String bookGenre,bookAuthor,bookName; 
    public static final double HEIGHT =75;
    public static final double WIDTH = 75;
    public static final double WORDHEIGHT = 20;
    
    public static double x, y;
    
    /**
     * Constructor for objects of class Book 
     */
    public Book(String name,String author,String genre){
        bookName = name;
        bookAuthor = author;
        bookGenre = genre;
    }
    
    public void draw(double xSpacing,double ySpacing) {
        UI.setColor(Color.black);
        UI.setLineWidth(3);
        UI.drawRect(HEIGHT+xSpacing,HEIGHT+ySpacing,HEIGHT,HEIGHT);
        // UI.drawRect(xSpacing , HEIGHT + xSpacing,ySpacing,WIDTH + ySpacing);
        UI.drawString(bookName, HEIGHT+xSpacing+10, HEIGHT+ySpacing+WORDHEIGHT);
        UI.drawString(bookAuthor, HEIGHT+xSpacing+10, HEIGHT+ySpacing+WORDHEIGHT*2);
       
    }


    

}

