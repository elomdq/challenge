package com.rodriguez.client;

import com.rodriguez.client.controllers.MenuDisplayController;
import com.rodriguez.client.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleApp implements CommandLineRunner {
    @Autowired
    private MenuDisplayController menuDisplayController;

    @Override
    public void run(String... args) throws Exception {
        try{
            menuDisplayController.displayHomeMenu();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
