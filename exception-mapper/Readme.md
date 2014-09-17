This is a very basic sample which shows exception mapper and JAX-RS

Download Glassfish 4.0

Start Glassfish
cd glassfish4/glassfish/bin
./asadmin start-domain

Build the sample by running
mvn clean install
cd target
deploy exception-mapper.war
./asadmin deploy <folder of sample>/target/exception-mapper.war


curl http://localhost:8080/exception-mapper/v1/coffees/orders/1/
You will see response like this {"name":"Mocha","order":1,"size":"Small","type":"Brewed"}

curl http://localhost:8080/exception-mapper/v1/coffees/orders/100
Powered-By: Servlet/3.1 JSP/2.3 (GlassFish Server Open Source Edition  4.0  Java/Oracle Corporation/1.7)
Server: GlassFish Server Open Source Edition  4.0 
Content-Type: application/json
Date: Tue, 12 Aug 2014 00:08:18 GMT
Content-Length: 54

{"code":404,"message":"No coffee found for order 100"}


