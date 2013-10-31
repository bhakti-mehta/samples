This sample is for the end to end demo

1. Twitter uses the v1.1 API and uses OAuth to send authorized requests to Twitter REST API

2. To run this demo you will need to have a twitter account and create application based on information
specified here
https://dev.twitter.com/docs/auth/oauth


3. Install GlassFish 4.0
4. Start GlassFish 4.0 by going to glassfish4.0/glassfish4/bin
Run asadmin start-domain domain1

5. Update src/main/resources/org/glassfish/jersey/sample/sse/twitter4j.properties to add the
oauth.consumerKey=
oauth.consumerSecret=
oauth.accessToken=
oauth.accessTokenSecret=

6. Build the war by running mvn install
7. Deploy the war by calling asadmin deploy target/jersey-sse-twitter-sample.war
8. Go to to http://localhost:8080/jersey-sse-twitter-sample/TestClient
You will see the tweets as shown in the image