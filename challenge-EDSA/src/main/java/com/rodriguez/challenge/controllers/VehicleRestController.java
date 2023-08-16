package com.rodriguez.challenge.controllers;

import com.rodriguez.challenge.models.Vehicle;
import com.rodriguez.challenge.servicies.VehicleService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

@RestController()
@RequestMapping(path = "/vehicles")
public class VehicleRestController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/list")
    public List<Vehicle> list(){
        return vehicleService.list();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> byId(@PathVariable Long id){
        try{
            return new ResponseEntity(vehicleService.byId(id),HttpStatus.OK);
        }catch (NoSuchElementException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please enter a valid ID, such ID doesn't exist", ex);
        }
    }

    @GetMapping("/plate")
    public ResponseEntity<Vehicle> byPlate(
            @Pattern(regexp = "^(?i)([a-z]{3}\\d{3}|[a-z]{2}\\d{3}[a-z]{2})$", message="Invalid Format")
            @RequestParam("plate") String plate){
        try {
            return new ResponseEntity(vehicleService.byPlate(plate), HttpStatus.OK);
        }catch (NoSuchElementException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please enter a valid vehicle Plate", ex);
        }catch (ConstraintViolationException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid plate format - (XXX000 or XX000XX)", ex);
        }
    }

    @GetMapping("/list/date")
    public List<Vehicle> listByDate(@RequestParam("date") String date){
        LocalDate searchDate = LocalDate.parse(date);
        searchDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return vehicleService.byDate(searchDate);
    }

    @PostMapping
    public Vehicle create(@RequestBody Vehicle vehicle){
        return vehicleService.save(vehicle);
    }

    @PutMapping(path="/{id}")
    public Vehicle update(@RequestBody Vehicle editedVehicle, @PathVariable Long id){
        Vehicle vehicle = vehicleService.byId(id);
        if(vehicle!=null){
            crossData(vehicle,editedVehicle);
            return vehicleService.save(vehicle);
        }
        return null;
    }

    private void crossData(Vehicle vehicle, Vehicle editedVehicle){
        vehicle.setBrand(editedVehicle.getBrand());
        vehicle.setColor(editedVehicle.getColor());
        vehicle.setYear(editedVehicle.getYear());
        vehicle.setPlate(editedVehicle.getPlate());
        vehicle.setEngineNumber(editedVehicle.getEngineNumber());
        vehicle.setChassisNumber(editedVehicle.getChassisNumber());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        vehicleService.delete(id);
    }

}
