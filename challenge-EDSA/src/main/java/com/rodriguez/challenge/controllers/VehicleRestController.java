package com.rodriguez.challenge.controllers;

import com.rodriguez.challenge.dto.VehicleDTO;
import com.rodriguez.challenge.mappers.Mapper;
import com.rodriguez.challenge.models.Vehicle;
import com.rodriguez.challenge.services.VehicleService;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;

@RestController()
@RequestMapping(path = "/vehicles")
@Validated
public class VehicleRestController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<List<VehicleDTO>> findAll(){
        try {
            return new ResponseEntity(mapper.toVehicleDtos(vehicleService.findAll()), HttpStatus.OK);
        }catch(RuntimeException ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "We have a problem, try later", ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> findById(@PathVariable Long id){
        try{
            return new ResponseEntity(mapper.toVehicleDto(vehicleService.byId(id)),HttpStatus.OK);
        }catch(NoSuchElementException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please enter a valid ID, such ID doesn't exist", ex);
        }catch(RuntimeException ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "We have a problem, try later", ex);
        }
    }

    @GetMapping("/plate")
    public ResponseEntity<VehicleDTO> findByPlate(
            @Pattern(regexp = "^(?i)([a-z]{3}\\d{3}|[a-z]{2}\\d{3}[a-z]{2})$", message="Invalid plate format - (XXX000 or XX000XX)")
            @RequestParam("plate") String plate){
        try {
            return new ResponseEntity(mapper.toVehicleDto(vehicleService.byPlate(plate)), HttpStatus.OK);
        }catch (NoSuchElementException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please enter a valid vehicle Plate", ex);
        }catch (ConstraintViolationException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid plate format - (XXX000 or XX000XX)", ex);
        }
    }

    @GetMapping("/list/date")
    public ResponseEntity<List<VehicleDTO>> findAllByDate(@RequestParam("date") String date){
        try {
            LocalDate searchDate = LocalDate.parse(date);
            searchDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return new ResponseEntity(mapper.toVehicleDtos(vehicleService.byDate(searchDate)), HttpStatus.OK);
        } catch (DateTimeParseException ex){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please enter a valid date - format (dd-MM-yyyy)", ex);
        } catch(RuntimeException ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "We have a problem, try later", ex);
        }
    }

    @PostMapping
    public ResponseEntity<VehicleDTO> create(@Valid @RequestBody VehicleDTO vehicle,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessage = new StringBuilder("Errors:\n");
            for (ObjectError error : errors) {
                errorMessage.append("- ").append(error.getDefaultMessage()).append("\n");
            }
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage.toString());
        }else{
            try{
                return new ResponseEntity(mapper.toVehicleDto(vehicleService.save(mapper.toVehicle(vehicle))), HttpStatus.CREATED);
            }catch(RuntimeException ex){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "We have a problem, try later", ex);
            }
        }
    }

    @PutMapping(path="/{id}")
    public ResponseEntity<VehicleDTO> update(@Valid @RequestBody VehicleDTO editedVehicle, @PathVariable Long id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessage = new StringBuilder("Errors:\n");
            for (ObjectError error : errors) {
                errorMessage.append("- ").append(error.getDefaultMessage()).append("\n");
            }
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage.toString());
        }else{
            try {
                Vehicle vehicle = vehicleService.byId(id);
                crossData(vehicle, mapper.toVehicle(editedVehicle));
                return new ResponseEntity(mapper.toVehicleDto(vehicleService.save(vehicle)), HttpStatus.OK);
            }catch (NoSuchElementException ex ){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please enter a valid ID", ex);
            }catch(RuntimeException ex){
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "We have a problem, try later", ex);
            }
        }
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
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            vehicleService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "We have a problem, try later", ex);
        }
    }

}
