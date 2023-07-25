package ru.patrakhin.VehicleFleet.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.patrakhin.VehicleFleet.models.Vehicle;
import ru.patrakhin.VehicleFleet.repositories.VehicleRepository;
import ru.patrakhin.VehicleFleet.services.VehicleService;

import java.util.List;


@Controller
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/index")
    public String getAllVehicles(Model model){
        model.addAttribute("vehicles", vehicleService.findAll());
        return "index";
    }
}
