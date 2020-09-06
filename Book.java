 /* Based on the VUW ecs100 template
 *
 */

import ecs100.*;
import java.util.*;
import java.io.*;

/**
 * Book class storing bookName, bookAuthor and bookGenre.
 *
 */
public class Book {
    /**
     * Strings containing book information.
     */
    private String bookGenre, bookAuthor, bookName;
    /**
     * Constructor for objects of class Book.
     *
     *  @param  NAME  name of book
     *  @param  AUTHOR  author of book
     *  @param  GENRE  genre of book, should be single genre.
     */
    public Book(final String NAME, final String AUTHOR, final String GENRE) {
        this.bookName = NAME;
        this.bookAuthor = AUTHOR;
        this.bookGenre = GENRE;
    }
    /**
     * returns name of book.
     * @return  name of book
     */
    public String getName() {
        return this.bookName;
    }
    /**
     *  returns author.
     *  @return author
     */
    public String getAuthor() {
        return this.bookAuthor;
    }
    /**
     *  returns genre.
     *  @return genre
     */
    public String getGenre() {
        return this.bookGenre;
    }
}

