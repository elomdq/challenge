package com.rodriguez.challenge.dto;

import com.rodriguez.challenge.models.Vehicle;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.io.Serializable;
import java.time.LocalDate;

public class ShopServiceDTO implements Serializable {

    private Long id;

    @NotBlank(message = "Please insert a service title")
    private String title;

    @NotBlank(message = "Please write a description for the service")
    private String description;

    @PastOrPresent(message = "The date must be in the past or present")
    private LocalDate date;
    @NotNull
    private Double price;
    @NotNull
    private VehicleDTO vehicle;

    //setter and getters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public VehicleDTO getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleDTO vehicle) {
        this.vehicle = vehicle;
    }
}
