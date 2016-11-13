package com.marc.rest;


import com.fasterxml.jackson.core.type.TypeReference;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import com.marc.rest.cache.InternalCache;
import com.marc.rest.connection.WeatherConnector;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marc.rest.weather.Weather;

@Path("/hello")
public class Hallo {

    final static Logger logger = Logger.getLogger(Hallo.class);
    private static InternalCache weatherCache = new InternalCache();
    private WeatherConnector weatherConnector = new WeatherConnector();
    private String homeLocation = "Karlsruhe";

    @GET
    @Path("test/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMsg(@PathParam("param") String msg) {
        String output = "Jersey say : " + msg;
        return Response.status(200).entity(output).build();
    }

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


    @POST
    @Path("/makeTee/location/{param}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void makeTee(@PathParam("param")String location){
        
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