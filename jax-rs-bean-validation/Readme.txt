This is a very basic sample which shows Beanvalidation and JAX-RS

Download Glassfish 4.0

Start Glassfish
cd glassfish4/glassfish/bin
./asadmin start-domain

deploy jax-rs-beanvalidation.war
./asadmin deploy <folder of sample>target/jax-rs-beanvalidation.war

To run go to browser and type
 http://localhost:8080/jax-rs-bean-validation/TestServlet
 
 You will see 
 "Here is the status of the response 500

There was 1 error when validating the request

The following validation error was thrown: Book does not exist for the isbn requested"