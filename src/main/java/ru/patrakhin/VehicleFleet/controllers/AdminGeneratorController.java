package ru.patrakhin.VehicleFleet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.patrakhin.VehicleFleet.dto.VehicleAdminDTO;
import ru.patrakhin.VehicleFleet.dto.VehicleGenerationDTO;
import ru.patrakhin.VehicleFleet.dto.VehiclesDTO;
import ru.patrakhin.VehicleFleet.models.Vehicles;
import ru.patrakhin.VehicleFleet.repositories.VehicleAdminRepository;
import ru.patrakhin.VehicleFleet.services.VehicleAdminService;
import ru.patrakhin.VehicleFleet.services.VehicleGenerationService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminGeneratorController {
    private final VehicleGenerationService vehicleGenerationService;
    private final VehicleAdminRepository vehicleAdminRepository;

    private final VehicleAdminService vehicleAdminService;

    @Autowired
    public AdminGeneratorController(VehicleGenerationService vehicleGenerationService, VehicleAdminRepository vehicleAdminRepository,
                                     VehicleAdminService vehicleAdminService) {
        this.vehicleGenerationService = vehicleGenerationService;
        this.vehicleAdminRepository = vehicleAdminRepository;

        this.vehicleAdminService = vehicleAdminService;
    }

    @PostMapping("/generate-vehicles")
    public ResponseEntity<String> generateVehicles(@RequestBody VehicleGenerationDTO request) {
        try {
            vehicleGenerationService.generateVehicles(request);
            return ResponseEntity.ok("Машины успешно сгенерированы.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка при генерации машин.");
        }
    }

    @GetMapping("/test")
    public List<VehicleAdminDTO> getTest(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ){
        return vehicleAdminService.getAdminVehicle(PageRequest.of(page, size));
    }

}
