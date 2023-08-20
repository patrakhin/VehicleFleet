package ru.patrakhin.VehicleFleet.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.patrakhin.VehicleFleet.dto.DriversDTO;
import ru.patrakhin.VehicleFleet.dto.EnterprisesDTO;
import ru.patrakhin.VehicleFleet.dto.VehiclesDTO;
import ru.patrakhin.VehicleFleet.security.PersonDetails;
import ru.patrakhin.VehicleFleet.services.DriversService;
import ru.patrakhin.VehicleFleet.services.EnterprisesService;
import ru.patrakhin.VehicleFleet.services.ManagersService;
import ru.patrakhin.VehicleFleet.services.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/manager2")
public class ManagerTwoController {
    private final VehicleService vehicleService;
    private final DriversService driversService;
    private final EnterprisesService enterprisesService;
    private final ManagersService managersService;

    @Autowired
    public ManagerTwoController(VehicleService vehicleService, DriversService driversService,
                                 EnterprisesService enterprisesService, ManagersService managersService) {
        this.vehicleService = vehicleService;
        this.driversService = driversService;
        this.enterprisesService = enterprisesService;
        this.managersService = managersService;
    }

    @GetMapping("/vehicles_2")
    public List<VehiclesDTO> getAllVehicles(Authentication authentication) {
        PersonDetails userDetails = (PersonDetails) authentication.getPrincipal();
        Integer managerId = userDetails.getId();
        return managersService.getVehiclesIdsByEnterprisesId(managerId);
    }

    @GetMapping("/drivers_2")
    public List<DriversDTO> getAllDrivers(Authentication authentication){
        PersonDetails userDetails = (PersonDetails) authentication.getPrincipal();
        Integer managerId = userDetails.getId();
        return managersService.getDriversIdsByEnterprisesId(managerId);
    }

    @GetMapping("/enterprises_2")
    public List<EnterprisesDTO> getAllEnterprises(Authentication authentication){
        PersonDetails userDetails = (PersonDetails) authentication.getPrincipal();
        Integer managerId = userDetails.getId();
        return managersService.getEnterpriseIdsByPersonId(managerId);
    }
}
