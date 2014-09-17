package org.coffeeshop;

import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.stream.JsonGenerator;
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
import java.lang.reflect.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * This is an entity provider for a collection of coffee objects
 *
 * @author Bhakti Mehta
 */
@Stateless
@Provider
@Produces({MediaType.APPLICATION_JSON})
public class CoffeeCollectionWriter implements MessageBodyWriter<List<Coffee>> {
    @Override
    public boolean isWriteable(Class<?> type, java.lang.reflect.Type genericType, Annotation[] annotations, MediaType mediaType) {
        return true;
    }

    @Override
    public long getSize(List<Coffee> coffees, Class<?> type, java.lang.reflect.Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }

    @Override
    public void writeTo(List<Coffee> coffees, Class<?> type, java.lang.reflect.Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
        StringWriter writer = new StringWriter();
        JsonGenerator generator = Json.createGenerator(writer);
        Map<String, Object> configs;
        configs = new HashMap<String, Object>(1);
        configs.put(JsonGenerator.PRETTY_PRINTING, true);

        generator.writeStartObject().
                writeStartObject("metadata")
                .write("pageCount",coffees.size())
                .write("totalCount",CoffeeService.getSize())
                .writeStartArray("links")
                .writeStartObject().write("self",
                "/orders?page="+(CoffeeService.getNextPage()-1) +"&limit=10");
                generator.writeEnd();
                generator.writeStartObject().write("next","/orders?page="+CoffeeService.getNextPage() +"&limit=10")
        ;
        generator.writeEnd();
        generator.writeEnd();
        generator.writeEnd();
        generator.writeStartArray("coffees");
        for (Coffee coffee : coffees ) {
            generator.writeStartObject()
                    .write("Id", coffee.getId())
                    .write("Name", coffee.getName())
                    .write("Price", coffee.getPrice())
                    .write("Type", coffee.getType())
                    .write("Size", coffee.getSize()).writeEnd();

        }
        generator.writeEnd();
        generator.writeEnd();
        generator.close();
        entityStream.write(writer.toString().getBytes());


    }
}

