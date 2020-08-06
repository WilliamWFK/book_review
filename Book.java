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
    
    public static double x, y;
    
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
      
        
        // Green Button
        UI.setColor(Color.green);
        UI.drawRect(HEIGHT+xSpacing+BUTTONWIDTH+1.5,BUTTONHEIGHT+ySpacing,BUTTONWIDTH,(BUTTONHEIGHT-28));
        // Red Button
        UI.setColor(Color.red);
        UI.drawRect(HEIGHT+xSpacing,BUTTONHEIGHT+ySpacing,BUTTONWIDTH-1.5,(BUTTONHEIGHT-28));
        
        UI.setColor(Color.black);
        UI.drawRect(HEIGHT+xSpacing,HEIGHT+ySpacing,HEIGHT,HEIGHT);
        UI.drawString(bookName, HEIGHT+xSpacing+10, HEIGHT+ySpacing+WORDHEIGHT);
        UI.drawString(bookAuthor, HEIGHT+xSpacing+10, HEIGHT+ySpacing+WORDHEIGHT*2);
        
       
    }


    

}

