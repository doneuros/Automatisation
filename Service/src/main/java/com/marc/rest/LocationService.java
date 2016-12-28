package com.marc.rest;

import com.google.maps.model.TravelMode;
import com.marc.rest.geo.GeoConnector;
import com.marc.rest.geo.OwnLatLng;
import com.marc.rest.internal.OwnerLocation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by marc on 07.12.16.
 */
@Path("/location")
public class LocationService {

    GeoConnector connector = new GeoConnector();

    private Response getCoordinateExceptionMapped() {
        return Response.status(204).entity("Keine Distanz für die Koordinaten gefunden").build();
    }


    private Response getNumberFormatExceptionMapped() {
        return Response.status(502).entity("Fehler umwandeln der Koordinaten").build();
    }

    private Response getIllegalArgumentExceptionMapped() {
        return Response.status(502).entity("Travel Mode nicht vorhanden").build();
    }

    @GET
    @Path("/time/latitude/{latitude}/longitude/{longitude}/travelmode/{mode}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getTimeNeededToDestincation(@PathParam("latitude") String lat, @PathParam("longitude") String lon, @PathParam("mode") String mode) {
        try {
            long dist = connector.getDistance(lat, lon, mode);
            if (dist == Long.MIN_VALUE) {
                return getCoordinateExceptionMapped();
            }
            return Response.status(200).entity(dist).build();
        } catch (NumberFormatException e) {
            return getNumberFormatExceptionMapped();
        } catch (IllegalArgumentException e) {
            return getIllegalArgumentExceptionMapped();
        }
    }

    @PUT
    @Path("/latitude/{latitude}/longitude/{longitude}/city/{city}")
    @Consumes(MediaType.APPLICATION_JSON)
    private Response setOwnerLocatin(@PathParam("latitude") String lat, @PathParam("longitude") String lon, @PathParam("city") String city) {
        setLatLng(lat, lon);
        setCity(city);
        return Response.status(200).build();
    }

    private void setLatLng(String lat, String lon) {
        OwnerLocation.getInstance().setLatLon(new OwnLatLng(Float.parseFloat(lat), Float.parseFloat(lon)));
    }

    private void setCity(String city) {
        OwnerLocation.getInstance().setCity(city);
    }
}
