package org.bookstore.readerwriter;

import javax.enterprise.inject.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * This is the main class for the implementation
 * of MessageBodyReader and MessageBodyWriter
 *
 * @author Bhakti Mehta
 */
@Provider
public class MessageBodyReaderWriter implements MessageBodyReader<Book>, MessageBodyWriter<Book> {

    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    public Book readFrom(Class<Book> bookClass, Type type, Annotation[] annotations,
                         MediaType mediaType,
                         MultivaluedMap<String, String> stringStringMultivaluedMap,
                         InputStream inputStream) throws IOException, WebApplicationException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Book.class.getPackage().getName());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Book book = (Book) unmarshaller.unmarshal(inputStream);
            return book;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    public long getSize(Book book, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    public void writeTo(Book book, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> stringObjectMultivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Book.class.getPackage().getName());

            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(book, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
