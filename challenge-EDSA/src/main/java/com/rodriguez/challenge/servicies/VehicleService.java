package com.rodriguez.challenge.servicies;

import com.rodriguez.challenge.models.Vehicle;

import java.time.LocalDate;
import java.util.List;

public interface VehicleService {

    public List<Vehicle> list();
    //public List<Vehicle> listWithServices();
    public Vehicle byId(Long id);
    public Vehicle save(Vehicle vehicle);
    public List<Vehicle> byDate(LocalDate date);
    Vehicle byPlate(String plate);
    public void delete(Long id);

}
