package com.marc.rest;

import com.marc.rest.geo.OwnLatLng;
import com.marc.rest.internal.OwnerLocation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by marc on 07.12.16.
 */
@Path("/location")
public class LocationService {


    @GET
    @Path("/time/latitude/{latitude}/longitude/{longitude}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTimeNeededToDestincation(@PathParam("latitude")String lat, @PathParam("longitude")String lon){
        return null;
    }

    @PUT
    @Path("/latitude/{latitude}/longitude/{longitude}/city/{city}")
    @Consumes(MediaType.APPLICATION_JSON)
    private Response setOwnerLocatin(@PathParam("latitude")String lat, @PathParam("longitude")String lon, @PathParam("city")String city){
        setLatLng(lat, lon);
        setCity(city);
        return Response.status(200).build();
    }



    private void setLatLng(String lat, String lon){
        OwnerLocation.getInstance().setLatLon(new OwnLatLng(Float.parseFloat(lat), Float.parseFloat(lon)));
    }

    private void setCity(String city){
        OwnerLocation.getInstance().setCity(city);
    }
}
