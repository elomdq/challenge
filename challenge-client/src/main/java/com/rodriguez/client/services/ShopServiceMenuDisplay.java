package com.rodriguez.client.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodriguez.client.models.ShopServiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ShopServiceMenuDisplay {

    @Autowired
    private ShopServiceService shopService;

    @Autowired
    Scanner scanner;

    public void findShopServiceById(){
        System.out.println("\nIngresa el ID del servicio: ");
        Long id = scanner.nextLong();
        scanner.nextLine();

        System.out.println(shopService.findById(id));
    }

    public void addShopService() {

        try{
            System.out.println("Ingrese un servicio en formato JSON {\"title\":\"\", \"description\":\"\", \"price\":\"\",\"vehicle\":{\"id\":\"\"}}");
            String jsonInput = scanner.nextLine();

            ObjectMapper objectMapper = new ObjectMapper();

            ShopServiceDTO serviceDTO = objectMapper.readValue(jsonInput, ShopServiceDTO.class);

            System.out.println("Agregando...");
            System.out.println(shopService.create(serviceDTO));
            System.out.wait(3000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void editShopService() {
        System.out.println("Ingrese el id del servicio a modificar: ");

        try{
            Long id = scanner.nextLong();
            scanner.nextLine();

            System.out.println("Service: "  + shopService.findById(id));
            System.out.println("");
            System.out.println("Ingrese el servicio editado en formato JSON {\"title\":\"\", \"description\":\"\", \"price\":\"\"}");
            String jsonInput = scanner.nextLine();

            ObjectMapper objectMapper = new ObjectMapper();
            ShopServiceDTO serviceDTO = objectMapper.readValue(jsonInput, ShopServiceDTO.class);

            System.out.println("Editando...");
            System.out.println(shopService.update(serviceDTO, id));
            System.out.wait(3000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void borrarShopService() {
        System.out.println("Ingrese el id del servicio a eliminar: ");

        try{
            Long id = scanner.nextLong();
            scanner.nextLine();

            System.out.println("Borrando...");
            shopService.delete(id);
            System.out.wait(3000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
