package org.coffeeshop;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 *
 * A simple JAX-RS resource which demonstrates Bean Validation
 * @author Bhakti Mehta
 *
 */
@Path("v1/coffees")
public class CoffeesResource {

    @GET
    @Path("orders")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Coffee> getCoffeeList( @QueryParam("page")  @DefaultValue("1") int page,
                                       @QueryParam("limit") @DefaultValue("10") int limit ) {
        return CoffeeService.getCoffeeList( page, limit);

    }

}
