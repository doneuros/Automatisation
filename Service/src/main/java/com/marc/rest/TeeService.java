package com.marc.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.RuntimeDelegate;

import com.google.maps.model.TravelMode;
import com.marc.rest.cache.InternalCache;
import com.marc.rest.geo.Location;
import com.marc.rest.geo.OwnLatLng;
import com.marc.rest.internal.OwnerLocation;
import com.marc.rest.weather.WeatherConnector;
import com.marc.rest.geo.GeoConnector;
import org.apache.log4j.Logger;

import com.marc.rest.weather.Weather;

import java.io.*;

@Path("/tee")
public class TeeService {

    final static Logger logger = Logger.getLogger(TeeService.class);
    private static InternalCache weatherCache = new InternalCache();
    private WeatherConnector weatherConnector = new WeatherConnector();
    private GeoConnector geoConnector = new GeoConnector();

    private Response getIOExceptionMapped() {
        return Response.status(502).entity("Fehler beim lesen der Wetter daten").build();
    }

    //TODO mapping from city to cordinates
//    @GET
//    @Path("/getWeather/{param}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getWeather(@PathParam("param") String msg) {
//        try {
//
//            return Response.status(200).entity(getWeatherInternal().toString()).build();
//        } catch (IOException e) {
//            return getIOExceptionMapped();
//        }
//    }


    @GET
    @Path("/getTemprature")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTemprature() {
        try {
            return Response.status(200).entity(getWeatherInternalOwnerLocation().getTemprature()).build();
        } catch (IOException e) {
            return getIOExceptionMapped();
        }
    }

    @PUT
    @Path("/postHomeLocation")
    @Consumes(MediaType.APPLICATION_JSON)
    @Deprecated
    public void changeHomeLocation(String location) {
        OwnerLocation.getInstance().setCity(location);
    }


    @GET
    @Path("/makeTee/latitude/{latitude}/longitude/{longitude}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response makeTee(@PathParam("latitude") String curLatitude, @PathParam("longitude") String curLongitude) {
        logger.info("makeTee started");
        //String msg = geoConnector.getLocation(curLatitude, curLongitude).getCity();
        logger.info("makeTee ended");
        try {
            OwnLatLng latLng = new OwnLatLng(Float.parseFloat(curLatitude), Float.parseFloat(curLongitude));
            Weather weather = getWeatherInternal(latLng);
            long distanceInSeconds = getDistance(curLatitude, curLongitude);

            try {


                ProcessBuilder pb = new ProcessBuilder("python","script.py");

                logger.info(pb.directory());
                pb.directory(new File("/home/pi/"));
                logger.info(pb.directory());
                Process p = pb.start();
                ProcessBuilder pb2 = new ProcessBuilder("runPython.sh");
                pb2.directory(new File("/home/pi/"));
                pb2.start();

            }catch(Exception e){
                e.printStackTrace();
            }

            return Response.status(200).entity(weather.toString()+" Distance:"+distanceInSeconds).build();
        } catch (IOException e) {
            return getIOExceptionMapped();
        }
    }

    private long getDistance(String curLatitude, String curLongitude) throws IOException{
        LocationService locationService = new LocationService();
        Response distanceResponse = locationService.getTimeNeededToDestincation(curLatitude, curLongitude, TravelMode.BICYCLING.toString());
        if(distanceResponse.getStatus()==200){
            return Long.parseLong(distanceResponse.getEntity().toString());
        } else {
            //TODO for Testing
            throw new IOException(distanceResponse.getEntity().toString());
        }
    }


    private Weather getWeatherInternalOwnerLocation() throws IOException {
        if (OwnerLocation.getInstance().getLatLon() == null) {
            throw new IOException("Owner Location not set");
        }
        return getWeatherInternal(OwnerLocation.getInstance().getLatLon());
    }


    private Weather getWeatherInternal(OwnLatLng latLng) throws IOException {
        Weather weather;
        if (weatherCache.isCached(latLng)) {
            logger.info("Location LoadedFrom Cache");
            weather = weatherCache.getWeather(latLng);
        } else {
            logger.info("Location LoadedFrom URL");
            weather = weatherConnector.getWeather(latLng);
        }
        return weather;
    }
}