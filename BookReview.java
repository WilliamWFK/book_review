/* Based on the VUW ecs100 template
 *
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

/** 
 * Write a description of class BookReview here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class BookReview{
    
    public String bookAuthor,bookName,bookGenre; // variables for book name
    ArrayList<Book> books = new ArrayList<Book>();
    Map<String, Book> bookMap = new TreeMap<String,Book>();
    private int bookTotal = 0;
    
    
    
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
    public static void main(){
        
        BookReview br = new BookReview();
        UI.addButton("Quit", UI::quit); 
        UI.addButton("Add Book", br::addBook);
        UI.setMouseListener(br::manageMouse);
        UI.setDivider(0.4);
        
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
        //books.add(new Book(bookName, bookAuthor, bookGenre));
        bookMap.put(bookName, new Book(bookName, bookAuthor, bookGenre));
        UI.println(bookName + "has been sucessfully added!");
       
        bookMap.get(bookName).draw( bookTotal);
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
    
    /**
     * 
     */
    public void manageMouse (String action, double x, double y) {
        if(action.equals("clicked")) {
            
            for (Map.Entry<String, Book> entry : bookMap.entrySet())
            {
               String key = entry.getKey();
               Object value = entry.getValue();
               if (entry.getValue().onButton(x,y) == true) {
                   entry.getValue().placeholder();
                }
            }
        }
    }
}

