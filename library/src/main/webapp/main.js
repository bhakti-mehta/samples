window.onload = writeMessage;
var wsUri = "ws://localhost:8080/libraryApp/app/websockets";
var websocket = new WebSocket(wsUri);
websocket.onopen = function(evt) { onOpen(evt) };
websocket.onmessage = function(evt) { onMessage(evt) };
websocket.onerror = function(evt) { onError(evt) };



function sendSearchWSRequest(book) {
    websocket.send(book);
    console.log("Searching for: " + book);
}

function onOpen(evt) {
    console.log("Opening websocket connection " );
}

function onMessage(evt) {

    document.getElementById("query").innerHTML="Sending websocket request to " + wsUri ;
    document.getElementById("output").innerHTML=evt.data;
}

function onError(evt) {
    console.log('<span style="color: red;">ERROR:</span> ' + evt.data);
}


function writeMessage() {
     document.getElementById("helloMessage").innerHTML = "My Library App";
}


function search_onclick()
{
    var book = document.getElementById("bookName");

    if ( book.value == ' ')  {

        alert('Please add book name');
    } else {
        sendSearchWSRequest(book.value);

    }
}

function hold_onclick()
{
    var book = document.getElementById("bookName");


    if ( book.value == ' ')  {

        alert('Please add book name');
    } else {
        sendHoldRequest(book.value);

    }
}

function return_onclick()
{
    var book = document.getElementById("bookName");


    if ( book.value == ' ')  {

        alert('Please add book name');
    } else {
        sendReturnRequest(book.value);

    }
}

function checkout_onclick()
{
    var book = document.getElementById("bookName");


    if ( book.value == ' ')  {

        alert('Please add book name');
    } else {
        sendCheckoutRequest(book.value);

    }
}

function browse_onclick()
{
    var book = document.getElementById("bookName");

   /* if ( book.value != ' ')  {

        document.getElementById("bookName").value =' ';
    } else {*/
        sendBrowseRequest();

    //}
}

function createRequest() {
  var result = null;
  if (window.XMLHttpRequest) {
    // FireFox, Safari, etc.
    result = new XMLHttpRequest();
    if (typeof result.overrideMimeType != 'undefined') {
      result.overrideMimeType('text/xml'); // Or anything else
    }
  }
  else if (window.ActiveXObject) {
    // MSIE
    result = new ActiveXObject("Microsoft.XMLHTTP");
  }
  else {
    // No known mechanism -- consider aborting the application
  }
  return result;
}

function sendSearchRequest() {
    var req = createRequest(); // defined above
;
    // Create the callback:
    req.onreadystatechange = function() {

      if (req.readyState == 4) {
          document.getElementById("query").innerHTML="GET app/books" ;
          document.getElementById("output").innerHTML=req.responseText;

      }
    }
        req.open("GET","app/books/" ,true);
        req.setRequestHeader ("Accept", "application/json");
        req.send(null);

}

function sendHoldRequest( book) {
    var req = createRequest(); // defined above
;
    // Create the callback:
    req.onreadystatechange = function() {

      if (req.readyState == 4) {
          document.getElementById("query").innerHTML="POST app/books/hold/" + encodeURI(book.trim());
          document.getElementById("output").innerHTML=req.responseText;

      }
    }
        req.open("POST","app/books/hold/" +book,true);
        req.send(null);

}

function sendReturnRequest( book) {
    var req = createRequest(); // defined above
;
    // Create the callback:
    req.onreadystatechange = function() {

      if (req.readyState == 4) {
          document.getElementById("query").innerHTML="POST app/books/return/" + encodeURI(book.trim());
          document.getElementById("output").innerHTML=req.responseText;

      }
    }
        req.open("POST","app/books/return/" +book,true);
        req.send(null);

}


function sendCheckoutRequest( book) {
    var req = createRequest(); // defined above
;
    // Create the callback:
    req.onreadystatechange = function() {

      if (req.readyState == 4) {
          document.getElementById("query").innerHTML="DELETE app/books/checkout/" + encodeURI(book.trim());
          document.getElementById("output").innerHTML=req.responseText;

      }
    }
        req.open("DELETE","app/books/checkout/" +book,true);
        req.send(null);

}


function sendBrowseRequest( ) {

    var req = createRequest(); // defined above
;
    // Create the callback:
    req.onreadystatechange = function() {

      if (req.readyState == 4) {
          document.getElementById("query").innerHTML="GET app/books/browse/" ;
          document.getElementById("output").innerHTML=req.responseText;

      }
    }
        req.open("GET","app/books/browse/" ,true);
        req.send(null);

}


