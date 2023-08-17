package com.rodriguez.challenge.mappers;

import com.rodriguez.challenge.dto.ShopServiceDTO;
import com.rodriguez.challenge.dto.VehicleDTO;
import com.rodriguez.challenge.models.ShopService;
import com.rodriguez.challenge.models.Vehicle;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {

    //Vehicles
    @Mapping(target = "services", qualifiedByName = "noVehicle")
    @Mapping(target = "id", ignore = false)
    public VehicleDTO toVehicleDto(Vehicle vehicle);

    @Named("noServices")
    @Mapping(target = "services", ignore = true)
    public VehicleDTO toVehicleDtoWithoutServices(Vehicle vehicle);

    public Vehicle toVehicle(VehicleDTO vehicleDTO);

    @Mapping(target = "services.vehicle", ignore = true)
    public List<VehicleDTO> toVehicleDtos(List<Vehicle> vehicles);


    //Services
    @Named("noVehicle")
    @Mapping(target = "vehicle", ignore = true)
    public ShopServiceDTO toShopServiceDtoWithoutVehicle(ShopService entity);

    @Mapping(target = "vehicle", qualifiedByName = "noServices")
    public ShopServiceDTO toShopServiceDto(ShopService entity);

    public ShopService shopServiceDtoToShopService(ShopServiceDTO dto);

    public List<ShopServiceDTO> toShopServicesDtos(List<ShopService> shopServices);
}
