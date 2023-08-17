package com.rodriguez.challenge.services;

import com.rodriguez.challenge.dto.VehicleDTO;
import com.rodriguez.challenge.mappers.Mapper;
import com.rodriguez.challenge.models.Vehicle;
import com.rodriguez.challenge.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional(readOnly = true)
    public List<Vehicle> findAll(){
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Vehicle byId(Long id){
        Vehicle vehicle = vehicleRepository.findById(id).get();
        return vehicle;
    }

    @Transactional(readOnly = true)
    public Vehicle byPlate(String plate){
        return vehicleRepository.findByPlate(plate).get();
    }

    @Transactional(readOnly = true)
    public List<Vehicle> byDate(LocalDate date) {
        return (List<Vehicle>) vehicleRepository.findAllByDate(date);
    }

    @Transactional(readOnly = false)
    public Vehicle save(Vehicle vehicle){
        return vehicleRepository.save(vehicle);
    }

    @Transactional(readOnly = false)
    public void delete(Long id){
        vehicleRepository.deleteById(id);
    }

}
