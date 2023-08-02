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
@RequestMapping("/car_brand")
public class CarBrandController {

    private final CarBrandService carBrandService;

    @Autowired
    public CarBrandController(CarBrandService carBrandService) {
        this.carBrandService = carBrandService;
    }

    //Показать все CarBrand (done)
    @GetMapping()
    public String index(Model model) {
        List<CarBrandDTO> carBrandsDTO = carBrandService.getAllCarBrands();
        model.addAttribute("carBrandsDTO", carBrandsDTO);
        return "/car_brand";
    }

    //Добавить новый CarBrand (ссылка Add_New_Car_Brand на стр car_brand) (done)
    @GetMapping("/create_car_brand")
    public String showCreateCarBrandForm(Model model) {
        CarBrandDTO carBrandDTO = new CarBrandDTO();
        model.addAttribute("carBrandDTO", carBrandDTO);
        List<BrandName> brandNamesList = carBrandService.getAllBrandNames();
        List<CarType> carTypeList = carBrandService.getAllCarTypes();
        model.addAttribute("brandNames", brandNamesList);
        model.addAttribute("carTypes", carTypeList);
        return "create_car_brand";
    }

    //Создать CаrBrand (кнопка Create на стр edit_car_brand)) (done)
    @PostMapping()
    public String create(@ModelAttribute("carBrandDTO") CarBrandDTO carBrandDTO) {
        carBrandService.saveCarBrand(carBrandDTO);
        return "redirect:/car_brand";
    }

    //Переход на форму редактирования edit_car_brand по id (кнопка Edit на стр car_brand) (done)
    @GetMapping("/edit_car_brand/{id}")
    public String edit(Model model, @PathVariable("id") int id) {
        CarBrandDTO carBrandDTO = carBrandService.getCarBrandById(id);
        model.addAttribute("carBrandDTO", carBrandDTO);
        return "edit_car_brand";
    }

    //Обновить CarBrand по id (кнопка Save на стр edit_car_brand) (done)
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("carBrandDTO") CarBrandDTO carBrandDTO, @PathVariable("id") int id) {
        carBrandService.updateCarBrand(id, carBrandDTO);
        return "redirect:/car_brand";
    }

    //Удалить CarBrand по id (кнопка Delete на стр car_brand) (done)
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        carBrandService.deleteCarBrand(id);
        return "redirect:/car_brand";
    }
}
