This is a very basic sample which shows Ratelimiting and JAX-RS

Download Glassfish 4.0

Start Glassfish
cd glassfish4/glassfish/bin
./asadmin start-domain

Build the sample by running
mvn clean install
cd target
deploy ratelimiting.war
./asadmin deploy <folder of sample>target/ratelimiting.war

Send 4 requests to curl -i http://localhost:8080/ratelimiting/v1/coffees/1
You will get a 429 error on the 4th request

