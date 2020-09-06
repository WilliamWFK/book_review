
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
    int number;

    /**
     * Constructor for objects of class Book 
     */
    public Book(String name,String author,String genre){
        this.bookName = name;
        this.bookAuthor = author;
        this.bookGenre = genre;
        
    }
    
    /**
     * 
     */
    public String getName() {
        return this.bookName;
    }
    /**
     * 
     */
    public String getAuthor() {
        return this.bookAuthor;
    }
    /**
     * 
     */
    public String getGenre() {
        return this.bookGenre;
    }
   
    
    


    

}

