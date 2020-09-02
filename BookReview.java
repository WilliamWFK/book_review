 
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
    ArrayList<Book> bookArray = new ArrayList<Book>();
    HashMap<String, String> bookRating = new HashMap<String,String>();
    HashMap<String, Book> bookMap = new HashMap<String,Book>();
    private int bookTotal = 0;
    
    Button like = new Button(true);
    Button dislike = new Button(false);
    
    
    /**
     * Constructor for objects of class BookReview 
     */
    public BookReview(){
        main();
    
    
    }


    /**
     * Main routine
     *
     */
    public void main(){
        
        //BookReview br = new BookReview();
        
        
        mainMenu();
        UI.setDivider(1);
        UI.clearGraphics();
        
        
        // UI.addTextField("Book Name", br::setBookName);
        // UI.addTextField("Book Author", br::setBookAuthor);
        // UI.addTextField("Book Genre", br::setBookGenre);
        // UI.setKeyListener(br::test);
    }
    public void mainMenu(){
        UI.setDivider(1);
        UI.addButton("Quit", UI::quit); 
        UI.addButton("Add Book", this::addBook);
        if(bookTotal <= 0 != true) {
            UI.addButton("Delete Book", this::deleteBook);
            UI.addButton("Search Book", this::searchBook);
            UI.addButton("Rate Book", this::rateBook);
            UI.addButton("Recommend Book", this::recommendBook);
        }
        
    }
   
    
    /**
     * Adds book, requires field bookName, bookAuthor, bookGenre
     * 
     */
    
    public void addBook() { 
        UI.initialise(); 
        UI.setDivider(1);
        setBookName(UI.askString("Enter the title of the book: "));
        setBookAuthor(UI.askString("Enter the author of the book: "));
        setBookGenre(UI.askString("Enter the genre of the book: "));
        //books.add(new Book(bookName, bookAuthor, bookGenre))
        Book book = new Book(bookName, bookAuthor, bookGenre);
        bookMap.put(bookName, book);
        UI.clearText();
        UI.println(bookName + " has been sucessfully added!");
        bookTotal += 1;
        
        mainMenu();

    }
    public void rateBook() {
        UI.initialise(); 
        boolean check = false;
        while (check == false) {
            String searchString = UI.askString("Enter the title of the book to rate: ");
            if(checkBook(searchString) == true) {
                
                check = true;
            }
            else{
                UI.println("That book isn't in the database.");
            }
        }
        UI.setMouseListener(this::mouseRate);
        boolean rated = false;
        UI.setDivider(0);
        while (rated != true) {
            rated = true;
        }
        mainMenu();
    }
    
    public void deleteBook() {
        UI.initialise(); 
        
        boolean check = false;
        while (check == false) {
        String searchString = UI.askString("Enter the title of the book to delete: ");
        if(checkBook(searchString) == true) {
            check = true;
            bookMap.remove(searchString);
            bookTotal -= 1;
        }
        else{
            UI.println("That book isn't in the database.");
        }
        
        }
        mainMenu();
    }
    
    public void recommendBook() {
        UI.initialise(); 
        
        mainMenu();
    }
    
    public void searchBook() {
        UI.initialise(); 
        boolean check = false;
        while (check == false) {
            
            String searchString = UI.askString("Enter the title of the book to search: ");
            if(checkBook(UI.askString(searchString)) == true) {
                check = true;
                
            }
            else{
                UI.println("That book isn't in the database.");
            }
            mainMenu();
        }
    }
    /**
     *  Checks to see if book exists
     *  @string searchTerm - searchTerm to be checked
     */
    public boolean checkBook(String searchTerm){
        if(bookMap.containsKey(searchTerm)){
            return true;
        }
        else{
            return false;
        }
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
    public boolean mouseRate (String action, double x, double y) {
        if(action.equals("clicked")) {
            if(like.onButton(x,y) == true) {
                like.placeholder();
            }
            if(dislike.onButton(x,y) == true ) {
                dislike.placeholder();
            }
        }
    }
}

