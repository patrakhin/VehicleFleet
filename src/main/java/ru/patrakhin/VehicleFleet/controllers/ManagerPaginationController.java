package ru.patrakhin.VehicleFleet.controllers;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.patrakhin.VehicleFleet.dto.VehiclesForManagersDTO;
import ru.patrakhin.VehicleFleet.repositories.ManagerRepository;
import ru.patrakhin.VehicleFleet.security.PersonDetails;
import ru.patrakhin.VehicleFleet.services.*;

@RestController
@RequestMapping("/pagination_manager1")
public class ManagerPaginationController {
    private final VehicleService vehicleService;
    private final DriversService driversService;
    private final EnterprisesService enterprisesService;
    private final ManagersService managersService;
    private final ModelMapper modelMapper;
    private final ManagerRepository managerRepository;
    private final CarBrandService carBrandService;
    private final ManagerPaginationService managerPaginationService;

    @Autowired
    public ManagerPaginationController(VehicleService vehicleService, DriversService driversService,
                                 EnterprisesService enterprisesService,
                                 ManagersService managersService, ModelMapper modelMapper,
                                 ManagerRepository managerRepository, CarBrandService carBrandService, ManagerPaginationService managerPaginationService) {
        this.vehicleService = vehicleService;
        this.driversService = driversService;
        this.enterprisesService = enterprisesService;
        this.managersService = managersService;
        this.modelMapper = modelMapper;
        this.managerRepository = managerRepository;
        this.carBrandService = carBrandService;
        this.managerPaginationService = managerPaginationService;
    }

    @GetMapping("/vehicles")
    @ResponseBody
    public Page<VehiclesForManagersDTO> getAllVehicles(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails userDetails = (PersonDetails) authentication.getPrincipal();
        int managerId = userDetails.getId();
        return managerPaginationService.getVehiclesIdsByEnterprisesId(managerId, page, size);
    }
}
