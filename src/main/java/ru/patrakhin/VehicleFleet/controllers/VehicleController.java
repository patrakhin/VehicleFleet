package ru.patrakhin.VehicleFleet.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.patrakhin.VehicleFleet.models.CarBrand;
import ru.patrakhin.VehicleFleet.services.CarBrandService;
import ru.patrakhin.VehicleFleet.services.VehicleService;

import java.util.List;


@Controller
public class VehicleController {

    private final VehicleService vehicleService;

    private final CarBrandService carBrandService;

    @Autowired
    public VehicleController(VehicleService vehicleService, CarBrandService carBrandService) {
        this.vehicleService = vehicleService;
        this.carBrandService = carBrandService;
    }

    @GetMapping("/index")
    public String getAllVehicles(Model model){
        model.addAttribute("allVehicles", vehicleService.findAll());
        return "index";
    }

    @GetMapping("/car_brand")
    public String getAllCarBrands(Model model){
        List<CarBrand> carBrands = carBrandService.findAll();
        model.addAttribute("carBrands", carBrands);
        return "car_brand";
    }
}
