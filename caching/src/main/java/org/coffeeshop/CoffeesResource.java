package org.coffeeshop;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * A simple JAX-RS resource which demonstrates Caching
 * @author Bhakti Mehta
 *
 */
@Path("v1/coffees")
public class CoffeesResource {


    @GET
    @Path("/etag/{order}")
    @Produces(MediaType.APPLICATION_JSON)
    @NotNull(message = "Coffee does not exist for the order id requested")
    public Response getCoffeeWithEtag(@PathParam("order") int order,
                                      @Context Request request
    ) {
        Coffee coffee = CoffeeService.getCoffee(order);
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(3600);
        cacheControl.setPrivate(true);


        EntityTag et = new EntityTag(
                "123456789");

        Response.ResponseBuilder responseBuilder  = request.evaluatePreconditions(et);
        if (responseBuilder != null) {
            responseBuilder.build();
        }
        responseBuilder = Response.ok(coffee);
        responseBuilder.cacheControl(cacheControl);

        return responseBuilder.tag(et).build();


    }

    @GET
    @Path("{order}")
    @Produces(MediaType.APPLICATION_JSON)
    @NotNull(message = "Coffee does not exist for the order id requested")
    public Response getCoffee(@PathParam("order") int order) {
        Coffee coffee = CoffeeService.getCoffee(order);
        CacheControl cacheControl = new CacheControl();
        cacheControl.setMaxAge(3600);
        cacheControl.setPrivate(true);
        Response.ResponseBuilder responseBuilder = Response.ok(coffee);
        responseBuilder.cacheControl(cacheControl);
        return responseBuilder.build();


    }




}