package com.rodriguez.challenge.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Hidden
@Table(name="services")
public class ShopService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String title;
    @Basic
    private String description;

    @Basic
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    @Basic
    private Double price;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id", nullable = false)
    private Vehicle vehicle;

    public ShopService() {
    }

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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @PrePersist
    private void setCurrentDate(){
        this.date = LocalDate.now();
        date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    @Override
    public String toString() {
        return "Vehicle: " + vehicle + "\n"+
                "Title: " + title + "\n"+
                "Price: " + price + "\n"+
                "Date: " + date + "\n"+
                "Description: " + description ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopService shopService = (ShopService) o;
        return Objects.equals(title, shopService.title) && Objects.equals(description, shopService.description) && Objects.equals(date, shopService.date) && Objects.equals(price, shopService.price) && Objects.equals(vehicle, shopService.vehicle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, date, price, vehicle);
    }
}
