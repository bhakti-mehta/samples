package org.coffeeshop;

import org.glassfish.jersey.server.ChunkedOutput;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;

/**
 *
 * A simple JAX-RS resource which demonstrates custom exception handling
 * @author Bhakti Mehta
 *
 */
@Path("v1/coffees")
public class CoffeesResource {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/orders/{id}")
    public Response getCoffee(@PathParam("id") int id) {
        Coffee coffee =  CoffeeService.getCoffee(id);
        if (coffee == null)
            throw new CoffeeNotFoundException("No coffee found for order " + id);
        return Response.ok(coffee).type(MediaType.APPLICATION_JSON_TYPE).build();
    }





}
