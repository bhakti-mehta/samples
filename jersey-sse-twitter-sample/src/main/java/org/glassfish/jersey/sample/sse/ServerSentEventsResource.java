package org.glassfish.jersey.sample.sse;

import java.io.IOException;
import javax.json.JsonArray;
import javax.ws.rs.Consumes;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseFeature;

/**
 * This is a resource which will produce the ServerSentEvents
 * @author Bhakti Mehta
 */
@Path("twittersse")
public class ServerSentEventsResource {

       static EventOutput eventChannel= new EventOutput();

       @GET
       @Produces(SseFeature.SERVER_SENT_EVENTS)
       public EventOutput getMessage() {
           return eventChannel;
       }


       @POST
       @Consumes(MediaType.APPLICATION_JSON)
       public void sendMessage(String message) throws IOException {
           System.out.println("Sending " + message);
           eventChannel.write(new OutboundEvent.Builder().name("custom-message").data(String.class, message).build());
       }
}
