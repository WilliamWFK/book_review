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
 *  Main class of BookReview.
 *
 *  @author William Kho
 *  @version 6/09/2020
 */
public class BookReview {
    /**
     *  Variables used in creating bookObject.
     */
    private String bookAuthor, bookName, bookGenre;
    /**
     *  ArrayLists storing liked books.
     */
    private ArrayList<String> likedBooks = new ArrayList<String>();
    /**
     *  ArrayLists storing disliked books.
     */
    private ArrayList<String> dislikedBooks = new ArrayList<String>();
    /**
     *  2D Array list storing book recommendations, held within are the pairings
     *  (First book name, Second book name, trait in common).
     *  i.e (Mistborn, Stormlight, author)
     */
    private ArrayList<ArrayList<String>> recommendedBooks =
            new ArrayList<ArrayList<String>>();
    /**
     *  Hashmap storing book objects with lowercase book titles as the key.
     */
    private HashMap<String, Book> bookMap = new HashMap<String, Book>();
    /**
     *  Variable used to store total amount of books for mainMenu logic.
     */
    private int bookTotal = 0;
    /**
     *  Boolean used in mouseRate logic.
     */
    private boolean rated;
    /**
     *  Variable used to store book titles input by user, primarily used
     *  to be parsed in bookMap.
     */
    private String searchString;
    /**
     *  Creates a new button class that has the like attribute.
     */
    private Button like = new Button(true);
    /**
     *  Creates a new button class that has the dislike attribute.
     */
    private Button dislike = new Button(false);
    /**
     *  Font size used in question of startRateBook().
     */
    private final double FONT = 30;
    /**
     *  Position of text in question of startRateBook().
     */
    private final double POSITION = 75;
    /**
     * Constructor for objects of class BookReview.
     */
    public BookReview() {
        main();
    }
    /**
     * Main routine.
     */
    public void main() {
        mainMenu();
        UI.setDivider(1);
        UI.clearGraphics();
        UI.println(("Welcome to book recommender.\n"
                  + "To start, please add a book."));
    }
    /**
     *  Refreshes the UI by calling refreshMenu() and then adds buttons
     *  If there are less than 1 books added, the user
     *  cannot possibly delete, search, rate or recommend
     *  so the menu does not add those buttons.
     */
    public void mainMenu() {
        refreshMenu();
        UI.addButton("Add Book", this::addBook);
        if (bookTotal > 0) {
            UI.addButton("Delete Book", this::deleteBook);
            UI.addButton("Search Book", this::searchBook);
            UI.addButton("Rate Book", this::startRateBook);
            UI.addButton("Recommend Book", this::recommendBook);
        }
    }
    /**
     *  Refreshes the UI and adds the quit button.
     */
    public void refreshMenu() {
        UI.initialise();
        UI.addButton("Quit", UI::quit);
        UI.setDivider(1);
    }
    /**
     *  Sets the name, author and genre of each book
     *  and then creates a new book object to add into bookMap.
     */
    public void addBook() {
        refreshMenu();
        boolean valid = false;
        while (!valid) {
            setBookName(UI.askString("Enter the title of the book: ").strip());
            if (bookName.length() == 0) {
                UI.println("Please enter a book name");
            } else if (bookMap.containsKey(bookName.toLowerCase())) {
                UI.println("Please enter a unique book name.");
            } else {
                valid = true;
            }
        }
        valid = false;
        while (!valid) {
            setBookAuthor(UI.askString("Who wrote this book?: ").strip());
            if (bookAuthor.length() == 0) {
                UI.println("Please enter a book author");
            } else {
                valid = true;
            }
        }
        valid = false;
        while (!valid) {
            setBookGenre(UI.askString("What is the primary genre of this book: ").strip());
            if (bookGenre.length() == 0) {
                UI.println("Please enter a book genre");
            } else {
                valid = true;
            }
        }
        Book book = new Book(bookName, titleCase(bookAuthor),
                            titleCase(bookGenre));
        bookMap.put(bookName.toLowerCase(), book);
        UI.clearText();UI.println(bookName + " has been sucessfully added!");
        bookTotal += 1;
        mainMenu();
        UI.println(bookName + " has been sucessfully added!");  
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
     */
    public void startRateBook() {
        refreshMenu();
        boolean check = false;
        while (!check) {
            searchString = UI.askString(
            "Enter the title of the book to rate: ").toLowerCase().strip();
            if (checkBook(searchString)) {
                setBookName(bookMap.get(searchString).getName());
                setBookAuthor(bookMap.get(searchString).getAuthor());
                setBookGenre(bookMap.get(searchString).getGenre());
                check = true;
            } else {
                UI.println("That book isn't in the database.");
            }
        }
        UI.setMouseListener(this::mouseRate);
        rated = false;
        UI.setDivider(0);
        like.drawButton();
        dislike.drawButton();
        UI.setFontSize(FONT);
        UI.setColor(Color.black);
        UI.drawString(("Did you like '" + bookName + "'?"), POSITION, POSITION);
    }
    /**
     *  Ends the process of rating books.
     *  Based on param will either add bookname to likedBooks
     *  or dislikedBooks and delete the entry in the opposite
     *  ArrayList if possible.
     *
     *  @param  LIKED  boolean storing whether book was liked,
     *  true = yes & viceversa
     */
    public void endRateBook(final boolean LIKED) {
        UI.clearGraphics();
        refreshMenu();
        if (LIKED) {
            if (!likedBooks.contains(searchString)) {
                likedBooks.add(searchString);
                //unratedBooks.remove(searchString);
            }
            if (dislikedBooks.contains(searchString)) {
                    dislikedBooks.remove(searchString);
                }
        }
        if (!LIKED) {
            if (!dislikedBooks.contains(searchString)) {
                dislikedBooks.add(searchString);
                //unratedBooks.remove(searchString);
            }
            if (likedBooks.contains(searchString)) {
                    likedBooks.remove(searchString);
                }
        }
        UI.println(likedBooks);
        UI.println(dislikedBooks);
        mainMenu();
        UI.println(bookName + " has been sucessfully rated");
    }
    /**
     *  Deletes a book from bookMap.
     *  Will ask the user for a book to delete which is
     *  then ran through checkBook(). If returned true
     *  will delete the book from bookMap and reduce bookTotal by 1.
     */
    public void deleteBook() {
        refreshMenu();
        boolean check = false;
        while (!check) {
            searchString = UI.askString(
                    "Enter the title of the book to delete: ").toLowerCase().strip();
            if (checkBook(searchString)) {
                check = true;
                bookMap.remove(searchString);
                likedBooks.remove(searchString);
                dislikedBooks.remove(searchString);
                bookTotal -= 1;
            } else {
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
           UI.println("Please positively rate a book first!");
        } else {
            for (String likedBook : likedBooks) {
               for (Map.Entry<String, Book> entry : bookMap.entrySet()) {
                   String book = entry.getKey();
                   if (!dislikedBooks.contains(book)
                    && !likedBooks.contains(book)) {
                       if (bookMap.get(likedBook).getAuthor().equals(
                            bookMap.get(book).getAuthor())) {
                           bookRecommend.add(bookMap.get(likedBook).getName());
                           bookRecommend.add(bookMap.get(book).getName());
                           bookRecommend.add("author");
                           recommendedBooks.add(bookRecommend);
                           
                           bookRecommend = new ArrayList<String>();
                        }
                        if (bookMap.get(likedBook).getGenre().equals(
                            bookMap.get(book).getGenre())) {
                           bookRecommend.add(bookMap.get(likedBook).getName());
                           bookRecommend.add(bookMap.get(book).getName());
                           bookRecommend.add("genre");
                           recommendedBooks.add(bookRecommend);
                           
                           bookRecommend = new ArrayList<String>();
                        }
                    }
                }
            }
           if (recommendedBooks.size() > 0) {
           int randNum = (int) (Math.random()
                  * (double) (recommendedBooks.size()));
           UI.println(("Because you liked "
                        + recommendedBooks.get(randNum).get(0)
                        + ", you might enjoy "
                        + recommendedBooks.get(randNum).get(1)
                        + " as they have the same "
                        + recommendedBooks.get(randNum).get(2)
                        + "."));
            } else {
                UI.println("We cannot recommend anything as there are "
                 + "no unrated books that match with liked books.");
            }
        }
    }
    /**
     *  Converts a string into Title Case.
     *  Credit to https://www.baeldung.com/java-string-title-case
     *
     *  @param TEXT text to be converted
     *  @return  text in TitleCase
     */
    public String titleCase(final String TEXT) {
        if (TEXT == null || TEXT.isEmpty()) {
            return TEXT;
        }
        StringBuilder converted = new StringBuilder();
        boolean convertNext = true;
        for (char ch : TEXT.toCharArray()) {
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
     *  Searches a book from bookMap.
     *
     */
    public void searchBook() {
        refreshMenu();
        boolean check = false;
        while (!check) {
            searchString = UI.askString(
            "Enter the title of the book to search: ").toLowerCase().strip();
            if (checkBook(searchString)) {
                check = true;
            } else {
                UI.println("That book isn't in the database.");
            }
        }
        setBookName(bookMap.get(searchString).getName());
        setBookAuthor(bookMap.get(searchString).getAuthor());
        setBookGenre(bookMap.get(searchString).getGenre());
        mainMenu();
        UI.println((
        bookName + " by " + bookAuthor + " is a " + bookGenre + " book."));
        if (likedBooks.contains(searchString)) {
            UI.println("You liked this book.");
        }
        if (dislikedBooks.contains(searchString)) {
            UI.println("You disliked this book.");
        }
    }
    /**
     *  Checks to see if book exists.
     *  @param  SEARCH_TERM  searchTerm to be checked
     *  @return  true if bookMap does contain SEARCHTERM
     */
    public boolean checkBook(final String SEARCH_TERM) {
        return bookMap.containsKey(SEARCH_TERM.toLowerCase());
    }
     /**
     *  Sets bookAuthor.
     *  @param AUTHOR Name of author
     */
    public void setBookAuthor(final String AUTHOR) {
        this.bookAuthor = AUTHOR;
    }
    /**
     *  Sets bookName.
     *  @param NAME Name of book
     */
    public void setBookName(final String NAME) {
        this.bookName = NAME;
    }
    /**
     *  Set bookGenre.
     *  @param  GENRE  Genre of book, parse in a single genre only
     */
    public void setBookGenre(final String GENRE) {
        this.bookGenre = GENRE;
    }
     /**
     *  Mouse manager used in rating books.
     *
     *  @param  ACTION  What action mouse has performed, i.e clicked, released
     *  @param  X  x-value of mouse
     *  @param  Y  y-value of mouse
     */
    public void mouseRate(final String ACTION, final double X, final  double Y) {
        if (ACTION.equals("clicked") && !rated) {
            if (like.onButton(X, Y)) {
                endRateBook(true);
                rated = true;
            }
            if (dislike.onButton(X, Y)) {
                endRateBook(false);
                rated = true;
            }
        }
    }
}
