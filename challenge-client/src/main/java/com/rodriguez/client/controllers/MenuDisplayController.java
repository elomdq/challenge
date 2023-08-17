package com.rodriguez.client.controllers;

import com.rodriguez.client.models.ShopServiceDTO;
import com.rodriguez.client.models.VehicleDTO;
import com.rodriguez.client.services.MenuDisplayService;
import com.rodriguez.client.services.ShopServiceService;
import com.rodriguez.client.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

@Controller
public class MenuDisplayController {

    @Autowired
    MenuDisplayService menuDisplayService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ShopServiceService service;

    public void displayHomeMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        try {

            do {
                System.out.println("====================");
                System.out.println("        Menu");
                System.out.println("====================");
                System.out.println("1. Vehiculos");
                System.out.println("2. Servicios");
                System.out.println("3. Exit");
                System.out.println("Enter your option: ");

                try {
                    option = scanner.nextInt();

                    switch (option) {
                        case 1:
                            displayVehicleMenu();
                            break;
                        case 2:
                            // Implement your logic for services menu
                            break;
                        case 3:
                            System.out.println("Exiting...");
                            break;
                        default:
                            System.out.println("Invalid option. Please select a valid option.");
                    }
                } catch (InputMismatchException ex) {;
                    scanner.nextLine(); // Clear invalid input from the buffer
                    option = 0; // Set a non-valid option to continue the loop
                }
            } while (option != 3);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    public void displayVehicleMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        try {
            do {
                System.out.println("====================");
                System.out.println("    Vehicle Menu");
                System.out.println("====================");
                System.out.println("1. Agregar");
                System.out.println("2. Lista");
                System.out.println("3. Buscar");
                System.out.println("4. Editar");
                System.out.println("5. Volver");
                System.out.println("Enter your option: ");

                try {
                    option = scanner.nextInt();

                    switch (option) {
                        case 1:
                            menuDisplayService.addVehicle();
                            break;
                        case 2:
                            System.out.println("List of Vehicles:");
                            for (VehicleDTO vehicle : vehicleService.findAll()) {
                                System.out.println(vehicle);
                            }
                            break;
                        case 3:
                            displayVehicleSearchMenu();
                            break;
                        case 4:
                            menuDisplayService.editVehicle();
                            break;
                        case 5:
                            displayHomeMenu();
                            break;
                        default:
                            System.out.println("Invalid option. Please select a valid option.");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input. Please enter a valid option.");
                    scanner.nextLine(); // Clear invalid input from the buffer
                    option = 0; // Set a non-valid option to continue the loop
                }
            } while (option != 4);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    public void displayVehicleSearchMenu() {
        int option;

        try {
            do {
                Scanner scanner = new Scanner(System.in);


                System.out.println("====================");
                System.out.println("    Vehicle Search Menu");
                System.out.println("====================");
                System.out.println("1. Auto por Patente");
                System.out.println("2. Autos por Fecha de Entrada");
                System.out.println("3. Volver");
                System.out.println("Enter your option: ");

                try {
                    option = scanner.nextInt();

                    switch (option) {
                        case 1:
                            menuDisplayService.findByPlate();
                            break;
                        case 2:
                            menuDisplayService.findByDate();
                            break;
                        case 3:
                            displayVehicleMenu();
                            break;
                        default:
                            System.out.println("No es una opción válida");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input. Please enter a valid option.");
                    scanner.nextLine(); // Clear invalid input from the buffer
                    option = 0; // Set a non-valid option to continue the loop
                }

                scanner.close();

            } while (option != 3);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        } finally {

        }
    }




    public void displayServiceMenu() {
        Scanner scanner = new Scanner(System.in);
        int option;

        try {
            do {
                System.out.println("====================");
                System.out.println("    Service Menu");
                System.out.println("====================");
                System.out.println("1. Agregar");
                System.out.println("2. Listar");
                System.out.println("3. Buscar");
                System.out.println("4. Editar");
                System.out.println("5. Volver");
                System.out.println("Enter your option: ");

                try {
                    option = scanner.nextInt();

                    switch (option) {
                        case 1:
                            menuDisplayService.addShopService();
                            break;
                        case 2:
                            System.out.println("List of Services:");
                            for (ShopServiceDTO service :service.findAll() ) {
                                System.out.println(service);
                            }
                            break;
                        case 3:
                            displayServiceSearchMenu();
                            break;
                        case 4:
                            menuDisplayService.editService();
                            break;
                        case 5:
                            displayHomeMenu();
                            break;
                        default:
                            System.out.println("Invalid option. Please select a valid option.");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input. Please enter a valid option.");
                    scanner.nextLine(); // Clear invalid input from the buffer
                    option = 0; // Set a non-valid option to continue the loop
                }
            } while (option != 4);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private void displayServiceSearchMenu() {
    }
}
