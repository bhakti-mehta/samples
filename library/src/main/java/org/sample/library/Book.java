package org.sample.library;

/**
 * A class representing a single book
 * @author Bhakti Mehta
 */
public class Book {


    private String name;
    private String author;
    private String isbn;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {

        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Book(String name, String isbn, String author) {
        this.name=name;
        this.isbn=isbn;
        this.author=author;
    }


    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Name: ").append(name).append(" ").append("ISBN: ").append(isbn)
                .append(" ").append("Author: ").append(author);

        return stringBuilder.toString();
    }

    public Book() {

    }
}
