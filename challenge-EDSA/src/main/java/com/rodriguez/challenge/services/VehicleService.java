package com.rodriguez.challenge.services;

import com.rodriguez.challenge.models.Vehicle;

import java.time.LocalDate;
import java.util.List;

public interface VehicleService {

    public List<Vehicle> findAll();
    //public List<Vehicle> listWithServices();
    public Vehicle byId(Long id);
    public Vehicle save(Vehicle vehicle);
    public List<Vehicle> byDate(LocalDate date);
    public Vehicle byPlate(String plate);
    public void delete(Long id);

}
