package com.rodriguez.challenge.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.List;

public class VehicleDTO implements Serializable {

    private Long id;
    @NotBlank(message = "Please insert the car Brand")
    private String brand;

    @NotBlank(message = "Include the color of the car.")
    private String color;
    @NotBlank(message = "Include the car year")
    private String year;
    @NotBlank(message = "You can't leave the plate in blank")
    @Size(min = 6, max = 7)
    @Pattern(regexp = "^(?i)([a-z]{3}\\d{3}|[a-z]{2}\\d{3}[a-z]{2})$", message = "Invalid license plate format")
    private String plate;

    @NotBlank(message = "You can't leave the engineNumber in blank")
    private String engineNumber;
    @NotBlank(message = "You can't leave the chassisNumber in blank")
    private String chassisNumber;
    private List<ShopServiceDTO> services;


    //Setter & getters


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
}
