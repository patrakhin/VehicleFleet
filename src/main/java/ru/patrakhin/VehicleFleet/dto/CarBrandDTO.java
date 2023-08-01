package ru.patrakhin.VehicleFleet.dto;

import ru.patrakhin.VehicleFleet.models.BrandName;
import ru.patrakhin.VehicleFleet.models.CarType;
import ru.patrakhin.VehicleFleet.models.Vehicles;

import javax.persistence.*;
import java.util.List;

public class CarBrandDTO {

    private int id;
    private BrandName brandName;
    private CarType carType;
    private double fuelTankVolume;
    private double carryingCapacity;
    private int numberOfSeats;
    private int maxSpeed;
    private List<Vehicles> vehicles;

    public CarBrandDTO(){}

    public CarBrandDTO(int id, BrandName brandName, CarType carType, double fuelTankVolume, double carryingCapacity, int numberOfSeats, int maxSpeed) {
        this.id = id;
        this.brandName = brandName;
        this.carType = carType;
        this.fuelTankVolume = fuelTankVolume;
        this.carryingCapacity = carryingCapacity;
        this.numberOfSeats = numberOfSeats;
        this.maxSpeed = maxSpeed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BrandName getBrandName() {
        return brandName;
    }

    public void setBrandName(BrandName brandName) {
        this.brandName = brandName;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public double getFuelTankVolume() {
        return fuelTankVolume;
    }

    public void setFuelTankVolume(double fuelTankVolume) {
        this.fuelTankVolume = fuelTankVolume;
    }

    public double getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public List<Vehicles> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicles> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "CarBrand{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", carType=" + carType +
                ", fuelTankVolume=" + fuelTankVolume +
                ", carryingCapacity=" + carryingCapacity +
                ", numberOfSeats=" + numberOfSeats +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}
