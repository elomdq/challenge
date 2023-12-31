package com.rodriguez.challenge.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Hidden
@Table(name = "vehicles")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = 1L;

    @Basic
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic
    private String brand;
    @Basic
    private String color;
    @Basic
    @Column(name = "vehicle_year")
    private String year;
    @Basic
    @Column(unique = true)
    @Pattern(regexp = "^(?i)([a-z]{3}\\d{3}|[a-z]{2}\\d{3}[a-z]{2})$", message = "Invalid license plate format")
    private String plate;
    @Basic
    private String engineNumber;
    @Basic
    private String chassisNumber;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "vehicle", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ShopService> services;

    public Vehicle() {
    }

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

    public List<ShopService> getServices() {
        return services;
    }

    public void setServices(List<ShopService> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", year='" + year + '\'' +
                ", plate='" + plate + '\'' +
                ", engineNumber='" + engineNumber + '\'' +
                ", chassisNumber='" + chassisNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(brand, vehicle.brand) && Objects.equals(color, vehicle.color) && Objects.equals(year, vehicle.year) && Objects.equals(plate, vehicle.plate) && Objects.equals(engineNumber, vehicle.engineNumber) && Objects.equals(chassisNumber, vehicle.chassisNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, color, year, plate, engineNumber, chassisNumber);
    }
}
