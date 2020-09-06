 
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
    //ArrayList<String> unratedBooks = new ArrayList<String>();
    ArrayList<String> dislikedBooks = new ArrayList<String>();
    
    //  Two dimensional ArrayList to store recommendations
    ArrayList<ArrayList<String>> recommendedBooks = 
            new ArrayList<ArrayList<String>>();
    
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
        UI.println("Welcome to book recommender.\nTo start, please add a book.");
        
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
        boolean valid = false;
        
        while (valid != true) {
            setBookName(UI.askString("Enter the title of the book: "));
            if (bookName.length() == 0) {
                UI.println("Please enter a book name");
            }
            else if (bookMap.containsKey(bookName.toLowerCase())) {
                UI.println("Please enter a unique book name.");
            }
            else {
                valid = true;  
            }
        }
        
        valid = false;
        while (valid != true) {
            setBookAuthor(UI.askString("Enter the author of the book: "));
            if (bookAuthor.length() == 0) {
                UI.println("Please enter a book author");
            }
            else {
                valid = true;  
            }
        }
        
        valid = false;
        while (valid != true) {
            setBookGenre(UI.askString("Enter the genre of the book: "));
            if (bookGenre.length() == 0) {
                UI.println("Please enter a book genre");
            }
            else {
                valid = true;  
            }
        }
        
        
        
        
        Book book = new Book(bookName, titleCase(bookAuthor), titleCase(bookGenre));
        //unratedBooks.add(bookName.toLowerCase());
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
            searchString = UI.askString("Enter the title of the book to search: ").toLowerCase();
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
     *  s
     *  @param  liked  boolean storing whether book was liked, true = yes & viceversa
     */
    public void endRateBook(boolean liked) {
        UI.clearGraphics();
        refreshMenu();
        
        if (liked == true) {
            
            if(likedBooks.contains(searchString) != true) {
                likedBooks.add(searchString);
                //unratedBooks.remove(searchString);
                
            }
            if(dislikedBooks.contains(searchString) == true) {
                    dislikedBooks.remove(searchString);
                }
        }
        
        if (liked == false) {
            if(dislikedBooks.contains(searchString) != true) {
                dislikedBooks.add(searchString);
                //unratedBooks.remove(searchString);
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
            searchString = UI.askString("Enter the title of the book to delete: ").toLowerCase();
            if(checkBook(searchString) == true) {
                check = true;
                bookMap.remove(searchString);
                likedBooks.remove(searchString);
                dislikedBooks.remove(searchString);
                bookTotal -= 1;
            }
            else{
                UI.println("That book isn't in the database.");
            }
            
        }
        
        mainMenu();
        UI.println((searchString + " has been deleted."));
    }
    
    /**
     *  Recommends a book based on books found in likedBooks.
     */
    public void recommendBook() {
       recommendedBooks.clear();
       ArrayList<String> bookRecommend = new ArrayList<String>();
       bookRecommend.clear();
       // loop through all books, checking to see if author or genre is a match
       if (likedBooks.size() == 0) {
           UI.println("Please like a book before asking for a recommendation!");
        }
       else{
            for(String likedBook : likedBooks) {
               
               for (Map.Entry<String, Book> entry : bookMap.entrySet())
                {
                   String book = entry.getKey();
                   
                   if (dislikedBooks.contains(book) != true && 
                        likedBooks.contains(book) != true) {
                       
                       
                       if (bookMap.get(likedBook).getAuthor().equals(
                            bookMap.get(book).getAuthor()) == true) {
                           
                           bookRecommend.add(bookMap.get(likedBook).getName());
                           bookRecommend.add(bookMap.get(book).getName());
                           bookRecommend.add("author");
                           recommendedBooks.add(bookRecommend);
                           
                           
                           UI.println("Added author");
                           bookRecommend = new ArrayList<String>();
                        } 
                        if (bookMap.get(likedBook).getGenre().equals(
                            bookMap.get(book).getGenre()) == true) {
                           
                           bookRecommend.add(bookMap.get(likedBook).getName());
                           bookRecommend.add(bookMap.get(book).getName());
                           bookRecommend.add("genre");
                           recommendedBooks.add(bookRecommend);
                           
                           
                           UI.println("Added genre");
                           bookRecommend = new ArrayList<String>();
                        } 
                    }
                    
                }
            }
        
           if (recommendedBooks.size() > 0) {
           int randNum = (int) (Math.random() * (double)(recommendedBooks.size()));
           UI.println(("Because you liked " + recommendedBooks.get(randNum).get(0)
                        + ", you might enjoy " + recommendedBooks.get(randNum).get(1)
                        + " as they have the same " + recommendedBooks.get(randNum).get(2)));
            }
            else {
                UI.println("We cannot recommend anything as there are not enough non-rated books.");
            }
        }
    }
    
    /**
     *  Converts a string into Title Case
     *  Credit to https://www.baeldung.com/java-string-title-case
     *  
     *  @param text text to be converted
     */
    public String titleCase(String text){
        if (text == null || text.isEmpty()) {
            return text;
        }
     
        StringBuilder converted = new StringBuilder();
     
        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }
     
        return converted.toString();
    }
    /**
     * 
     */
    public boolean checkString(String text){
        return true;
    }
    /**
     *  Searches a book from bookMap
     *  
     *  
     */
    public void searchBook() {
        refreshMenu();
        boolean check = false;
        while (check  != true) {
            
            searchString = UI.askString("Enter the title of the book to rate: ").toLowerCase();
            if(checkBook(searchString) == true) {
                check = true;
                
            }
            else{
                UI.println("That book isn't in the database.");
            }
            }
        setBookName(bookMap.get(searchString).getName());
        setBookAuthor(bookMap.get(searchString).getAuthor());
        setBookGenre(bookMap.get(searchString).getGenre());
        
        mainMenu();
        UI.println((bookName + " by " + bookAuthor + " is a " + bookGenre + " book."));
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
     *  Sets bookAuthor
     *  @param author Name of author
     */
    public void setBookAuthor(String author){
        this.bookAuthor = author;
        
    }
    
    /**
     *  Sets bookName
     *  @param name Name of book
     */
    public void setBookName(String name){
        this.bookName = name;
        
    }
    
    /**
     *  Set bookGenre
     *  @param  genre  Genre of book, parse in a single genre only
     */
    public void setBookGenre(String genre){
        this.bookGenre = genre;
        
    }
    
    /**
     *  Mouse manager used in rating books
     *  
     *  @param  action  What action mouse has performed, i.e clicked, released
     *  @param  x  x-value of mouse
     *  @param  y  y-value of mouse
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

