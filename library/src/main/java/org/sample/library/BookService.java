package org.sample.library;

/**
 * A Singleton EJB service which contains information of all the books
 * @author Bhakti Mehta
 */

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.HashMap;


import java.util.*;

/**
 * This class stores the books
 * @author Bhakti Mehta
 */
@Singleton
public class BookService {

    private static final HashMap<String,Book> books = new HashMap<String,Book>();

    public static void addBook(Book book) {
        books.put(book.getName(), book);
    }

    public static int getSize() {
           return  books.size();
    }

    public static Book deleteBook(String isbn) {
        return books.remove(isbn);
    }


    public static ArrayList<Book> getBooks() {
        System.out.println("In getBooks");
        return new ArrayList<Book>(books.values());
    }

    public BookService() {
        // initial content
        addBook( new Book("Java EE development using GlassFish Aplication Server","782345689","David Heffinger"));
        addBook( new Book("Java 7 JAX-WS Web Services","123456789","Deepak Vohra"));
        addBook( new Book("Netbeans IDE7 CookBook","2234555567","Rhawi Dantas"));
        addBook( new Book("Getting Started with RESTful WebServices","11233333","Bhakti Mehta, Masoud Kalali"));

    }


}

