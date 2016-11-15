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
    @Test
    public void testCache(){
        internalCache = new InternalCache();
        assertFalse(internalCache.isCached("Berlin"));
        assertNull(internalCache.getWeather("Berlin"));
        internalCache.save(new Weather(20.0, "Cold"), "Berlin");
        assertTrue(internalCache.isCached("Berlin"));
        assertEquals(internalCache.getWeather("Berlin").getTemprature(), 20.0, 0.1);

        DateTimeUtils.setCurrentMillisOffset(dayMillis-trashhold);
        assertTrue(internalCache.isCached("Berlin"));
        System.out.println((new DateTime().getDayOfMonth()+""));
        DateTimeUtils.setCurrentMillisOffset(dayMillis+trashhold);
        System.out.println((new DateTime().getDayOfMonth()+""));
        assertFalse(internalCache.isCached("Berlin"));
    }

    @Test
    public void testCacheMonthJump(){
        internalCache = new InternalCache();
        DateTimeUtils.setCurrentMillisFixed(dayMillis*30);
        internalCache.save(new Weather(20.0, "Cold"), "Berlin");
        assertTrue(internalCache.isCached("Berlin"));
        //System.out.println((new DateTime().toString()+""));
        DateTimeUtils.setCurrentMillisFixed(dayMillis*31);
        //System.out.println((new DateTime().toString()+""));
        assertFalse(internalCache.isCached("Berlin"));
    }
}
