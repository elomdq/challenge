package com.rodriguez.client.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class ShopServiceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String description;
    private LocalDate date;
    private Double price;
    private VehicleDTO vehicleDTO;

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

    public VehicleDTO getVehicleDTO() {
        return vehicleDTO;
    }

    public void setVehicleDTO(VehicleDTO vehicleDTO) {
        this.vehicleDTO = vehicleDTO;
    }

    @Override
    public String toString() {
        return "ShopServiceDTO{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", price=" + price +
                ", vehicleDTO=" + vehicleDTO +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopServiceDTO that = (ShopServiceDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(date, that.date) && Objects.equals(price, that.price) && Objects.equals(vehicleDTO, that.vehicleDTO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, date, price, vehicleDTO);
    }
}
