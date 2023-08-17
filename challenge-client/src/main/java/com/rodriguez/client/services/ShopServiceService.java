package com.rodriguez.client.services;

import com.rodriguez.client.models.ShopServiceDTO;
import com.rodriguez.client.models.VehicleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShopServiceService {

    @Autowired
    private RestTemplate restTemplate;


    public List<ShopServiceDTO> findAll(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<List<ShopServiceDTO>> response = restTemplate.exchange(
                "/services",
                HttpMethod.GET,
                new HttpEntity<>("requestBody", headers),
                new ParameterizedTypeReference<List<ShopServiceDTO>>(){}
        );
        return response.getBody();
    }
    public ShopServiceDTO findById(Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<ShopServiceDTO> response = restTemplate.exchange(
                "/services/"+id,
                HttpMethod.GET,
                new HttpEntity<>("requestBody", headers),
                ShopServiceDTO.class
        );
        return response.getBody();
    }

    public void delete(Long id){
        HttpHeaders headers = new HttpHeaders();;

        ResponseEntity<Void> response = restTemplate.exchange(
                "/services/"+id,
                HttpMethod.DELETE,
                new HttpEntity<>("requestBody", headers),
                void.class
        );
    }

    public ShopServiceDTO create(ShopServiceDTO vehicle){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ShopServiceDTO> vehicleEntity = new HttpEntity<>(vehicle, headers);

        ResponseEntity<ShopServiceDTO> response = restTemplate.exchange(
                "/services",
                HttpMethod.POST,
                vehicleEntity,
                ShopServiceDTO.class
        );

        return response.getBody();
    }

    public ShopServiceDTO update(ShopServiceDTO vehicle, Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<ShopServiceDTO> vehicleEntity = new HttpEntity<>(vehicle, headers);

        ResponseEntity<ShopServiceDTO> response = restTemplate.exchange(
                "/services/"+id,
                HttpMethod.PUT,
                vehicleEntity,
                ShopServiceDTO.class
        );

        return response.getBody();
    }
}
