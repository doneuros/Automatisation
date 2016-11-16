package com.marc.rest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import static org.junit.Assert.*;
import javax.ws.rs.core.Application;

/**
 * Created by marc on 16.11.16.
 */
public class TestTestService extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(TestService.class);
    }


    @Test
    public void test() {
        final String hello = target("test/hello").request().get(String.class);
        assertEquals("Jersey say : hello", hello);
    }

    @Test
    public void testMsg() {
        final String hello = target("test/msg/hello").request().get(String.class);
        assertEquals("hello", hello);
    }
}
