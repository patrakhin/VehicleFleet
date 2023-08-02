package ru.patrakhin.VehicleFleet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.patrakhin.VehicleFleet.dto.VehiclesDTO;
import ru.patrakhin.VehicleFleet.services.CarBrandService;
import ru.patrakhin.VehicleFleet.services.VehicleService;

import java.util.List;


@Controller
@RequestMapping("/vehicle")
public class VehicleController {
    private final VehicleService vehicleService;
    /*private final CarBrandService carBrandService;*/

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping()
    public String showVehicle(Model model) {
        List<VehiclesDTO> vehiclesDTO = vehicleService.getAllVehicles();
        model.addAttribute("allVehiclesDTO", vehiclesDTO);
        return "vehicle";
    }

    /*@GetMapping("/add_vehicle")
    public String showAddVehicleForm(Model model) {
        VehiclesDTO vehiclesDTO = new VehiclesDTO();
        model.addAttribute("vehiclesDTO", vehiclesDTO);
        return "create_vehicle"; // Создайте страницу create_vehicle.html
    }

    @PostMapping("/add_vehicle")
    public String addVehicle(@ModelAttribute("vehiclesDTO") VehiclesDTO vehiclesDTO) {
        vehicleService.saveVehicle(vehiclesDTO);
        return "redirect:/start_page/index";
    }

    @GetMapping("/edit_vehicle/{id}")
    public String showEditVehicleForm(@PathVariable Long id, Model model) {
        VehiclesDTO vehiclesDTO = vehicleService.getVehicleById(id);
        model.addAttribute("vehiclesDTO", vehiclesDTO);
        return "edit_vehicle"; // Создайте страницу edit_vehicle.html
    }

    @PostMapping("/edit_vehicle/{id}")
    public String editVehicle(@PathVariable Long id, @ModelAttribute("vehiclesDTO") VehiclesDTO vehiclesDTO) {
        vehicleService.updateVehicle(id, vehiclesDTO);
        return "redirect:/start_page/index";
    }

    @GetMapping("/delete_vehicle/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return "redirect:/start_page/index";
    }*/
}
