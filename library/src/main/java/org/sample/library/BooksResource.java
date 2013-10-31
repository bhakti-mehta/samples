package org.sample.library;
/**
 * This is a basic  sample with MessageBodyReaders and MessageBodyWriters
 * Lets begin with a simple BooksResource
 * @author Bhakti Mehta
 */

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Path("books")
public class BooksResource {

    private static final int SLEEP_TIME_IN_MILLIS = 1000;
    private static final ExecutorService TASK_EXECUTOR = Executors.newCachedThreadPool();

    @EJB
    BookService bookService;

    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @GET
    public String getGreeting() {
        return "Hello from book resource";
    }



    @GET
    @Path("browse")
    public List<Book> browseCollection() {
        return bookService.getBooks();
    }


    @POST
    @Path("return/{name}")
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public String returnBook(@PathParam("name") String nameOfBook) {
        return "Successfully returned Book " + nameOfBook;
    }

    @DELETE
    @Path("checkout/{name}")
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    public Book checkoutBook(@PathParam("name") String nameOfBook) {
        return bookService.deleteBook(nameOfBook);

    }


    /**
     * Asynchronously reply to placing a book on hold after sleeping for some tme
     *
     */
    @POST
    @Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @Path("hold/{name}")
    public void asyncEcho(@PathParam("name") final String name,  @Suspended final AsyncResponse ar) {
        TASK_EXECUTOR.submit(new Runnable() {

            public void run() {
                try {
                    Thread.sleep(SLEEP_TIME_IN_MILLIS);
                } catch (InterruptedException ex) {
                    ar.cancel();
                }
                ar.resume("Placed a hold for " + name);
            }
        });
    }
}



