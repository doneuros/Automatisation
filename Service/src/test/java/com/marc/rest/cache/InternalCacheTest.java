package com.marc.rest.cache;

import com.marc.rest.geo.OwnLatLng;
import com.marc.rest.weather.Weather;
import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * Created by marc on 15.11.16.
 */
public class InternalCacheTest {

    private InternalCache internalCache;
    private static long dayMillis = 1000*60*60*24;
    private static  long trashhold = 85465;
    float latitude = 1;
    float longitude =2;
    String location = "Berlin";
    OwnLatLng latLng = new OwnLatLng(latitude,longitude);

    @Test
    public void testCache(){
        DateTimeUtils.setCurrentMillisOffset(0);
        internalCache = new InternalCache();
        assertFalse(internalCache.isCached(location));
        assertNull(internalCache.getWeather(location));
        internalCache.save(new Weather(true, 20), location);
        assertTrue(internalCache.isCached(location));
        assertEquals(internalCache.getWeather(location).getTemprature(), 20.0, 0.1);

        DateTimeUtils.setCurrentMillisOffset(dayMillis-trashhold);
        assertTrue(internalCache.isCached(location));
        System.out.println((new DateTime().getDayOfMonth()+""));
        DateTimeUtils.setCurrentMillisOffset(dayMillis+trashhold);
        System.out.println((new DateTime().getDayOfMonth()+""));
        assertFalse(internalCache.isCached(location));
    }

    @Test
    public void testCacheMonthJump(){
        DateTimeUtils.setCurrentMillisOffset(0);
        internalCache = new InternalCache();
        DateTimeUtils.setCurrentMillisFixed(dayMillis*30);
        internalCache.save(new Weather(true, 20), location);
        assertTrue(internalCache.isCached(location));
        //System.out.println((new DateTime().toString()+""));
        DateTimeUtils.setCurrentMillisFixed(dayMillis*31);
        //System.out.println((new DateTime().toString()+""));
        assertFalse(internalCache.isCached(location));
        DateTimeUtils.setCurrentMillisFixed(0);
    }

    @Test
    public void testCacheCoordiantes(){
        DateTimeUtils.setCurrentMillisOffset(0);

        internalCache = new InternalCache();
        assertFalse(internalCache.isCached(latLng));
        assertNull(internalCache.getWeather(latLng));
        internalCache.save(new Weather(true, 20), latLng);
        assertTrue(internalCache.isCached(latLng));
        assertEquals(internalCache.getWeather(latLng).getTemprature(), 20.0, 0.1);

        DateTimeUtils.setCurrentMillisOffset(dayMillis-trashhold);
        assertTrue(internalCache.isCached(latLng));
        System.out.println((new DateTime().getDayOfMonth()+""));
        DateTimeUtils.setCurrentMillisOffset(dayMillis+trashhold);
        System.out.println((new DateTime().getDayOfMonth()+""));
        assertFalse(internalCache.isCached(latLng));
    }

    @Test
    public void testCacheMonthJumpCoordiantes(){
        DateTimeUtils.setCurrentMillisOffset(0);
        internalCache = new InternalCache();
        DateTimeUtils.setCurrentMillisFixed(dayMillis*30);
        internalCache.save(new Weather(true, 20), latLng);
        assertTrue(internalCache.isCached(latLng));
        //System.out.println((new DateTime().toString()+""));
        DateTimeUtils.setCurrentMillisFixed(dayMillis*31);
        //System.out.println((new DateTime().toString()+""));
        assertFalse(internalCache.isCached(latLng));
        DateTimeUtils.setCurrentMillisFixed(0);
    }
}
