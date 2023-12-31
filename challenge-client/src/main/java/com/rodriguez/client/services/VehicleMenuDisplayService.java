package com.rodriguez.client.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rodriguez.client.models.ShopServiceDTO;
import com.rodriguez.client.models.VehicleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class VehicleMenuDisplayService {
    @Autowired
    private VehicleService vehicleService;

    @Autowired
    Scanner scanner;

    public void findVehicleByPlate(){
        System.out.println("\nIngresa la patente (Formatos Validos: XXX000 - XX00XX): ");
        String plate = scanner.nextLine();

        System.out.println(vehicleService.findByPlate(plate));
    }

    public void findVehicleByDate(){
        try{
            System.out.println("\nIngresa una fecha (Formato Valido: yyyy-mm-dd): ");
            String date = scanner.nextLine();

            LocalDate searchDate = LocalDate.parse(date);
            searchDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            System.out.println(vehicleService.findByDate(searchDate));
        }catch (InputMismatchException | DateTimeParseException ex){
            System.out.println("Hubo problemas con el formato de la fecha, intenta de nuevo.");
        }
    }

    public void addVehicle(){
        try{
            System.out.println("Ingrese el vehiculo en formato JSON {\"brand\":\"\", \"color\":\"\", \"year\":\"\", \"plate\":\"\", \"engineNumber\":\"\" ,\"chassisNumber\":\"\"}");
            String jsonInput = scanner.nextLine();

            ObjectMapper objectMapper = new ObjectMapper();
            VehicleDTO vehicleDTO = objectMapper.readValue(jsonInput, VehicleDTO.class);

            System.out.println("Agregando...");
            System.out.println(vehicleService.create(vehicleDTO));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }



    public void editVehicle(){
        try{
            System.out.println("Ingrese el id del auto a modificar: ");
            Long id = scanner.nextLong();
            scanner.nextLine();

            System.out.println("Vehiculo: "  + vehicleService.findById(id));
            System.out.println("");
            System.out.println("Ingrese el vehiculo editado en formato JSON {\"brand\":\"\", \"color\":\"\", \"year\":\"\", \"plate\":\"\", \"engineNumber\":\"\" ,\"chassisNumber\":\"\"}");
            String jsonInput = scanner.nextLine();

            ObjectMapper objectMapper = new ObjectMapper();
            VehicleDTO vehicleDTO = objectMapper.readValue(jsonInput, VehicleDTO.class);

            System.out.println("Editando...");
            System.out.println(vehicleService.update(vehicleDTO, id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteVehicle() {
        System.out.println("Ingrese el id del vehiculo a eliminar: ");

        try{
            Long id = scanner.nextLong();
            scanner.nextLine();

            System.out.println("Borrando...");
            vehicleService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
