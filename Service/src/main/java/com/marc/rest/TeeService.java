package com.marc.rest;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.marc.rest.cache.InternalCache;
import com.marc.rest.connection.WeatherConnector;
import com.marc.rest.geo.GeoConnector;
import org.apache.log4j.Logger;

import com.marc.rest.weather.Weather;

@Path("/tee")
public class TeeService {

    final static Logger logger = Logger.getLogger(TeeService.class);
    private static InternalCache weatherCache = new InternalCache();
    private WeatherConnector weatherConnector = new WeatherConnector();
    private GeoConnector geoConnector = new GeoConnector();
    private String homeLocation = "Karlsruhe";



    @GET
    @Path("/getWeather/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeather(@PathParam("param") String msg) {
        return Response.status(200).entity(weatherResponse(getWeatherInternal(msg))).build();
    }

    private String weatherResponse(Weather weather) {
        return "Momentan entspricht das Wetter: " + weather.getDescription() + "\n"
                + " es hat " + weather.getTempratureInCalcius() + " Celcius";
    }

    @GET
    @Path("/getTemprature")
    @Produces(MediaType.APPLICATION_JSON)
    public double getTemprature(){
        return getWeatherInternal(homeLocation).getTemprature();
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
        return Response.status(200).entity(weatherResponse(getWeatherInternal(msg))).build();
    }

    private Weather getWeatherInternal(String location){
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