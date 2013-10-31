package org.sample.bookstore;

import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;

/**
 *  This is a basic ws endpoint which can add books using Websockets
 */
@ServerEndpoint(value="/book")
public class BookCollection {

    @OnMessage
    public String addBook(String name) {
        return "Added book " + name + "!";
    }

}
