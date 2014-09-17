This is a very basic sample which shows entities and JAX-RS

Download Glassfish 4.0

Start Glassfish
cd glassfish4/glassfish/bin
./asadmin start-domain

Build the sample by running
mvn clean install
cd target
deploy entities.war
./asadmin deploy <folder of sample>/target/entities.war


To try the StreamingOutput 
curl http://localhost:8080/entities/v1/coffees/orders/1/

To try the ChunkedOutput
curl http://localhost:8080/entities/v1/coffees/orders/1/chunk
