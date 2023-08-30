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
import ru.patrakhin.VehicleFleet.util.EnterpriseNotFoundException;
import ru.patrakhin.VehicleFleet.util.VehicleNotCreatedException;

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

    @PostMapping("/vehicles/new")
    public List <VehiclesDTO> createVehicle(@RequestBody VehiclesDTO vehiclesDTO, BindingResult bindingResult) {
        // Создание новой машинки
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");
            }
            throw new VehicleNotCreatedException(errorMsg.toString());
        }
        System.out.println(vehiclesDTO);
        vehicleService.addVehicle(vehiclesDTO); //сохранили новую машинку
        return getAllVehicles(); //вывели весь список машинок
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
    public List <EnterprisesDTO> createEnterprise(@RequestBody EnterprisesDTO enterprisesDTO, BindingResult bindingResult) {
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
        managerRepository.save(managers); //сохранили связь менедж и предприятия
        Integer managerId = userDetails.getId();
        return managersService.getEnterpriseIdsByPersonId(managerId);
    }

    @PutMapping("/enterprises/{id}")
    public EnterprisesDTO updateEnterprise(@PathVariable("id") int id, @RequestBody EnterprisesDTO enterprisesDTO,
                                           BindingResult bindingResult) {
        // Обновление данных предприятия по id
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
        Enterprises existingEnterprises = enterprisesService.getEnterprisesById(id);
        if (existingEnterprises == null) {
            throw new EnterpriseNotFoundException("Enterprises with this id wasn't found!");
        }
        Enterprises updatedEnterprises = convertToEnterprises(enterprisesDTO);
        updatedEnterprises.setId(id);
        enterprisesService.updateEnterprise(id, enterprisesDTO); //уже сделанная функция обновления в сервисе
        return convertToEnterprisesDTO(enterprisesService.getEnterprisesById(id));
    }

    @DeleteMapping("enterprises/delete/{id}")
    public EnterprisesDTO deleteEnterprise(@PathVariable("id") int id) {
        // Удаление предприятия по id
        Enterprises existingEnterprise = enterprisesService.getEnterprisesById(id);
        if (existingEnterprise == null) {
            throw new EnterpriseNotFoundException("Enterprises with this id wasn't found!");
        }
        enterprisesService.deleteEnterprise(id);
        return convertToEnterprisesDTO(existingEnterprise);
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
