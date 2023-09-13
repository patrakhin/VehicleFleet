package ru.patrakhin.VehicleFleet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.patrakhin.VehicleFleet.dto.VehicleDriversDTO;
import ru.patrakhin.VehicleFleet.models.Drivers;
import ru.patrakhin.VehicleFleet.models.VehicleDrivers;
import ru.patrakhin.VehicleFleet.models.Vehicles;
import ru.patrakhin.VehicleFleet.repositories.VehicleDriverRepository;

import java.util.List;

@Service
public class VehicleDriverService {

    private final VehicleDriverRepository vehicleDriverRepository;

    @Autowired
    public VehicleDriverService(VehicleDriverRepository vehicleDriverRepository) {
        this.vehicleDriverRepository = vehicleDriverRepository;
    }

    public void saveVehicleDriver(VehicleDriversDTO vehicleDriversDTO) {
       VehicleDrivers vehicleDrivers = new VehicleDrivers();
       vehicleDrivers.setDrivers(vehicleDriversDTO.getDrivers());
       vehicleDrivers.setVehicles(vehicleDriversDTO.getVehicles());
       vehicleDrivers.setActive(vehicleDriversDTO.isActive());
       vehicleDriverRepository.save(vehicleDrivers);
    }
}
