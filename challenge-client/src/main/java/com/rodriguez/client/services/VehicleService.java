package com.rodriguez.client.services;

import com.rodriguez.client.models.VehicleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private RestTemplate restTemplate;

    public VehicleService() {
    }

    public List<VehicleDTO> findAll(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<List<VehicleDTO>> response = restTemplate.exchange(
                "/vehicles",
                HttpMethod.GET,
                new HttpEntity<>("requestBody", headers),
                new ParameterizedTypeReference<List<VehicleDTO>>(){}
        );
        return response.getBody();
    }
    public VehicleDTO findById(Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<VehicleDTO> response = restTemplate.exchange(
                "/vehicles/"+id,
                HttpMethod.GET,
                new HttpEntity<>("requestBody", headers),
                VehicleDTO.class
        );
        return response.getBody();
    }

    public VehicleDTO findByPlate(String plate){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<VehicleDTO> response = restTemplate.exchange(
                "/vehicles/plate?plate="+plate,
                HttpMethod.GET,
                new HttpEntity<>("requestBody", headers),
                VehicleDTO.class
        );
        return response.getBody();
    }

    public List<VehicleDTO> findByDate(LocalDate date){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<List<VehicleDTO>> response = restTemplate.exchange(
                "/vehicles/list/date?date="+date,
                HttpMethod.GET,
                new HttpEntity<>("requestBody", headers),
                new ParameterizedTypeReference<List<VehicleDTO>>(){}
        );
        return response.getBody();
    }

    public void delete(Long id){
        HttpHeaders headers = new HttpHeaders();;

        ResponseEntity<Void> response = restTemplate.exchange(
                "/vehicles/"+id,
                HttpMethod.DELETE,
                new HttpEntity<>("requestBody", headers),
                void.class
        );
    }

    public VehicleDTO create(VehicleDTO vehicle){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<VehicleDTO> vehicleEntity = new HttpEntity<>(vehicle, headers);

        ResponseEntity<VehicleDTO> response = restTemplate.exchange(
                "/vehicles",
                HttpMethod.POST,
                vehicleEntity,
                VehicleDTO.class
        );

        return response.getBody();
    }

    public VehicleDTO update(VehicleDTO vehicle, Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<VehicleDTO> vehicleEntity = new HttpEntity<>(vehicle, headers);

        ResponseEntity<VehicleDTO> response = restTemplate.exchange(
                "/vehicles/"+id,
                HttpMethod.PUT,
                vehicleEntity,
                VehicleDTO.class
        );

        return response.getBody();
    }

}
