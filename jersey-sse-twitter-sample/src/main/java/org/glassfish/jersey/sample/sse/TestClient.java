package org.glassfish.jersey.sample.sse;


import javax.json.*;
import org.glassfish.jersey.media.sse.EventSource;
import org.glassfish.jersey.media.sse.InboundEvent;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A test client to use Jersey 2.0 EventSource client apis for ServerSentEvents
 *
 * @author Bhakti Mehta
 */
@WebServlet(name = "TestClient", urlPatterns = {"/TestClient"} ,asyncSupported=true)
public class TestClient extends HttpServlet {

    private final static String TARGET_URI = "http://localhost:8080/jersey-sse-twitter-sample/twittersse";
    private ExecutorService executorService;

    @Override
    public void init() throws ServletException {
        executorService = Executors.newFixedThreadPool(3);

    }

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {

            final AsyncContext asyncContext = request.startAsync();
            asyncContext.setTimeout(600000);
            asyncContext.addListener(new AsyncListener() {

                @Override
                public void onComplete(AsyncEvent event) throws IOException {
                }

                @Override
                public void onTimeout(AsyncEvent event) throws IOException {
                    System.out.println("Timeout" + event.toString());
                }

                @Override
                public void onError(AsyncEvent event) throws IOException {
                    System.out.println("Error" + event.toString());
                }

                @Override
                public void onStartAsync(AsyncEvent event) throws IOException {
                }
            });


            Thread t = new Thread(new AsyncRequestProcessor(asyncContext));
            t.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * This class will create the EventSource
     * and when the SSE are received will print the data
     * from the Inbound events
     */
    class AsyncRequestProcessor implements Runnable {

        private final AsyncContext context;

        public AsyncRequestProcessor(AsyncContext context) {
            this.context = context;
        }

        @Override
        public void run() {
            Client client = ClientBuilder.newClient();
            //client.configuration().register(OutboundEventWriter.class);
            context.getResponse().setContentType("text/html");
            final javax.ws.rs.client.WebTarget webTarget;
            try {
                final PrintWriter out = context.getResponse().getWriter();
                webTarget = client.target(new URI(TARGET_URI));
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Glassfish SSE TestClient</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>");
                out.println("Glassfish tweets");
                out.println("</h1>");

                EventSource eventSource = new EventSource(webTarget) {
                    @Override
                    public void onEvent(InboundEvent inboundEvent) {
                        try {
                            //get the JSON data and parse it
                            JsonReader jsonReader = Json.createReader (new ByteArrayInputStream(inboundEvent.getData(String.class,
                                    MediaType.APPLICATION_JSON_TYPE).getBytes()));

                            JsonArray jsonArray = jsonReader.readArray();
                            for (int i = 0; i <jsonArray.size(); i++) {
                                JsonObject o = ((JsonObject)jsonArray.getJsonObject(i)) ;
                                out.println( o.get("text"));
                                out.println("<br>");
                                out.println("Created at " + o.get("created_at"));
                                out.println("<br>");

                            }
                            out.println("</p>");
                            out.flush();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}