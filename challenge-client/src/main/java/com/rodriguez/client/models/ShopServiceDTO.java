package com.rodriguez.client.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ShopServiceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private Double price;

    @JsonIgnoreProperties({"services"})
    private VehicleDTO vehicle;

    public ShopServiceDTO() {
    }

    /*public ShopServiceDTO() {
    }*/

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

    @Override
    public String toString() {
        return "ShopServiceDTO: {" +
                "\"title\"=\"" + title + '\"' +
                ", \"description\"=\"" + description + '\"' +
                ", \"date\"=\"" + date + '\"' +
                ", \"price\"=" + price +
                ", \"vehicle\"=" + vehicle +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopServiceDTO that = (ShopServiceDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(date, that.date) && Objects.equals(price, that.price) && Objects.equals(vehicle, that.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, date, price, vehicle);
    }
}
