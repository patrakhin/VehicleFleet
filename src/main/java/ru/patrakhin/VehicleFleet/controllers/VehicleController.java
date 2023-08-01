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

    // Показать все CarBrand
    @GetMapping("/car_brand")
    public String showCarBrands(Model model) {
        List<CarBrandDTO> carBrandDTOList = carBrandService.getAllCarBrands();
        model.addAttribute("carBrandsList", carBrandDTOList);
        return "car_brand";
    }

    // Показать форму для создания нового CarBrand
    @GetMapping("/create_car_brand")
    public String showCreateCarBrandForm(Model model) {
        CarBrandDTO carBrandDTO = new CarBrandDTO();
        model.addAttribute("carBrandDTO", carBrandDTO);
        model.addAttribute("brandNames", BrandName.values());
        model.addAttribute("carTypes", CarType.values());
        return "create_car_brand";
    }

    // Создать новый CarBrand
    @PostMapping("/create_car_brand")
    public String createCarBrand(@ModelAttribute("carBrandDTO") CarBrandDTO carBrandDTO) {
        carBrandService.saveCarBrand(carBrandDTO);
        return "redirect:/start_page/car_brand";
    }

    // Показать форму для редактирования CarBrand
    @GetMapping("/edit_car_brand/{id}")
    public String showEditCarBrandForm(@PathVariable("id") int carBrandId, Model model) {
        CarBrandDTO carBrandDTO = carBrandService.getCarBrandById(carBrandId);
        model.addAttribute("carBrandDTO", carBrandDTO);
        model.addAttribute("brandNames", BrandName.values());
        model.addAttribute("carTypes", CarType.values());
        return "edit_car_brand";
    }

    // Обновить CarBrand
    @PostMapping("/edit_car_brand/{id}")
    public String updateCarBrand(@PathVariable("id") int carBrandId, @ModelAttribute("carBrandDTO") CarBrandDTO updatedCarBrandDTO) {
        carBrandService.updateCarBrand(carBrandId, updatedCarBrandDTO);
        return "redirect:/start_page/car_brand";
    }

    // Удалить CarBrand
    @GetMapping("/delete_car_brand/{id}")
    public String deleteCarBrand(@PathVariable("id") int carBrandId) {
        carBrandService.deleteCarBrand(carBrandId);
        return "redirect:/start_page/car_brand";
    }
}
