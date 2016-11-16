package com.marc.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by marc on 16.11.16.
 */
@Path("/test")
public class TestService {

    @GET
    @Path("{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsg(@PathParam("param") String msg) {
        String output = "Jersey say : " + msg;
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/msg/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTest(@PathParam("param") String msg) {
        String output = msg;
        return Response.status(200).entity(output).build();
    }
}
