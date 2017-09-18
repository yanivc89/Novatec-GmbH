package com.novatec.info.microprofilerest.rest;

import java.time.LocalDateTime;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import com.novatec.info.microprofilerest.entity.Vehicle;

@ApplicationScoped
public class DataProducer {
	
	@Produces
    LocalDateTime getDateTimeNow() {
        return LocalDateTime.now();
    }
	
	@Produces
	Vehicle getVehicle() {
		 return new Vehicle();
	}
  
}
