package ru.patrakhin.VehicleFleet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.patrakhin.VehicleFleet.dto.CarBrandDTO;
import ru.patrakhin.VehicleFleet.dto.VehiclesDTO;
import ru.patrakhin.VehicleFleet.models.BrandName;
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

    @GetMapping("/car_brand")
    public String showCarBrandList(Model model) {
        List<CarBrandDTO> carBrands = carBrandService.getAllCarBrands();
        model.addAttribute("carBrands", carBrands);
        return "car_brand";
    }

    @GetMapping("/create_car_brand")
    public String showCreateCarBrandForm(Model model) {
        CarBrandDTO carBrandDTO = new CarBrandDTO();
        model.addAttribute("carBrandDTO", carBrandDTO);

        List<BrandName> brandNames = carBrandService.getAllBrandNames();
        model.addAttribute("brandNames", brandNames);

        List<CarType> carTypes = carBrandService.getAllCarTypes();
        model.addAttribute("carTypes", carTypes);

        return "create_car_brand";
    }

    @PostMapping("/create_car_brand")
    public String createCarBrand(@ModelAttribute("carBrandDTO") CarBrandDTO carBrandDTO) {
        carBrandService.saveCarBrand(carBrandDTO);
        return "redirect:/car_brand";
    }

    @GetMapping("/edit/{id}")
    public String showEditCarBrandForm(@PathVariable("id") int id, Model model) {
        CarBrandDTO carBrandDTO = carBrandService.getCarBrandById(id);
        model.addAttribute("carBrandDTO", carBrandDTO);
        return "car_brand/edit";
    }

    @PostMapping("/edit/{id}")
    public String editCarBrand(@PathVariable("id") int id, @ModelAttribute("carBrandDTO") CarBrandDTO carBrandDTO) {
        carBrandService.updateCarBrand(id, carBrandDTO);
        return "redirect:/car_brand/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteCarBrand(@PathVariable("id") int id) {
        carBrandService.deleteCarBrand(id);
        return "redirect:/car_brand/list";
    }
}
