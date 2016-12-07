package com.marc.rest;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by marc on 16.11.16.
 */
public class TestTeeService extends JerseyTest {


    @Override
    protected Application configure() {
        return new ResourceConfig(TeeService.class);
    }


    @Test
    public void test() {
        //TODO for Advance Testing MOKITO WITH JERSEYTEST
        //when(connector.getWeather("Berlin")).thenReturn(new Weather(20.0,"Raining"));
        //final String hello = target("tee/getWeather/Berlin").request().get(String.class);
       //System.out.println(hello);
    }

}
