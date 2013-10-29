package org.coffeeshop;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * A simple JAX-RS resource which demonstrates Bean Validation
 * @author Bhakti Mehta
 *
 */
@Path("v1/coffees")
public class CoffeesResource {
    @Context
    javax.ws.rs.core.UriInfo uriInfo;

    @GET
    @Path("{order}")
    @Produces(MediaType.APPLICATION_XML)
    @NotNull(message="Coffee does not exist for the order id requested")
    public Coffee getCoffee(  @PathParam("order") int order) {
        return CoffeeService.getCoffee(order);

    }

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    @ValidateOnExecution
    public Response addCoffee(@Valid Coffee coffee) {
        int order = CoffeeService.addCoffee(coffee);
        return Response.created(uriInfo.getAbsolutePath())
        .entity("<order>" + order + "</order>").build();
    }
}