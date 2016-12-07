package com.marc.rest.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marc.rest.geo.GeoConnector;
import org.junit.Test;

import javax.ws.rs.core.UriBuilder;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by marc on 22.10.16.
 */
public class TestJSONOwnSimpleParser {


    private static String urlStart = "http://api.openweathermap.org/data/2.5/weather?q=";
    private static String urlEnd = "&appid=738192430eb7328f8a5b9a7cbdab6b45";

    /**
     * Not a real Unit Test Because it uses Network but to learn about the openweathermap api very usefull
     * @throws Exception
     */
    //@Test
    public void lookForJSONFormatting() throws Exception {
        System.out.println("Start Network Test");
        URL url = getBaseURI("Karlsruhe").toURL();
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
        System.out.println(map.toString());
        assertEquals(map.toString(),getHttpRequestAsString());
        System.out.println("END");
    }

    @Test
    public void testFind() throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(getHttpRequestWithDoubleQuote(), new TypeReference<Map<String,Object>>(){});
        System.out.println(map.toString());
        System.out.println(map.keySet().toString());
        System.out.println((((Map<String, Object>)((ArrayList<Object>)map.get("weather")).get(0))).get("description"));
        System.out.println(((Map<String, Object>)map.get("main")).get("temp"));


    }

    @Test
    public void testWeatherConnector() throws Exception {
        GeoConnector geoConnector = new GeoConnector();
        //geoConnector.getLocation(51.45212424934954+"", 12.135772705078125+"");
    }

    private static URI getBaseURI(String town) {
        return UriBuilder.fromUri(urlStart + town + urlEnd).build();
    }

    private static String getHttpRequestAsString(){
        return "" +
                "{coord={lon=8.39, lat=49}, " +
                "weather=[{id=802, main=Clouds, description=scattered clouds, icon=03d}], " +
                "base=stations, main={temp=283.654, pressure=1007.15, " +
                "humidity=91, temp_min=283.654, temp_max=283.654, " +
                "sea_level=1027.65, grnd_level=1007.15}, " +
                "wind={speed=2.96, deg=264.501}, " +
                "clouds={all=48}, dt=1477149846, " +
                "sys={message=0.0022, country=DE, " +
                "sunrise=1477115957, " +
                "sunset=1477153286}, " +
                "id=2892794, " +
                "name=Karlsruhe, " +
                "cod=200}";
    }

    private static String getHttpRequestWithDoubleQuote(){
        return "{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"weather\":[{\"id\":804,\"main\":\"" +
                "Clouds\",\"description\":\"overcast clouds\",\"icon\":\"04d\"}],\"base\":\"" +
                "stations\",\"main\":{\"temp\":286.029,\"pressure\":1021.09,\"humidity\":71,\"" +
                "temp_min\":286.029,\"temp_max\":286.029,\"sea_level\":1028.62,\"grnd_level\"" +
                ":1021.09},\"wind\":{\"speed\":3.16,\"deg\":36.5009},\"clouds\":{\"all\":88},\"" +
                "dt\":1477150799,\"sys\":{\"message\":0.0728,\"country\":\"GB\",\"sunrise\":" +
                "1477118294,\"sunset\":1477155034},\"id\":2643743,\"name\":\"London\",\"cod\":200}";
    }
}
