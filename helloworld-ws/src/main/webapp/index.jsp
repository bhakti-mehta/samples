<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hello WebSocket</title>

        <script language="javascript" type="text/javascript">
            var wsUri = "ws://localhost:8080/helloworld-ws/book";
            var websocket = new WebSocket(wsUri);
            websocket.onopen = function(evt) { onOpen(evt) };
            websocket.onmessage = function(evt) { onMessage(evt) };
            websocket.onerror = function(evt) { onError(evt) };

            function init() {
                output = document.getElementById("output");
            }

            function addBook() {
                websocket.send(nameField.value);
                writeToScreen("Sending: " + nameField.value);
            }

            function onOpen(evt) {
                writeToScreen("Opening websocket connection " );
            }

            function onMessage(evt) {
                writeToScreen("Receiving event: " + evt.data);
            }

            function onError(evt) {
                writeToScreen('<span style="color: red;">ERROR:</span> ' + evt.data);
            }

            function writeToScreen(message) {
                var pre = document.createElement("p");
                pre.style.wordWrap = "break-word";
                pre.innerHTML = message;
                output.appendChild(pre);
            }

            window.addEventListener("load", init, false);
        </script>
    </head>
    <body>
        <h1>Book Collection</h1>

        <div style="text-align: center;">
            <form action=""> 
                <input onclick="addBook()" value="Add Book" type="button">
                <input id="nameField" name="name" value="JavaEE development using GlassFish" type="text"><br>
            </form>
        </div>
        <div id="output"></div>
    </body>
</html>
