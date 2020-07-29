/* Based on the VUW ecs100 template
 *
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import java.util.ArrayList;

/** 
 * Write a description of class BookReview here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BookReview{
    
    private String bookAuthor,bookName,bookGenre; // variables for book name
    ArrayList<Book> books = new ArrayList<Book>();
    private int bookTotal = 0;
    public static final int SPACING = 100;
    
    
    /**
     * Constructor for objects of class BookReview 
     */
    public BookReview(){
    UI.initialise();
    
    }


    /**
     * Main routine
     *
     */
    public static void main(String[] args){
        
        BookReview br = new BookReview();
        UI.addButton("Quit", UI::quit); 
        UI.addButton("Add Book", br::addBook);
        
        // UI.addTextField("Book Name", br::setBookName);
        // UI.addTextField("Book Author", br::setBookAuthor);
        // UI.addTextField("Book Genre", br::setBookGenre);
        // UI.setKeyListener(br::test);
    }
    
    /**
     * Adds book, requires field bookName, bookAuthor, bookGenre
     * 
     */
    
    public void addBook(){
        setBookName(UI.askString("Enter the title of the book: "));
        setBookAuthor(UI.askString("Enter the author of the book: "));
        setBookGenre(UI.askString("Enter the genre of the book: "));
        books.add(new Book(bookName, bookAuthor, bookGenre));
        UI.println(bookName + "has been sucessfully added!");
        
        books.get(bookTotal).draw(SPACING * bookTotal, SPACING/4);
        bookTotal += 1;
        
        
    }
    
    /**
     * 
     */
    public void setBookAuthor(String author){
        this.bookAuthor = author;
        
    }
    
    /**
     * 
     */
    public void setBookName(String name){
        this.bookName = name;
        
    }
    
    /**
     * 
     */
    public void setBookGenre(String genre){
        this.bookGenre = genre;
        
    }
}

