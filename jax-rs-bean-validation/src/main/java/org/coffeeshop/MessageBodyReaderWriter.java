package org.coffeeshop;

import javax.ws.rs.Produces;
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
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * This is the main class for the implementation
 * of MessageBodyReader and MessageBodyWriter
 *
 * @author Bhakti Mehta
 */
@Provider
@Produces({MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN})
public class MessageBodyReaderWriter implements MessageBodyReader<Coffee>, MessageBodyWriter<Coffee> {
    public boolean isReadable(Class<?> aClass, java.lang.reflect.Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    public Coffee readFrom(Class<Coffee> coffeeClass, java.lang.reflect.Type type, Annotation[] annotations,
                           MediaType mediaType,
                           MultivaluedMap<String, String> stringStringMultivaluedMap,
                           InputStream inputStream) throws IOException, WebApplicationException {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Coffee.class.getPackage().getName());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            Coffee coffee = (Coffee) unmarshaller.unmarshal(inputStream);
            return coffee;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isWriteable(Class<?> aClass, java.lang.reflect.Type type, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    public long getSize(Coffee coffee, Class<?> aClass, java.lang.reflect.Type type, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    public void writeTo(Coffee coffee, Class<?> aClass, java.lang.reflect.Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> stringObjectMultivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        try {
            if (mediaType.equals(MediaType.APPLICATION_XML_TYPE)) {
                JAXBContext jaxbContext = JAXBContext.newInstance(Coffee.class.getPackage().getName());

                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.marshal(coffee, outputStream);
            } else {
                outputStream.write(coffee.toString().getBytes());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
}
