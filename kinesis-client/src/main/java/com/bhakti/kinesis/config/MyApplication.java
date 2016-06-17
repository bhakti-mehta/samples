package com.bhakti.kinesis.config;

import com.amazonaws.services.kinesis.AmazonKinesisClient;
import com.bhakti.kinesis.api.StreamResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;


public class MyApplication extends Application<MyConfiguration> {

    @Override
    public String getName() {
        return "myrecordservice";
    }


    @Override
    public void run(MyConfiguration configuration, Environment environment) throws Exception {
        CredentialsProvider credentialsProvider = new CredentialsProvider();
        AmazonKinesisClient amazonKinesisClient = new AmazonKinesisClient(credentialsProvider.getCredentials());
        environment.jersey().register(new StreamResource(configuration.getStreamName(), amazonKinesisClient));
    }

    public static void main(String[] args) throws Exception {
        new MyApplication().run(args);
    }
}
