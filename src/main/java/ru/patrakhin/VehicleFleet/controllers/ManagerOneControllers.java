package ru.patrakhin.VehicleFleet.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.patrakhin.VehicleFleet.dto.*;
import ru.patrakhin.VehicleFleet.models.Enterprises;
import ru.patrakhin.VehicleFleet.models.Managers;
import ru.patrakhin.VehicleFleet.models.Vehicles;
import ru.patrakhin.VehicleFleet.repositories.ManagerRepository;
import ru.patrakhin.VehicleFleet.security.PersonDetails;
import ru.patrakhin.VehicleFleet.services.*;
import ru.patrakhin.VehicleFleet.util.EnterpriseErrorResponse;
import ru.patrakhin.VehicleFleet.util.EnterpriseNotCreatedException;
import ru.patrakhin.VehicleFleet.util.VehicleNotCreatedException;

import java.util.List;
@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/manager1")
public class ManagerOneControllers {
    private final VehicleService vehicleService;
    private final DriversService driversService;
    private final EnterprisesService enterprisesService;
    private final ManagersService managersService;
    private final ModelMapper modelMapper;
    private final ManagerRepository managerRepository;
    private final CarBrandService carBrandService;
    private final ManagerPaginationService managerPaginationService;

    @Autowired
    public ManagerOneControllers(VehicleService vehicleService, DriversService driversService,
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
    public List<VehiclesForManagersDTO> getAllVehicles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails userDetails = (PersonDetails) authentication.getPrincipal();
        int managerId = userDetails.getId();
        return managersService.getVehiclesIdsByEnterprisesId(managerId);
    }

    @GetMapping("/vehicles/car_brand")
    public List<CarBrandDTO> getAllCarBrand() {
        return carBrandService.getAllCarBrands();
    }


    @PostMapping("/vehicles/new")
    public List <VehiclesForManagersDTO> createNewVehicle(@RequestBody VehiclesDTO vehiclesDTO, BindingResult bindingResult) {
        // Создание новой машины //вернулся
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

        vehicleService.save(vehiclesDTO.toVehicles()); //сохранили новую машину
        return getAllVehicles();
    }

    @GetMapping("/vehicle/{id}")
    public VehiclesForManagersDTO getVehicleById(@PathVariable("id") int id) {
        // Получаем существующее транспортное средство по ID
        List<VehiclesForManagersDTO> allVehicles = getAllVehicles();
        VehiclesForManagersDTO resultVehicle = new VehiclesForManagersDTO();
        for (VehiclesForManagersDTO vehiclesForManagersDTO : allVehicles) {
            if (vehiclesForManagersDTO.getId() == id) {
                resultVehicle = vehiclesForManagersDTO;
            }
        }
        return resultVehicle;
    }

    @PatchMapping("/edit/{id}")
    public List<VehiclesForManagersDTO> update(@RequestBody VehiclesDTO vehiclesDTO,
                                               @PathVariable("id") int id) {
        //изменяем поля машины
        vehicleService.updateVehicle(id, vehiclesDTO);
        return getAllVehicles();
    }

    @DeleteMapping("/delete/{id}")
    public List<VehiclesForManagersDTO> deleteVehicle(@PathVariable int id) {
        // Вызываем метод сервиса для удаления транспортного средства
        vehicleService.delete(id);
        return getAllVehicles();
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
    public List<EnterprisesDTO> getAllEnterprises(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails userDetails = (PersonDetails) authentication.getPrincipal();
        Integer managerId = userDetails.getId();
        return managersService.getEnterpriseIdsByPersonId(managerId);
    }

    @PostMapping("/enterprises/new")
    public List <EnterprisesDTO> createNewEnterprise(@RequestBody EnterprisesDTO enterprisesDTO, BindingResult bindingResult) {
        // Создание нового предприятия //вернулся
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


    @GetMapping("/enterprise/{id}")
    public EnterprisesDTO getEnterpriseById(@PathVariable("id") int id) {
        // Получаем существующее предприятие по ID
        List<EnterprisesDTO> allEnterprises = getAllEnterprises();
        EnterprisesDTO resultEnterprise = new EnterprisesDTO();
        for (EnterprisesDTO enterprisesDTO : allEnterprises) {
            if (enterprisesDTO.getId() == id) {
                resultEnterprise = enterprisesDTO;
            }
        }
        return resultEnterprise;
    }


    @PatchMapping("/enterprises/edit/{id}")
    public List<EnterprisesDTO> updateEnterprise(@RequestBody EnterprisesDTO enterprisesDTO,
                                               @PathVariable("id") int id) {
        //изменяем поля предприятия
        enterprisesService.updateEnterprise(id, enterprisesDTO);
        return getAllEnterprises();
    }

    @DeleteMapping("/delete/enterprise/{id}")
    public List<EnterprisesDTO> deleteEnterprise(@PathVariable int id) {
        // Вызываем метод сервиса для удаления транспортного средства
        enterprisesService.deleteEnterprise(id);
        return getAllEnterprises();
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

    private VehiclesDTO convertToVehicleDTO(Vehicles vehicles) {
        return modelMapper.map(vehicles, VehiclesDTO.class);
    }
}
