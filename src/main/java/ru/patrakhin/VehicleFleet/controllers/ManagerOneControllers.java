package ru.patrakhin.VehicleFleet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.patrakhin.VehicleFleet.dto.DriversDTO;
import ru.patrakhin.VehicleFleet.dto.EnterprisesDTO;
import ru.patrakhin.VehicleFleet.dto.ManagersDTO;
import ru.patrakhin.VehicleFleet.dto.VehiclesDTO;
import ru.patrakhin.VehicleFleet.models.Managers;
import ru.patrakhin.VehicleFleet.models.Person;
import ru.patrakhin.VehicleFleet.services.DriversService;
import ru.patrakhin.VehicleFleet.services.EnterprisesService;
import ru.patrakhin.VehicleFleet.services.ManagersService;
import ru.patrakhin.VehicleFleet.services.VehicleService;

import java.util.List;

@RestController
@RequestMapping("manager1")
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
    public List<VehiclesDTO> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @GetMapping("/drivers")
    public List<DriversDTO> getAllDrivers(){return driversService.getAllDrivers();}

    @GetMapping("/enterprises")
    public List<EnterprisesDTO> getAllEnterprises(){
        List<Integer> enterprisesId = managersService.getEnterpriseIdsByPersonId(1);
        List<EnterprisesDTO> enterprisesDTOList = enterprisesService.getEnterpriseById();
        return enterprisesService.getAllEnterprises();
    }
}
