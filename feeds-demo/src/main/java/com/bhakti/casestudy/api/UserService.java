package com.bhakti.casestudy.api;

import com.bhakti.casestudy.model.FeedInfo;
import com.yammer.metrics.annotation.Timed;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * This is the resource representing the <code>User</code> related details
 *
 * @author Bhakti Mehta
 */
@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
public interface UserService {

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/")
    public Response getUser(@PathParam("id")Integer id);

    @POST
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/tweets")
    public Response createTweet(@PathParam("id") Integer id, FeedInfo text) ;

    @GET
    @Timed
    @Path("/{id}/tweets")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTweets(@PathParam("id") Integer id ) ;

    @PUT
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}/following/{followingId}")
    public Response follow(@PathParam("id")Integer id,@PathParam("followingId")Integer followingId);





}
