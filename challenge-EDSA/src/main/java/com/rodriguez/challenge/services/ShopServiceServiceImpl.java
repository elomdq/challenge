package com.rodriguez.challenge.services;

import com.rodriguez.challenge.models.ShopService;
import com.rodriguez.challenge.repositories.ShopServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShopServiceServiceImpl implements ShopServiceService {

    @Autowired
    private ShopServiceRepository shopServiceRepository;

    public ShopServiceServiceImpl() {
    }
    @Transactional(readOnly = true)
    public List<ShopService> list(){
        return (List<ShopService>) shopServiceRepository.findAll();
    }
    @Transactional(readOnly = true)
    public ShopService byId(Long id){
        return shopServiceRepository.findById(id).orElse(null);
    }
    @Transactional(readOnly = false)
    public ShopService save(ShopService service){
        return shopServiceRepository.save(service);
    }

    @Transactional(readOnly = false)
    public void delete(Long id){
        shopServiceRepository.deleteById(id);
    }
}
