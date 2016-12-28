package com.marc.rest.geo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.model.*;
import com.marc.rest.internal.OwnerLocation;
import com.marc.rest.sensitive.SensitiveKeys;
import com.marc.rest.weather.WeatherConnector;
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

import static com.marc.rest.sensitive.SensitiveKeys.googleApiKey;

/**
 * Created by marc on 16.11.16.
 * Thanks to http://stackoverflow.com/questions/2296377/how-to-get-city-name-from-latitude-and-longitude-coordinates-in-google-maps
 */
public class GeoConnector {

    public long getDistance(OwnLatLng latLng, OwnLatLng dest, TravelMode mode) {
        GeoApiContext context = new GeoApiContext().setApiKey(googleApiKey);
        LatLng origin = latLng.convertToGoogleLatLng();
        LatLng destination = dest.convertToGoogleLatLng();
        try {
            DistanceMatrix matrix = DistanceMatrixApi.newRequest(context).
                    origins(origin).
                    destinations(destination).
                    mode(mode).
                    units(Unit.IMPERIAL).
                    await();
            for (DistanceMatrixRow row : matrix.rows) {
                for (DistanceMatrixElement element : row.elements) {
                    if (element != null && element.duration != null) {
                        return element.duration.inSeconds;
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Long.MIN_VALUE;

    }

    public long getDistance(String lat, String lon, String mode) {
        OwnerLocation homeLocation = OwnerLocation.getInstance();
        OwnLatLng actualLocation;
        TravelMode travelMode;
        actualLocation = new OwnLatLng(Double.parseDouble(lat), Double.parseDouble(lon));
        travelMode = TravelMode.valueOf(mode.toUpperCase());
        return getDistance(homeLocation.getLatLon(), actualLocation, travelMode);
    }



}
