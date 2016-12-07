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

    public void getDistance(){
        GeoApiContext context = new GeoApiContext().setApiKey(" ");

    }
}
