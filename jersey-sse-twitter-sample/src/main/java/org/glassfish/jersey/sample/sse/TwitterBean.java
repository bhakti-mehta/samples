/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2012 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package org.glassfish.jersey.sample.sse;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * This is a Schedule based Timer which will get tweets information from Twitter and send as Server
 * Sent Events
 * @author Bhakti Mehta
 */
@Stateless
@Named
public class TwitterBean {

   private final static String SEARCH_URL =
              "http://search.twitter.com/search.json?q=glassfish&rpp=5&include_entities=true" +
                      "&with_twitter_user_id=true&result_type=mixed";


    private final static String TARGET_URI= "http://localhost:8080/jersey-sse-twitter-sample";

    @Schedule(hour="*", minute="*", second="*/10")
    public void sendTweets() {

        Client client = ClientBuilder.newClient();
        try {
            WebTarget webTarget= client.target(new URI(TARGET_URI)) ;


            JsonArray statuses = null;
            try {
                 statuses = getFeedData();
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            System.out.println("Posting message");
            webTarget.path("twittersse").request().post(Entity.json(statuses));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    /**
     * Since twitter uses the v1.1 API we use twitter4j to get
     * the search results using OAuth
     * @return a JsonArray containing tweets
     * @throws TwitterException
     * @throws IOException
     */
    public JsonArray getFeedData() throws TwitterException, IOException {

        Properties prop = new Properties();
        // try {
        //load a properties file
        prop.load(this.getClass().getResourceAsStream("twitter4j.properties"));

        //get the property value and print it out
        String consumerKey = prop.getProperty("oauth.consumerKey");
        String consumerSecret= prop.getProperty("oauth.consumerSecret");
        String accessToken = prop.getProperty("oauth.accessToken");
        String accessTokenSecret = prop.getProperty("oauth.accessTokenSecret");
         ConfigurationBuilder cb = new ConfigurationBuilder();
         cb.setDebugEnabled(true)
                 .setOAuthConsumerKey(consumerKey)
                 .setOAuthConsumerSecret(consumerSecret)
                 .setOAuthAccessToken(accessToken)
                 .setOAuthAccessTokenSecret(accessTokenSecret);


        TwitterFactory tf = new TwitterFactory(cb.build());

        Twitter twitter = tf.getInstance();

        Query query = new Query("glassfish");
        QueryResult result = twitter.search(query);

        JsonArrayBuilder jsonArrayBuilder  = Json.createArrayBuilder();
        for (Status status : result.getTweets()) {
            jsonArrayBuilder
                    .add(Json.createObjectBuilder().
                            add("text", status.getText())
                            .add("created_at", status.getCreatedAt().toString()));

            System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText() + status.getCreatedAt());
        }

        return jsonArrayBuilder.build() ;
    }

}
