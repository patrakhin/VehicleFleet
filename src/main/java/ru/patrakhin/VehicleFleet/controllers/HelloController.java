package ru.patrakhin.VehicleFleet.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.patrakhin.VehicleFleet.security.PersonDetails;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @GetMapping()
    public String sayHello() {
        return "hello user!";
    }

/*    @GetMapping("/showUserInfo")
    @ResponseBody*/
    @RequestMapping(value = "/showUserInfo",
            produces = "application/json",
            method= RequestMethod.GET)
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        System.out.println(personDetails.getPerson());
        return personDetails.getUsername();
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }
}
