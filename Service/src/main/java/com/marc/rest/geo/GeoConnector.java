package com.marc.rest.geo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marc.rest.weather.WeatherConnector;
import org.apache.log4j.Logger;

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
 * Created by marc on 16.11.16.
 * Thanks to http://stackoverflow.com/questions/2296377/how-to-get-city-name-from-latitude-and-longitude-coordinates-in-google-maps
 */
public class GeoConnector {

    final static Logger logger = Logger.getLogger(WeatherConnector.class);
    //Test Url Berlin: http://maps.googleapis.com/maps/api/geocode/json?latlng=52.520007,13.404954&sensor=true
    private static String urlStart = "http://maps.googleapis.com/maps/api/geocode/json?latlng=";
    private static String urlEnd = "&sensor=true";


    public Location getLocation(String curLatitude, String curLongitude){
        Location location = new Location();

        try {
            location.setLatitude(Double.parseDouble(curLatitude));
            location.setLongitude(Double.parseDouble(curLongitude));

        } catch (NumberFormatException e){
            logger.info("Coordinates are not a number, Latitude:"+curLatitude +" Longitude:"+curLongitude);
            //Fail fast
            throw e;
        }

        logger.info("Requesting http://maps.googleapis.com/maps/api/geocode/json?latlng=52.520007,13.404954"+curLatitude+","+curLongitude+"&sensor=true");
        try {
            URL url = getBaseURI(curLatitude, curLongitude).toURL();
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
            location.setCity(JSONParsingCity(map));
        } catch (MalformedURLException e) {
            logger.info("Error of request "+ urlEnd.toString()+"\n"+e.getMessage());
            e.printStackTrace();
            return location;
        } catch (IOException e) {
            logger.info("Error of request "+urlEnd.toString()+"\n"+e.getMessage());
            e.printStackTrace();
            return location;
        }
        logger.info("End of request "+urlEnd.toString());
        return location;
    }

    protected String JSONParsingCity(Map<String, Object> map) {
        ArrayList<Object> results = (ArrayList<Object>) map.get("results");
        //Should be simplified and generalized because of complexity and reuse
        Map<String, Object> addressComponents0Array = (Map<String, Object>)results.get(0);
        ArrayList<Object> addressComponents = (ArrayList<Object>)addressComponents0Array.get("address_components");
        Map<String, Object> addressComponents3 = (Map<String, Object>)addressComponents.get(3);
        String city = addressComponents3.get("long_name").toString();
        return city;
    }

    private static URI getBaseURI(String curLatitude, String curLongitude) {
        return UriBuilder.fromUri(urlStart + curLatitude + "," + curLongitude+ urlEnd).build();
    }

}
