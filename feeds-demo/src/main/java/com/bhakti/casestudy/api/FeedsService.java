package com.bhakti.casestudy.api;

import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This is the resource which will be used to display the feeds
 *
 * @author Bhakti Mehta
 */
@Path("/feeds")
@Produces(MediaType.APPLICATION_JSON)
public interface FeedsService {

    /*
     * This method will display a paginated list of feeds
     */
    @GET
    @Timed
    public Response showFeeds(@QueryParam("page") @DefaultValue("1") int page,
                              @QueryParam("limit") @DefaultValue("10") int limit);
}

