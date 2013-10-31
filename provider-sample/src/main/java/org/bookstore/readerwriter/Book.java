package org.bookstore.readerwriter;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *This is a basic jaxb class for a Book
 */
@XmlRootElement
public class Book {

    public String name;
    public String isbn;
    public String getName() {
        return name;
    }

    public String getIsbn() {
        return isbn;
    }

    public Book(String name, String isbn) {
        this.name=name;
        this.isbn=isbn;
    }


    //JAXB requires this
    public Book() {

    }
}
