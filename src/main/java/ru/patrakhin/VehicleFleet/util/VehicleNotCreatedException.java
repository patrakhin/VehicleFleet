package ru.patrakhin.VehicleFleet.util;

public class VehicleNotCreatedException extends RuntimeException{
    public VehicleNotCreatedException(String message){
        super(message);
    }
}
