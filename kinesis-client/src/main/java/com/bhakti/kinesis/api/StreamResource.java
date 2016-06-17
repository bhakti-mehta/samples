package com.bhakti.kinesis.api;


import com.amazonaws.services.kinesis.AmazonKinesisClient;
import com.amazonaws.services.kinesis.model.*;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A resource which will put records and get records from Kinesis streams
 * @author Bhakti Mehta
 */

@Path("/records")
@Produces(MediaType.APPLICATION_JSON)
public class StreamResource {

    private String streamName;

    private AmazonKinesisClient amazonKinesisClient;

    public StreamResource(String streamName, AmazonKinesisClient amazonKinesisClient) {
        this.streamName = streamName;
        this.amazonKinesisClient = amazonKinesisClient;
    }

    @POST
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createEvent(
            ) {
        PutRecordsRequest putRecordsRequest = new PutRecordsRequest();
        putRecordsRequest.setStreamName(streamName);
        ArrayList<PutRecordsRequestEntry> putRecordsRequestEntryArrayList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            PutRecordsRequestEntry putRecordsRequestEntry = new PutRecordsRequestEntry();
            String data = "foobar" +i;
            putRecordsRequestEntry.setData(ByteBuffer.wrap(data.
                    getBytes()));
            putRecordsRequestEntry.setPartitionKey(String.valueOf(i / 100));
            putRecordsRequestEntryArrayList.add(putRecordsRequestEntry);

        }
        putRecordsRequest.setRecords(putRecordsRequestEntryArrayList);
        PutRecordsResult putRecordsResult = amazonKinesisClient.putRecords(putRecordsRequest);
        return Response.ok().entity(putRecordsResult).build();
    }

    @GET
    public Response getEvents(@QueryParam("shardIterator")String shardIterator,
                              @QueryParam("shardId")String shardId) {
        if (shardId == null) {
            shardId = "shardId-000000000001";
        }
        if (shardIterator == null) {
            GetShardIteratorRequest getShardIteratorRequest = new GetShardIteratorRequest();
            getShardIteratorRequest.setShardId(shardId);
            getShardIteratorRequest.setStreamName(streamName);
            getShardIteratorRequest.setShardIteratorType(ShardIteratorType.LATEST);

            GetShardIteratorResult getShardIteratorResult = amazonKinesisClient.getShardIterator(getShardIteratorRequest);
            shardIterator =
                    getShardIteratorResult.getShardIterator();
        }
        GetRecordsRequest getRecordsRequest = new GetRecordsRequest();
        getRecordsRequest.setShardIterator(shardIterator);

        GetRecordsResult getRecordsResult = amazonKinesisClient.getRecords(getRecordsRequest);
        List<Record> records = getRecordsResult.getRecords();
        return Response.ok().entity(getRecordsResult).build();

    }


}
