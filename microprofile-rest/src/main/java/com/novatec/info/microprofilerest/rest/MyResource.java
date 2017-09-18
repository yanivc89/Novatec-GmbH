package com.novatec.info.microprofilerest.rest;

import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.novatec.info.microprofilerest.entity.Vehicle;

@Path("/")
public class MyResource {
	
	private static final JsonBuilderFactory jsonFactory = Json.createBuilderFactory(null);
	
	@Inject
    LocalDateTime dateTime;
	
	@Inject
	Vehicle vehicle;
	
	@GET
    @Path("/initialdatetime")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTimeWithoutCDI() {
        return LocalDateTime.now().toString();
    }

    @GET
    @Path("/datetime")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTime() {
        return dateTime.toString();
    }
    
    @GET
    @Path("getVehicle")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getVehicle() {
    	
        return jsonFactory.createObjectBuilder()
                .add("id", vehicle.getId())
                .add("driver", vehicle.getDriver())
                .add("insurance", vehicle.getInsurance())
                .add("type", vehicle.getType())
                .add("worth", vehicle.getWorth())
                .add("manufacturer", vehicle.getManufacturer())
                .build();
    }
    
}
