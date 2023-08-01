package ru.patrakhin.VehicleFleet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.patrakhin.VehicleFleet.dto.CarBrandDTO;
import ru.patrakhin.VehicleFleet.dto.VehiclesDTO;
import ru.patrakhin.VehicleFleet.services.CarBrandService;
import ru.patrakhin.VehicleFleet.services.VehicleService;

import java.util.ArrayList;
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

    // Показать страницу со списком всех Vehicles
    @GetMapping("/index")
    public String showVehiclesPage(Model model) {
        List<VehiclesDTO> vehiclesDTOList = vehicleService.getAllVehicles();
        model.addAttribute("vehiclesList", vehiclesDTOList);
        return "index";
    }

    // Показать страницу со списком всех Car Brands
    @GetMapping("/car_brand")
    public String showCarBrandsPage(Model model) {
        List<CarBrandDTO> carBrandDTOList = carBrandService.getAllCarBrands();
        model.addAttribute("carBrandsList", carBrandDTOList);
        return "car_brand";
    }

    // Показать страницу для создания нового Vehicle
    @GetMapping("/start_page/create_vehicle")
    public String showCreateVehiclePage(Model model) {
        List<CarBrandDTO> carBrandDTOList = carBrandService.getAllCarBrands();
        model.addAttribute("carBrands", carBrandDTOList);
        model.addAttribute("vehicle", new VehiclesDTO());
        return "create_vehicle";
    }

    // Показать страницу для создания нового Car Brand
    @GetMapping("/start_page/create_car_brand")
    public String showCreateCarBrandPage(Model model) {
        model.addAttribute("carBrand", new CarBrandDTO());
        return "create_car_brand";
    }

    // Создать новый Car Brand
    @PostMapping("/start_page/car_brand")
    public String createCarBrand(@ModelAttribute CarBrandDTO carBrandDTO) {
        carBrandService.saveCarBrand(carBrandDTO);
        return "redirect:/car_brand";
    }

    // Создать новый Vehicle
    @PostMapping("/start_page/create_vehicle")
    public String createVehicle(@ModelAttribute VehiclesDTO vehiclesDTO) {
        vehicleService.saveVehicle(vehiclesDTO);
        return "redirect:/index";
    }

    // Показать страницу для редактирования Vehicle
    @GetMapping("/edit_vehicle/{id}")
    public String showEditVehiclePage(@PathVariable int id, Model model) {
        VehiclesDTO vehicleDTO = vehicleService.findVehicleById(id);
        List<CarBrandDTO> carBrandDTOList = carBrandService.getAllCarBrands();
        model.addAttribute("vehicle", vehicleDTO);
        model.addAttribute("carBrands", carBrandDTOList);
        return "edit_vehicle";
    }

    // Обновить Vehicle
    @PostMapping("/save_vehicle")
    public String saveVehicle(@ModelAttribute VehiclesDTO vehiclesDTO) {
        vehicleService.updateVehicle(vehiclesDTO);
        return "redirect:/index";
    }

    // Удалить Vehicle
    @GetMapping("/delete_vehicle/{id}")
    public String deleteVehicle(@PathVariable int id) {
        vehicleService.deleteVehicleById(id);
        return "redirect:/index";
    }

    // Показать страницу для редактирования Car Brand
    @GetMapping("/edit_car_brand/{id}")
    public String showEditCarBrandPage(@PathVariable int id, Model model) {
        CarBrandDTO carBrandDTO = carBrandService.findCarBrandById(id);
        model.addAttribute("carBrand", carBrandDTO);
        return "edit_car_brand";
    }

    // Обновить Car Brand
    @PostMapping("/save_car_brand")
    public String saveCarBrand(@ModelAttribute CarBrandDTO carBrandDTO) {
        carBrandService.updateCarBrand(carBrandDTO);
        return "redirect:/car_brand";
    }

    // Удалить Car Brand
    @GetMapping("/delete_car_brand/{id}")
    public String deleteCarBrand(@PathVariable int id) {
        carBrandService.deleteCarBrandById(id);
        return "redirect:/car_brand";
    }

    // Показать стартовую страницу
    @GetMapping("/start_page")
    public String showStartPage() {
        return "start_page";
    }

    // Показать страницу с информацией об авторе
    @GetMapping("/about")
    public String showAboutPage() {
        return "about";
    }
}
