package org.coffeeshop;

import org.glassfish.jersey.server.ChunkedOutput;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.*;

/**
 *
 * A simple JAX-RS resource which demonstrates Streaming, Chunking
 * @author Bhakti Mehta
 *
 */
@Path("v1/coffees")
public class CoffeesResource {


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/orders/{id}")
    public Response streamExample(@PathParam("id") int id) {
        final Coffee coffee = CoffeeService.getCoffee(id);
        StreamingOutput stream = new StreamingOutput() {
            @Override
            public void write(OutputStream os) throws IOException,
                    WebApplicationException {
                Writer writer = new BufferedWriter(new OutputStreamWriter(os));
                writer.write(coffee.toString());
                writer.flush();
            }
        };
        return Response.ok(stream).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/orders/{id}/chunk")
    public ChunkedOutput<String> chunkExample(final @PathParam("id") int id) {
        final ChunkedOutput<String> output = new ChunkedOutput<String>(String.class);

        new Thread() {
            @Override
            public void run() {
                try {
                    output.write("test");
                    output.write("test");
                    output.write("test");
                } catch (IOException e) {
                   e.printStackTrace();
                } finally {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

        return output;

    }


}
