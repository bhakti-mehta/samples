This is a very basic sample which shows Beanvalidation and JAX-RS

Download Glassfish 4.0

Start Glassfish
cd glassfish4/glassfish/bin
./asadmin start-domain

deploy jax-rs-beanvalidation.war
./asadmin deploy <folder of sample>target/jax-rs-beanvalidation.war

You can try by using Advanced REST client or PostMan and sending data in the POST method
<coffee>
  <type>Expresso</type>
  <size>XL</size> 
  <name>Mocha1</name> 
  <price>3.50</price>
</coffee>

to the URL http://localhost:8080/jax-rs-bean-validation/v1/coffees

or send a GET request to http://localhost:8080/jax-rs-bean-validation/v1/coffees/<order> 
You will get a validation error incase there is no coffee with the order