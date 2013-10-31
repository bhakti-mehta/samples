package org.sample.library;

import javax.ejb.Singleton;

import javax.ejb.Stateless;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.json.*;

import javax.json.stream.JsonGenerationException;

import javax.json.stream.JsonGenerator;

/**
 * This is an entity provider for a collection of Book objects
 * @author Bhakti Mehta
 */
@Stateless
@Provider
@Produces({MediaType.APPLICATION_JSON})
public class BookCollectionWriter implements MessageBodyWriter<List<Book>> {
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(List<Book> books, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(List<Book> books, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        Map<String, Object> configs;
        configs = new HashMap<String, Object>(1);
        configs.put(JsonGenerator.PRETTY_PRINTING, true);

        generator.writeStartArray();
        for (Book book: books) {
            generator.writeStartObject()
            .write("Name", book.getName())
            .write(" ISBN", book.getIsbn())
            .write("Author",book.getAuthor()) .writeEnd();

        }
        generator.writeEnd();
        generator.close();
        entityStream.write(writer.toString().getBytes());


    }
}
