package com.marc.rest.geo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
/**
 * Created by marc on 16.11.16.
 */
public class TestGeoConnector {

    @Test
    public void testGeoConnector() throws Exception{
        //Germany 51.1657° N, 10.4515° E
        //Berlin 52.5200° N, 13.4050° E
        GeoConnector connector = new GeoConnector();
        OwnLatLng start = new OwnLatLng(49.0068901,8.403652700000066);
        OwnLatLng end = new OwnLatLng(52.5200, 13.4050);
        //TODO mock GoogleApi because it should be unit test not more
        //assertEquals(23431, connector.getDistance( start, end, TravelMode.DRIVING), 40000);


    }


}
