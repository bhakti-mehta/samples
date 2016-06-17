package com.bhakti.kinesis.config;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;

/*
 * The CredentialsProvider will return [default]
 * credential profile by reading from the credentials file located at
 * (~/.aws/credentials).
 */

public class CredentialsProvider implements AWSCredentialsProvider {
    public AWSCredentials getCredentials() {

        AWSCredentials credentials = null;
        try {
            credentials = new DefaultAWSCredentialsProviderChain().getCredentials();

        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                            "Please make sure that your credentials file is at the correct " +
                            "location (~/.aws/credentials), and is in valid format.",
                    e);
        }
        return credentials;
    }

    public void refresh() {

    }

}
