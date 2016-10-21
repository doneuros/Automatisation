package com.marc.rest;


import com.fasterxml.jackson.core.type.TypeReference;
import com.marc.rest.weather.Weather;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marc.rest.weather.WeatherJSON;

@Path("/hello")
public class Hallo {

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
        WeatherJSON weather = new WeatherJSON();
        String output ="";
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
            Map<String, Object> map = objectMapper.readValue(is, new TypeReference<Map<String,Object>>(){});

            for (Object value:map.values()){
                output += value.toString()+ "\n";
                output += value.getClass().toString()+ "\n";
            }



            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return Response.status(200).entity(output).build();
            //return weather;

        }


    private void itsWrong() {
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Accept", "application/json");
//
//        if (conn.getResponseCode() != 200) {
//            throw new RuntimeException("Failed : HTTP error code : "
//                    + conn.getResponseCode());
//        }
//        BufferedReader br = new BufferedReader(new InputStreamReader(
//                (conn.getInputStream())));
//        output = br.readLine().split("\\}");
//
//
//        conn.disconnect();
        //        for (String first:output) {
//            String[] firstSpilt = first.split(",");
//            for (String second:firstSpilt) {
//                if(first.contains("description")){
//
//                } else if(first.contains("temp")){
//
//                }
//            }
//        }
    }

    private static URI getBaseURI(String town) {
        return UriBuilder.fromUri(urlStart + town + urlEnd).build();
    }


}