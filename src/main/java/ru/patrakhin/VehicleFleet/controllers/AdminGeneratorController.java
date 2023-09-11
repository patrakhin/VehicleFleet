package ru.patrakhin.VehicleFleet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.patrakhin.VehicleFleet.dto.VehicleGenerationDTO;
import ru.patrakhin.VehicleFleet.services.VehicleGenerationService;

@RestController
@RequestMapping("/admin")
public class AdminGeneratorController {
    private final VehicleGenerationService vehicleGenerationService;

    @Autowired
    public AdminGeneratorController(VehicleGenerationService vehicleGenerationService) {
        this.vehicleGenerationService = vehicleGenerationService;
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
}
