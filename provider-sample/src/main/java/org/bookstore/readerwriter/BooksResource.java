package org.bookstore.readerwriter;
/**
 * This is a basic  sample with MessageBodyReaders and MessageBodyWriters
 * Lets begin with a simple BooksResource
 * @author Bhakti Mehta
 */
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("books")
public class BooksResource {
        @POST
    	@Consumes(MediaType.APPLICATION_XML)
        @Produces(MediaType.APPLICATION_XML)
    	public Response addBook(Book book) {
    		return Response.ok(book).type(MediaType.APPLICATION_XML_TYPE).build();
    	}
}