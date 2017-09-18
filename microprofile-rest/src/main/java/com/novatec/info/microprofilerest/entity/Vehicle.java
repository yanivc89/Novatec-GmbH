package com.novatec.info.microprofilerest.entity;

public class Vehicle {

	private long id;

    private String driver;

    private String insurance;

    private String type;

    private double worth;

    private String manufacturer;

    public Vehicle() {
    	
    	this.id = 1;
    	this.driver = "Vinay";
    	this.insurance = "AOK";
    	this.type = "Health";
    	this.worth = 1000;
    	this.manufacturer = "Bosch";
    	
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWorth() {
        return worth;
    }

    public void setWorth(double worth) {
        this.worth = worth;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}