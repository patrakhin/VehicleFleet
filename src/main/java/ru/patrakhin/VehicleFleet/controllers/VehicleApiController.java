package ru.patrakhin.VehicleFleet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.patrakhin.VehicleFleet.dto.VehiclesDTO;
import ru.patrakhin.VehicleFleet.services.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleApiController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleApiController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping()
    public List<VehiclesDTO> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }
}
