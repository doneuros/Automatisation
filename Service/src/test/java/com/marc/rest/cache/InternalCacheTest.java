package com.marc.rest.cache;

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
    String latitude = "1";
    String longitude ="2";
    String location = "Berlin";

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
        assertFalse(internalCache.isCached(latitude,longitude));
        assertNull(internalCache.getWeather(latitude,longitude));
        internalCache.save(new Weather(true, 20), latitude,longitude);
        assertTrue(internalCache.isCached(latitude,longitude));
        assertEquals(internalCache.getWeather(latitude,longitude).getTemprature(), 20.0, 0.1);

        DateTimeUtils.setCurrentMillisOffset(dayMillis-trashhold);
        assertTrue(internalCache.isCached(latitude,longitude));
        System.out.println((new DateTime().getDayOfMonth()+""));
        DateTimeUtils.setCurrentMillisOffset(dayMillis+trashhold);
        System.out.println((new DateTime().getDayOfMonth()+""));
        assertFalse(internalCache.isCached(latitude,longitude));
    }

    @Test
    public void testCacheMonthJumpCoordiantes(){
        DateTimeUtils.setCurrentMillisOffset(0);
        internalCache = new InternalCache();
        DateTimeUtils.setCurrentMillisFixed(dayMillis*30);
        internalCache.save(new Weather(true, 20), latitude,longitude);
        assertTrue(internalCache.isCached(latitude,longitude));
        //System.out.println((new DateTime().toString()+""));
        DateTimeUtils.setCurrentMillisFixed(dayMillis*31);
        //System.out.println((new DateTime().toString()+""));
        assertFalse(internalCache.isCached(latitude,longitude));
        DateTimeUtils.setCurrentMillisFixed(0);
    }
}
