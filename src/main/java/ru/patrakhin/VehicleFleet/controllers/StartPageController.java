package ru.patrakhin.VehicleFleet.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/start_page")
public class StartPageController {
    @GetMapping()
    public String showStartPage() {
        return "start_page";
    }
}
