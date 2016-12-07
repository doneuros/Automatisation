package com.marc.rest;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.marc.rest.cache.InternalCache;
import com.marc.rest.geo.OwnLatLng;
import com.marc.rest.internal.OwnerLocation;
import com.marc.rest.weather.WeatherConnector;
import com.marc.rest.geo.GeoConnector;
import org.apache.log4j.Logger;

import com.marc.rest.weather.Weather;

import java.io.IOException;

@Path("/tee")
public class TeeService {

    final static Logger logger = Logger.getLogger(TeeService.class);
    private static InternalCache weatherCache = new InternalCache();
    private WeatherConnector weatherConnector = new WeatherConnector();
    private GeoConnector geoConnector = new GeoConnector();



    private Response getIOExceptionMapped(){
        return Response.status(502).entity("Fehler beim lesen der Wetter daten").build();
    }

    @GET
    @Path("/getWeather/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeather(@PathParam("param") String msg) {
        try {
            return Response.status(200).entity(getWeatherInternal().toString()).build();
        } catch (IOException e) {
            return getIOExceptionMapped();
        }
    }



    @GET
    @Path("/getTemprature")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTemprature(){
        try {
            return Response.status(200).entity(getWeatherInternal().getTemprature()).build();
        } catch (IOException e) {
            return getIOExceptionMapped();
        }
    }

    @PUT
    @Path("/postHomeLocation")
    @Consumes(MediaType.APPLICATION_JSON)
    @Deprecated
    public void changeHomeLocation(String location){
        OwnerLocation.getInstance().setCity(location);
    }


    @GET
    @Path("/makeTee/latitude/{latitude}/longitude/{longitude}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response makeTee(@PathParam("latitude")String curLatitude, @PathParam("longitude")String curLongitude){
        logger.info("makeTee started");
        //String msg = geoConnector.getLocation(curLatitude, curLongitude).getCity();
        logger.info("makeTee ended");
        try {
            OwnLatLng latLng = new OwnLatLng(Float.parseFloat(curLatitude), Float.parseFloat(curLongitude));
            return Response.status(200).entity(getWeatherInternal(latLng).toString()).build();
        } catch (IOException e) {
            return getIOExceptionMapped();
        }
    }


    private Weather getWeatherInternal() throws IOException{
        return getWeatherInternal(OwnerLocation.getInstance().getLatLon());
    }


    private Weather getWeatherInternal(OwnLatLng latLng) throws IOException {
        Weather weather;
        if(weatherCache.isCached(latLng)){
            logger.info("Location LoadedFrom Cache");
            weather = weatherCache.getWeather(latLng);
        } else {
            logger.info("Location LoadedFrom URL");
            weather = weatherConnector.getWeather(latLng);
        }
        return weather;
    }
}