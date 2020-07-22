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
public class BookReview extends Book{
    
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
        UI.addTextField("Book Name", br::setBookName);
        UI.addTextField("Book Author", br::setBookAuthor);
        UI.addTextField("Book Genre", br::setBookGenre);
        // UI.setKeyListener(br::test);
    }
    
    /**
     * 
     */
    
    public void addBook(){
        books.add(new Book(bookName, bookAuthor, bookGenre));
        UI.println(bookName + "has been sucessfully added!");
        
        books.get(bookTotal).draw(SPACING * bookTotal, SPACING/4);
        bookTotal += 1;
        UI.println(books);
    }
    
    /**
     * 
     */
    public void setBookAuthor(String author){
        this.bookAuthor = author;
        UI.println("Author set!");
    }
    
    /**
     * 
     */
    public void setBookName(String name){
        this.bookName = name;
        UI.println("Name set!");
    }
    
    /**
     * 
     */
    public void setBookGenre(String genre){
        this.bookGenre = genre;
        UI.println("Genre set!");
    }
}

