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
 * A simple JAX-RS resource which demonstrates Rate limiting
 * @author Bhakti Mehta
 *
 */
@Path("v1/coffees")
public class CoffeesResource {


    @GET
    @Path("{order}")
    @Produces(MediaType.APPLICATION_JSON)
    @NotNull(message="Coffee does not exist for the order id requested")
    public Coffee getCoffee(  @PathParam("order") int order) {
        return CoffeeService.getCoffee(order);

    }


}