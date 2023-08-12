package ru.patrakhin.VehicleFleet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.patrakhin.VehicleFleet.dto.DriversDTO;
import ru.patrakhin.VehicleFleet.dto.EnterprisesDTO;
import ru.patrakhin.VehicleFleet.dto.VehiclesDTO;
import ru.patrakhin.VehicleFleet.services.DriversService;
import ru.patrakhin.VehicleFleet.services.EnterprisesService;
import ru.patrakhin.VehicleFleet.services.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VehicleApiController {

    private final VehicleService vehicleService;
    private final DriversService driversService;
    private final EnterprisesService enterprisesService;


    @Autowired
    public VehicleApiController(VehicleService vehicleService, DriversService driversService, EnterprisesService enterprisesService) {
        this.vehicleService = vehicleService;
        this.driversService = driversService;
        this.enterprisesService = enterprisesService;
    }

    @GetMapping("/vehicles")
    public List<VehiclesDTO> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/drivers")
    public List<DriversDTO> getAllDrivers(){return driversService.getAllDrivers();}

    @GetMapping("/enterprises")
    public List<EnterprisesDTO> getAllEnterprises(){return enterprisesService.getAllEnterprises();}
}
