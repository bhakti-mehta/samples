package org.sample.library;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Bhakti Mehta
 */


public class BookApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(BooksResource.class);
        classes.add(BookCollectionWriter.class);
        classes.add(BookWriter.class);
        return classes;
    }

}

