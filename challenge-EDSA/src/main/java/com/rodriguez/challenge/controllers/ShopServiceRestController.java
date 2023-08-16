package com.rodriguez.challenge.controllers;

import com.rodriguez.challenge.models.ShopService;
import com.rodriguez.challenge.servicies.ShopServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/services")
public class ShopServiceRestController {

    @Autowired
    ShopServiceService shopService;

    @GetMapping("/list")
    public List<ShopService> list(){
        return shopService.list();
    }

    @GetMapping("/{id}")
    public ShopService byId(@PathVariable Long id){
        return shopService.byId(id);
    }

    @PostMapping
    public ShopService create(@RequestBody ShopService service){
        return shopService.save(service);
    }

    @PutMapping("/{id}")
    public ShopService update(@RequestBody ShopService editedService, @PathVariable Long id){
        ShopService service = shopService.byId(id);

        if(service!=null){
            crossData(service, editedService);
            return shopService.save(service);
        }

        return null;
    }

    private void crossData(ShopService service, ShopService editedService){
        service.setTitle(service.getTitle());
        service.setPrice(service.getPrice());
        service.setDescription(service.getDescription());

    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        shopService.delete(id);
    }

}
