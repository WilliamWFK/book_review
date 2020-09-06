 
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
    ArrayList<String> likedBooks = new ArrayList<String>();
    ArrayList<String> dislikedBooks = new ArrayList<String>();
    HashMap<String, String> bookRating = new HashMap<String,String>();
    HashMap<String, Book> bookMap = new HashMap<String,Book>();
    private int bookTotal = 0;
    private boolean rated;
    private String searchString;
    
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
        
        mainMenu();
        UI.setDivider(1);
        UI.clearGraphics();
        
    }
    
    /**
     *  Refreshes the UI by calling refreshMenu() and then adds buttons
     *  If there are less than 1 books added, the user
     *  cannot possibly delete, search, rate or recommend
     *  so the menu does not add those buttons.
     */
    public void mainMenu(){
        refreshMenu();
        
        UI.addButton("Add Book", this::addBook);
        if(bookTotal <= 0 != true) {
            UI.addButton("Delete Book", this::deleteBook);
            UI.addButton("Search Book", this::searchBook);
            UI.addButton("Rate Book", this::startRateBook);
            UI.addButton("Recommend Book", this::recommendBook);
        }
        
    }
    /**
     *  Refreshes the UI and adds the quit button.
     */
    public void refreshMenu(){
        UI.initialise(); 
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1);
    }
    
    /**
     *  Sets the name, author and genre of each book
     *  and then creates a new book object to add into bookMap.
     * 
     */
    
    public void addBook() { 
        refreshMenu();
        setBookName(UI.askString("Enter the title of the book: "));
        setBookAuthor(UI.askString("Enter the author of the book: "));
        setBookGenre(UI.askString("Enter the genre of the book: "));
        
        Book book = new Book(bookName, bookAuthor, bookGenre);
        bookMap.put(bookName.toLowerCase(), book);
        UI.clearText();
        UI.println(bookName + " has been sucessfully added!");
        bookTotal += 1;
        
        mainMenu();

    }
    /**
     *  Starts the process of rating books. 
     *  Starts off by asking the user for a book to rate. If the 
     *  book is found in the hashmap the bookName, bookAuthor and bookGenre
     *  will be set, else it will retry.
     *  
     *  rated will then be set to false in order to activate the condition
     *  used in mouseRate().
     *  Calls the drawButton() method to display the buttons as well as
     *  draws the question onto the canvas.
     *  
     */
    public void startRateBook() {
        refreshMenu();
        
        boolean check = false;
        while (check == false) {
            searchString = UI.askString("Enter the title of the book to rate: ").toLowerCase();
            if(checkBook(searchString) == true) {
                setBookName(bookMap.get(searchString).getName());
                setBookAuthor(bookMap.get(searchString).getAuthor());
                setBookGenre(bookMap.get(searchString).getGenre());
                check = true;
            }
            else{
                UI.println("That book isn't in the database.");
            }
        }
        UI.setMouseListener(this::mouseRate);
        rated = false;
        UI.setDivider(0);
        like.drawButton();
        dislike.drawButton();
        
        UI.setFontSize(30);
        UI.setColor(Color.black);
        UI.drawString(("Did you like '" + bookName + "'?"),75,75); // if longer than 32 bugs//
        
        
    }
    /**
     *  Ends the process of rating books.
     *  Based on param will either add bookname to likedBooks
     *  or dislikedBooks and delete the entry in the opposite
     *  ArrayList if possible.
     *  
     *  @param  liked  boolean storing whether book was liked, true = yes & viceversa
     */
    public void endRateBook(boolean liked) {
        UI.clearGraphics();
        refreshMenu();
        
        if (liked == true) {
            if(likedBooks.contains(searchString) != true) {
                likedBooks.add(searchString);
                
            }
            if(dislikedBooks.contains(searchString) == true) {
                    dislikedBooks.remove(searchString);
                }
        }
        
        if (liked == false) {
            if(dislikedBooks.contains(searchString) != true) {
                dislikedBooks.add(searchString);
                
            }
            if(likedBooks.contains(searchString) == true) {
                    likedBooks.remove(searchString);
                }
        }
        
        UI.println(likedBooks);
        UI.println(dislikedBooks);
        UI.println(bookName + " has been sucessfully rated");
        mainMenu();
    }
    
    /**
     *  Deletes a book from bookMap.
     *  Will ask the user for a book to delete which is
     *  then ran through checkBook(). If returned true
     *  will delete the book from bookMap and reduce bookTotal by 1.
     *  
     */
    public void deleteBook() {
        refreshMenu();
        
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
    
    /**
     *  Recommends a book based on books found in likedBooks.
     */
    public void recommendBook() {
       refreshMenu(); 
        
       mainMenu();
    }
    
    /**
     *  Searches a book from bookMap
     *  
     *  
     */
    public void searchBook() {
        refreshMenu();
        boolean check = false;
        while (check == false) {
            
            String searchString = UI.askString("Enter the title of the book to search: ").toLowerCase();
            if(checkBook(UI.askString(searchString)) == true) {
                check = true;
            }
            else{
                UI.println("That book isn't in the database.");
            }
            }
        setBookName(bookMap.get(searchString).getName());
        setBookAuthor(bookMap.get(searchString).getAuthor());
        setBookGenre(bookMap.get(searchString).getGenre());
        UI.println((bookName + " by " + bookAuthor + " is a " + bookGenre + " book."));
        mainMenu();
        
    }
    /**
     *  Checks to see if book exists
     *  @param  searchTerm  searchTerm to be checked
     */
    public boolean checkBook(String searchTerm){
        if(bookMap.containsKey(searchTerm.toLowerCase())){
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
    public void mouseRate (String action, double x, double y) {
        
        if(action.equals("clicked") && rated != true) {
            if(like.onButton(x,y) == true) {
                endRateBook(true);
                rated = true;
            }
            if(dislike.onButton(x,y) == true ) {
                endRateBook(false);
                rated = true;
            }
        }
        
    }
    
}

