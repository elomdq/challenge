package com.rodriguez.client.controllers;

import com.rodriguez.client.models.ShopServiceDTO;
import com.rodriguez.client.models.VehicleDTO;
import com.rodriguez.client.services.ShopServiceMenuDisplay;
import com.rodriguez.client.services.VehicleMenuDisplayService;
import com.rodriguez.client.services.ShopServiceService;
import com.rodriguez.client.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.InputMismatchException;
import java.util.Scanner;

@Controller
public class MenuDisplayController {

    @Autowired
    private VehicleMenuDisplayService vehicleMenuDisplayService;
    @Autowired
    private ShopServiceMenuDisplay shopServiceMenuDisplay;
    @Autowired
    private Scanner scanner;
    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private ShopServiceService service;


    public void displayHomeMenu() {
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
                            displayServiceMenu();
                            break;
                        case 3:
                            System.out.println("Exiting...");
                            break;
                        default:
                            System.out.println("Invalid option. Please select a valid option.");
                    }
                } catch (InputMismatchException ex) {
                    option = 0; // Set a non-valid option to continue the loop
                }
            } while (option != 3);
            System.exit(0);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }

    public void displayVehicleMenu() {
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
                System.out.println("5. Borrar");
                System.out.println("");
                System.out.println("6. Volver");
                System.out.println("");
                System.out.println("Enter your option: ");

                try {
                    option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1:
                            vehicleMenuDisplayService.addVehicle();
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
                            vehicleMenuDisplayService.editVehicle();
                            break;
                        case 5:
                            vehicleMenuDisplayService.deleteVehicle();
                            break;
                        case 6:
                            displayHomeMenu();
                            break;
                        default:
                            System.out.println("Invalid option. Please select a valid option.");
                    }
                } catch (Exception ex) {
                    System.out.println("Invalid input. Please enter a valid option.");
                    option = 0;
                }
            } while (option != 6);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }

    public void displayVehicleSearchMenu() {
        int option;

        try {
            do {
                System.out.println("====================");
                System.out.println("    Vehicle Search Menu");
                System.out.println("====================");
                System.out.println("1. Auto por Patente");
                System.out.println("2. Autos por Fecha de Entrada");
                System.out.println("");
                System.out.println("3. Volver");
                System.out.println("");
                System.out.println("Enter your option: ");

                try {
                    option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1:
                            vehicleMenuDisplayService.findVehicleByPlate();
                            break;
                        case 2:
                            vehicleMenuDisplayService.findVehicleByDate();
                            break;
                        case 3:
                            displayVehicleMenu();
                            break;
                        default:
                            System.out.println("No es una opción válida");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input. Please enter a valid option.");
                    option = 0;
                }
            } while (option != 3);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }


    public void displayServiceMenu() {
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
                System.out.println("5. Borrar");
                System.out.println("");
                System.out.println("6. Volver");
                System.out.println("");
                System.out.println("Enter your option: ");

                try {
                    option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1:
                            shopServiceMenuDisplay.addShopService();
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
                            shopServiceMenuDisplay.editShopService();
                            break;
                        case 5:
                            shopServiceMenuDisplay.borrarShopService();
                            break;
                        case 6:
                            displayHomeMenu();
                            break;
                        default:
                            System.out.println("Invalid option. Please select a valid option.");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input. Please enter a valid option.");
                    option = 0;
                }
            } while (option != 6);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }

    private void displayServiceSearchMenu() {
        int option;

        try {
            do {

                System.out.println("====================");
                System.out.println("    Service Search Menu");
                System.out.println("====================");
                System.out.println("1. por ID");
                System.out.println("2. Volver");
                System.out.println("Enter your option: ");

                try {
                    option = scanner.nextInt();
                    scanner.nextLine();

                    switch (option) {
                        case 1:
                            shopServiceMenuDisplay.findShopServiceById();
                            break;
                        case 2:
                            displayServiceMenu();
                            break;
                        default:
                            System.out.println("No es una opción válida");
                    }
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input. Please enter a valid option.");
                    option = 0;
                }

            } while (option != 2);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
        }

    }
}
