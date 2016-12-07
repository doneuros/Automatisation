package com.marc.rest.weather;

import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by marc on 07.12.16.
 */
@RunWith(MockitoJUnitRunner.class)
public class TestWeatherConnector {


    WeatherConnector connector = new WeatherConnector();

    @Mock
    OpenWeatherMap owm;

    @Mock
    CurrentWeather currentWeather;

    @Test
    public void test() {

    }

    /*   TODO not working
    @Test
    public void testGoodCase() throws IOException {
        connector.setOpenWeatherMap(owm);
        when(owm.currentWeatherByCoordinates(1,2)).thenReturn(currentWeather);
        when(owm.currentWeatherByCityName("Berlin")).thenReturn(currentWeather);
        when(currentWeather.getMainInstance().getTemperature()).thenReturn((float) 1);
        assertEquals(1, connector.getWeather("1","2").getTemprature(), 0.01);
        assertEquals(1, connector.getWeather("Berlin").getTemprature(), 0.01);
    }

    @Test(expected=IOException.class)
    public void testInvalidCase() throws IOException {
        connector.setOpenWeatherMap(owm);
        when(owm.currentWeatherByCoordinates(1,2)).thenReturn(currentWeather);
        when(currentWeather.isValid()).thenReturn(false);
        connector.getWeather("1","2");
    }  */


}
