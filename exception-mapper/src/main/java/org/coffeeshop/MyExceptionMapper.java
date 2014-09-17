package org.coffeeshop;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * This is an implementation of the ExceptionMapper class
 * to convert a user defined Exception to a JAX-RS response
 * @author Bhakti Mehta
 */
@Provider
public class MyExceptionMapper implements ExceptionMapper<Exception> {

    public Response toResponse(Exception e) {
        ResourceError resourceError = new ResourceError();

        String error = "Service encountered an internal error";
        if (e instanceof CoffeeNotFoundException) {
            resourceError.setCode(Response.Status.NOT_FOUND.getStatusCode());
            resourceError.setMessage(e.getMessage());

            return Response.status(Response.Status.NOT_FOUND).entity(resourceError)
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .build();
        }
        return Response.status(503).entity(resourceError).type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
