package com.rodriguez.challenge.servicies;

import com.rodriguez.challenge.models.Vehicle;
import com.rodriguez.challenge.repositories.VehicleRepository;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;

    @Transactional(readOnly = true)
    public List<Vehicle> list(){
        return (List<Vehicle>) vehicleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Vehicle byId(Long id){

        return vehicleRepository.findById(id).get();
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
