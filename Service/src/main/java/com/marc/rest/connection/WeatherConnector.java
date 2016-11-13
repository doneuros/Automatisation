package com.marc.rest.connection;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marc.rest.Hallo;
import com.marc.rest.weather.Weather;
import org.apache.log4j.Logger;

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

/**
 * Created by marc on 13.11.16.
 */
public class WeatherConnector {

    final static Logger logger = Logger.getLogger(WeatherConnector.class);
    private static String urlStart = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String urlEnd = "&appid=738192430eb7328f8a5b9a7cbdab6b45";

    public Weather getWeather(String location){
        Weather weather = new Weather();
        logger.info("Requesting http://api.openweathermap.org/data/2.5/weather?q with Location:"+location);
        try {
            URL url = getBaseURI(location).toURL();
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
            logger.info("Error of request http://api.openweathermap.org/data/2.5/weather?q with Location:"+location+"\n"+e.getMessage());
            e.printStackTrace();
            return weather;
        } catch (IOException e) {
            logger.info("Error of request http://api.openweathermap.org/data/2.5/weather?q with Location:"+location+"\n"+e.getMessage());
            e.printStackTrace();
            return weather;
        }
        logger.info("End of request http://api.openweathermap.org/data/2.5/weather?q with Location:"+location);
        return weather;
    }

    private static URI getBaseURI(String town) {
        return UriBuilder.fromUri(urlStart + town + urlEnd).build();
    }
}
