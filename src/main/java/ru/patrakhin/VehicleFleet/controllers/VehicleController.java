package ru.patrakhin.VehicleFleet.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.patrakhin.VehicleFleet.models.BrandName;
import ru.patrakhin.VehicleFleet.models.CarBrand;
import ru.patrakhin.VehicleFleet.models.CarType;
import ru.patrakhin.VehicleFleet.models.Vehicles;
import ru.patrakhin.VehicleFleet.services.CarBrandService;
import ru.patrakhin.VehicleFleet.services.VehicleService;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/start_page")
public class VehicleController {

    private final VehicleService vehicleService;
    private final CarBrandService carBrandService;

    @Autowired
    public VehicleController(VehicleService vehicleService, CarBrandService carBrandService) {
        this.vehicleService = vehicleService;
        this.carBrandService = carBrandService;
    }

    @GetMapping()
    public String showStartPage() {
        return "start_page";
    }

    @GetMapping("/index") //пока не редактировал
    public String viewTableVehicles(Model model){
        List<Vehicles> vehicles = vehicleService.findAll();
        model.addAttribute("allVehicles", vehicles);
        return "index";
    }

    @GetMapping("/car_brand")
    public String viewTableCarBrands(Model model){
        List<CarBrand> carBrands = carBrandService.findAll();
        model.addAttribute("carBrands", carBrands);
        return "car_brand";
    }

    @GetMapping("/create_car_brand")
    public String showCreateCarBrandPage(Model model) {
        BrandName[] brandNames = BrandName.values();
        CarType[] carTypes = CarType.values();

        model.addAttribute("brandNames", brandNames);
        model.addAttribute("carTypes", carTypes);

        return "create_car_brand";
    }

    @PostMapping("/create_car_brand")
    public String saveCarBrand(@ModelAttribute("carBrand") CarBrand carBrand) {
        carBrandService.save(carBrand);
        return "redirect:/car_brand";
    }


    @GetMapping("/edit_car_brand/{id}")
    public String showEditCarBrandPage(@PathVariable int id, Model model) {
        CarBrand carBrand = carBrandService.findOne(id);
        model.addAttribute("carBrand", carBrand);
        return "edit_car_brand";
    }

    @GetMapping("/edit_vehicle/{id}")
    public String showEditVehiclePage(@PathVariable int id, Model model) {
        Vehicles vehicle = vehicleService.findOne(id);
        model.addAttribute("vehicle", vehicle);
        List<CarBrand> carBrands = carBrandService.findAll();
        model.addAttribute("carBrands", carBrands);
        return "edit_vehicle";
    }




    @GetMapping("/create_vehicle")
    public String showCreateVehiclePage(Model model) {
        model.addAttribute("vehicle", new Vehicles());
        List<CarBrand> carBrands = carBrandService.findAll();
        model.addAttribute("carBrands", carBrands);
        return "create_vehicle";
    }

    @PostMapping("/save_vehicle")
    public String saveVehicle(@ModelAttribute("vehicle") Vehicles vehicle) {
        vehicleService.save(vehicle);
        return "redirect:/index";
    }

    @GetMapping("/delete_car_brand/{id}")
    public String deleteCarBrand(@PathVariable int id) {
        carBrandService.delete(id);
        return "redirect:/car_brand";
    }

    @GetMapping("/delete_vehicle/{id}")
    public String deleteVehicle(@PathVariable int id) {
        vehicleService.delete(id);
        return "redirect:/index";
    }


/*    @GetMapping("/start_page")
    public String showStartPage(Model modelForCarBrand, Model modelForVehicles) {
        List<Vehicles> vehicles = vehicleService.findAll();
        List<CarBrand> carBrands = carBrandService.findAll();

        modelForCarBrand.addAttribute("carBrands", carBrands);
        modelForVehicles.addAttribute("vehicles", vehicles);

        return "start_page";
    }*/


}
