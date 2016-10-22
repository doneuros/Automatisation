package com.marc.rest;


import com.fasterxml.jackson.core.type.TypeReference;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marc.rest.weather.Weather;

@Path("/hello")
public class Hallo {

    final static Logger logger = Logger.getLogger(Hallo.class);
    private static String urlStart = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String urlEnd = "&appid=738192430eb7328f8a5b9a7cbdab6b45";

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
        Weather weather = new Weather();
        try {
            URL url = getBaseURI(msg).toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            InputStream is = conn.getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> map = objectMapper.readValue(is, new TypeReference<Map<String, Object>>() {
            });
            logger.info(map.toString());
            weather.setTemprature(Double.parseDouble(((Map<String, Object>) map.get("main")).get("temp").toString()));
            weather.setDescription((((Map<String, Object>) ((ArrayList<Object>) map.get("weather")).get(0))).get("description").toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.status(200).entity(weatherResponse(weather)).build();
    }

    private static URI getBaseURI(String town) {
        return UriBuilder.fromUri(urlStart + town + urlEnd).build();
    }

    private String weatherResponse(Weather weather) {
        return "Momentan entspricht das Wetter: " + weather.getDescription() + "\n"
                + " es hat " + weather.getTempratureInCalcius() + " Celcius";
    }
}