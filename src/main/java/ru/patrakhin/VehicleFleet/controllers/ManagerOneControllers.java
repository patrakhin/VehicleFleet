package ru.patrakhin.VehicleFleet.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.patrakhin.VehicleFleet.dto.DriversDTO;
import ru.patrakhin.VehicleFleet.dto.EnterprisesDTO;
import ru.patrakhin.VehicleFleet.dto.VehiclesDTO;
import ru.patrakhin.VehicleFleet.models.Enterprises;
import ru.patrakhin.VehicleFleet.models.Managers;
import ru.patrakhin.VehicleFleet.repositories.ManagerRepository;
import ru.patrakhin.VehicleFleet.security.PersonDetails;
import ru.patrakhin.VehicleFleet.services.DriversService;
import ru.patrakhin.VehicleFleet.services.EnterprisesService;
import ru.patrakhin.VehicleFleet.services.ManagersService;
import ru.patrakhin.VehicleFleet.services.VehicleService;
import ru.patrakhin.VehicleFleet.util.EnterpriseErrorResponse;
import ru.patrakhin.VehicleFleet.util.EnterpriseNotCreatedException;

import java.util.List;

@RestController
@RequestMapping("/manager1")
public class ManagerOneControllers {
    private final VehicleService vehicleService;
    private final DriversService driversService;
    private final EnterprisesService enterprisesService;
    private final ManagersService managersService;
    private final ModelMapper modelMapper;
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerOneControllers(VehicleService vehicleService, DriversService driversService,
                                 EnterprisesService enterprisesService,
                                 ManagersService managersService, ModelMapper modelMapper,
                                 ManagerRepository managerRepository) {
        this.vehicleService = vehicleService;
        this.driversService = driversService;
        this.enterprisesService = enterprisesService;
        this.managersService = managersService;
        this.modelMapper = modelMapper;
        this.managerRepository = managerRepository;
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

    @PostMapping("/enterprises/new")
    public List <EnterprisesDTO> create(@RequestBody EnterprisesDTO enterprisesDTO, BindingResult bindingResult) {
        // Создание нового предприятия
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new EnterpriseNotCreatedException(errorMsg.toString());
        }
        enterprisesService.saveEnterprise(enterprisesDTO); //сохранили новое предприятие
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails userDetails = (PersonDetails) authentication.getPrincipal();
        Managers managers = new Managers();
        managers.setPerson(userDetails.getPerson());
        managers.setEnterprises(enterprisesService.getEnterpriseByName(enterprisesDTO));
        managerRepository.save(managers);
        Integer managerId = userDetails.getId();
        return managersService.getEnterpriseIdsByPersonId(managerId);
    }

    @ExceptionHandler
    private ResponseEntity<EnterpriseErrorResponse> handleException(EnterpriseNotCreatedException e){
        EnterpriseErrorResponse enterpriseErrorResponse = new EnterpriseErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(enterpriseErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private Enterprises convertToEnterprises(EnterprisesDTO enterprisesDTO) {
        return modelMapper.map(enterprisesDTO, Enterprises.class);
    }

    private EnterprisesDTO convertToEnterprisesDTO(Enterprises enterprises) {
        return modelMapper.map(enterprises, EnterprisesDTO.class);
    }
}
