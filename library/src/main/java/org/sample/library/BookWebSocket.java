package org.sample.library;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

/**
 * This is a basic implementation of a WebSocket endpoint for book
 * @author Bhakti
 */
@ServerEndpoint(value="/app/websockets")
public class BookWebSocket {

    @OnMessage
    public String searchBook(String name) {
        return "This is from a Websocket endpoint. \n Found book " + name ;
    }

}