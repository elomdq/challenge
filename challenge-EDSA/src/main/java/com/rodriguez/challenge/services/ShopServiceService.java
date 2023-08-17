package com.rodriguez.challenge.services;

import com.rodriguez.challenge.models.ShopService;

import java.util.List;

public interface ShopServiceService {

    public List<ShopService> list();
    public ShopService byId(Long id);
    public ShopService save(ShopService service);
    public void delete(Long id);
}
