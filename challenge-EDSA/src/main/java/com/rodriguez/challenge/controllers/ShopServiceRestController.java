package com.rodriguez.challenge.controllers;

import com.rodriguez.challenge.dto.ShopServiceDTO;
import com.rodriguez.challenge.mappers.Mapper;
import com.rodriguez.challenge.models.ShopService;
import com.rodriguez.challenge.models.Vehicle;
import com.rodriguez.challenge.services.ShopServiceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/services")
@Validated
public class ShopServiceRestController {

    @Autowired
    ShopServiceService shopService;

    @Autowired
    private Mapper mapper;

    @GetMapping
    public ResponseEntity<List<ShopServiceDTO>> findAll(){
        try{
            return new ResponseEntity(mapper.toShopServicesDtos(shopService.list()), HttpStatus.OK);
        }catch(RuntimeException ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "We have a problem, try later", ex);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopServiceDTO> findById(@PathVariable Long id){
        try{
            return new ResponseEntity(mapper.toShopServiceDto(shopService.byId(id)), HttpStatus.OK);
        }catch (NoSuchElementException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please enter a valid ID, such ID doesn't exist", ex);
        }catch(RuntimeException ex){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "We have a problem, try later", ex);
        }
    }
    @PostMapping
    public ResponseEntity<ShopServiceDTO> create(@Valid @RequestBody ShopService service, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessage = new StringBuilder("Errors:\n");
            for (ObjectError error : errors) {
                errorMessage.append("- ").append(error.getDefaultMessage()).append("\n");
            }
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage.toString());
        } else {
            try {
                return new ResponseEntity(mapper.toShopServiceDto(shopService.save(service)), HttpStatus.CREATED);
            } catch (RuntimeException ex) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "We have a problem, try later", ex);
            }
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ShopServiceDTO> update(@Valid @RequestBody ShopService editedService, @PathVariable Long id, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            StringBuilder errorMessage = new StringBuilder("Errors:\n");
            for (ObjectError error : errors) {
                errorMessage.append("- ").append(error.getDefaultMessage()).append("\n");
            }
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, errorMessage.toString());
        } else {
            try {
                ShopService service = shopService.byId(id);
                crossData(service, editedService);
                return new ResponseEntity(mapper.toShopServiceDto(shopService.save(service)), HttpStatus.CREATED);
            } catch (NoSuchElementException ex) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please enter a valid ID", ex);
            } catch (RuntimeException ex) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "We have a problem, try later", ex);
            }
        }
    }

    private void crossData(ShopService service, ShopService editedService){
        service.setTitle(service.getTitle());
        service.setPrice(service.getPrice());
        service.setDescription(service.getDescription());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        try{
            shopService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(NoSuchElementException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please enter a valid vehicle Plate", ex);
        }catch (RuntimeException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "We have a problem, try later", ex);
        }
    }

}
