package ru.patrakhin.VehicleFleet.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {

    @GetMapping(value = "/test", produces = "application/json")
    public Map<String, String> getString(){
        return Map.of("test", "Hello Volgograd!");
    }
}
