package com.rodriguez.client.models;

import java.io.Serializable;
import java.util.List;

public class VehicleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String brand;
    private String color;
    private String year;
    private String plate;
    private String engineNumber;
    private String chassisNumber;
    private List<ShopServiceDTO> services;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public List<ShopServiceDTO> getServices() {
        return services;
    }

    public void setServices(List<ShopServiceDTO> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Vehicle {" +
                "\"id\":\"" + id + "\"" +
                ", \"brand\":\"" + brand + "\"" +
                ", \"color\":\"" + color + "\"" +
                ", \"year\":\"" + year + "\"" +
                ", \"plate\":\"" + plate + "\"" +
                ", \"engineNumber\":\"" + engineNumber + "\"" +
                ", \"chassisNumber\":\"" + chassisNumber + "\"" +
                ", \"services\":" + services +
                '}';
    }


}
