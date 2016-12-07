package com.marc.rest;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.marc.rest.cache.InternalCache;
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
    private String homeLocation = "Karlsruhe";


    private Response getIOExceptionMapped(){
        return Response.status(502).entity("Fehler beim lesen der Wetter daten").build();
    }

    @GET
    @Path("/getWeather/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeather(@PathParam("param") String msg) {
        try {
            return Response.status(200).entity(getWeatherInternal(msg).toString()).build();
        } catch (IOException e) {
            return getIOExceptionMapped();
        }
    }



    @GET
    @Path("/getTemprature")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTemprature(){
        try {
            return Response.status(200).entity(getWeatherInternal(homeLocation).getTemprature()).build();
        } catch (IOException e) {
            return getIOExceptionMapped();
        }
    }

    @PUT
    @Path("/postHomeLocation")
    @Consumes(MediaType.APPLICATION_JSON)
    public void changeHomeLocation(String location){
        this.homeLocation = location;
    }


    @GET
    @Path("/makeTee/latitude/{latitude}/longitude/{longitude}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response makeTee(@PathParam("latitude")String curLatitude, @PathParam("longitude")String curLongitude){
        logger.info("makeTee started");
        String msg = geoConnector.getLocation(curLatitude, curLongitude).getCity();
        logger.info("makeTee ended");
        try {
            return Response.status(200).entity(getWeatherInternal(msg).toString()).build();
        } catch (IOException e) {
            return getIOExceptionMapped();
        }
    }





    private Weather getWeatherInternal(String location) throws IOException {
        Weather weather;
        if(weatherCache.isCached(location)){
            logger.info("Location LoadedFrom Cache");
            weather = weatherCache.getWeather(location);
        } else {
            logger.info("Location LoadedFrom URL");
            weather = weatherConnector.getWeather(location);
        }
        return weather;
    }
}