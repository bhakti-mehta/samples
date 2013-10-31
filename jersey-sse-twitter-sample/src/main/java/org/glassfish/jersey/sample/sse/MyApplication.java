package org.glassfish.jersey.sample.sse;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.media.sse.SseFeature;

/**
 *  This is an Application subclass which registers
 *  the <code> ServerSentEventsResource.class</code>
 *  and the <code>OutboundEventWriter.class</code>
 * @author Bhakti Mehta
 */
public class MyApplication extends Application {

    Set<Class<?>> classes = new HashSet<Class<?>>() {
        { add(ServerSentEventsResource.class);
            add(SseFeature.class);
        }};

    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}
