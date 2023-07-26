package ru.patrakhin.VehicleFleet.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.patrakhin.VehicleFleet.services.VehicleService;


@Controller
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/index")
    public String getAllVehicles(Model model){
        model.addAttribute("allVehicles", vehicleService.findAll());
        return "index";
    }
}
