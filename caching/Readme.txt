
This is a very basic sample which shows caching

Download Glassfish 4.0

Start Glassfish
cd glassfish4/glassfish/bin
./asadmin start-domain

deploy caching.war
./asadmin deploy <folder of sample>/target/caching.war

curl -i http://localhost:8080/caching/v1/coffees/1

/You will see the headers like this

HTTP/1.1 200 OK
X-Powered-By: Servlet/3.1 JSP/2.3 (GlassFish Server Open Source Edition  4.0  Java/Oracle Corporation/1.7)
Server: GlassFish Server Open Source Edition  4.0
Cache-Control: private, no-transform, max-age=3600
Content-Type: application/json
Date: Thu, 11 Sep 2014 16:07:32 GMT
Content-Length: 69

{"name":"Mocha","order":1,"price":3.5,"size":"Small","type":"Brewed"}

Another request with ETag in response
curl -i http://localhost:8080/caching/v1/coffees/etag/1

HTTP/1.1 200 OK
X-Powered-By: Servlet/3.1 JSP/2.3 (GlassFish Server Open Source Edition  4.0  Java/Oracle Corporation/1.7)
Server: GlassFish Server Open Source Edition  4.0
Cache-Control: private, no-transform, max-age=3600
ETag: "123456789"
Content-Type: application/json
Date: Thu, 11 Sep 2014 16:07:05 GMT
Content-Length: 69

{"name":"Mocha","order":1,"price":3.5,"size":"Small","type":"Brewed"}

