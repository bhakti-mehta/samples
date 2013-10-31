package org.bookstore.readerwriter;

/**
 * This is an application which will register the JAX-RS resources
 * @author Bhakti Mehta
 */

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/")
public class HelloWorldApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(BooksResource.class);
        classes.add(MessageBodyReaderWriter.class);
        return classes;
    }

}

