package com.rodriguez.challenge.repositories;

import com.rodriguez.challenge.models.ShopService;
import com.rodriguez.challenge.models.Vehicle;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VehicleRepository extends CrudRepository<Vehicle, Long> {

    @Query("select v from Vehicle v where v.plate = ?1")
    Optional<Vehicle> findByPlate(String plate);

    @Query("select v from Vehicle v right join v.services s where s.date = ?1")
    Iterable<Vehicle> findAllByDate(LocalDate date);

}
