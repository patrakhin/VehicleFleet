package ru.patrakhin.VehicleFleet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.patrakhin.VehicleFleet.dto.DriversDTO;
import ru.patrakhin.VehicleFleet.dto.EnterprisesDTO;
import ru.patrakhin.VehicleFleet.dto.ManagersDTO;
import ru.patrakhin.VehicleFleet.dto.VehiclesDTO;
import ru.patrakhin.VehicleFleet.models.Enterprises;
import ru.patrakhin.VehicleFleet.models.Managers;
import ru.patrakhin.VehicleFleet.models.Person;
import ru.patrakhin.VehicleFleet.security.PersonDetails;
import ru.patrakhin.VehicleFleet.services.DriversService;
import ru.patrakhin.VehicleFleet.services.EnterprisesService;
import ru.patrakhin.VehicleFleet.services.ManagersService;
import ru.patrakhin.VehicleFleet.services.VehicleService;

import java.util.List;

@RestController
@RequestMapping("/manager1")
public class ManagerOneControllers {
    private final VehicleService vehicleService;
    private final DriversService driversService;
    private final EnterprisesService enterprisesService;
    private final ManagersService managersService;

    @Autowired
    public ManagerOneControllers(VehicleService vehicleService, DriversService driversService,
                                 EnterprisesService enterprisesService, ManagersService managersService) {
        this.vehicleService = vehicleService;
        this.driversService = driversService;
        this.enterprisesService = enterprisesService;
        this.managersService = managersService;
    }

    @GetMapping("/vehicles")
    @ResponseBody
    public List<VehiclesDTO> getAllVehicles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails userDetails = (PersonDetails) authentication.getPrincipal();
        Integer managerId = userDetails.getId();
        return managersService.getVehiclesIdsByEnterprisesId(managerId);
    }

    @GetMapping("/drivers")
    @ResponseBody
    public List<DriversDTO> getAllDrivers(Authentication authentication){
        PersonDetails userDetails = (PersonDetails) authentication.getPrincipal();
        Integer managerId = userDetails.getId();
        return managersService.getDriversIdsByEnterprisesId(managerId);
    }

    @GetMapping("/enterprises")
    @ResponseBody
    public List<EnterprisesDTO> getAllEnterprises(Authentication authentication){
        PersonDetails userDetails = (PersonDetails) authentication.getPrincipal();
        Integer managerId = userDetails.getId();
        return managersService.getEnterpriseIdsByPersonId(managerId);
    }
}
