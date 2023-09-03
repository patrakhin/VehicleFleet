package ru.patrakhin.VehicleFleet.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "car_brand")
public class CarBrand {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "brand_name")
    private BrandName brandName;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_type")
    private CarType carType;

    @Column(name = "fuel_tank_volume")
    private double fuelTankVolume;

    @Column(name = "carrying_capacity")
    private double carryingCapacity;

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    @Column(name = "max_speed")
    private int maxSpeed;

    @OneToMany(mappedBy = "carBrand")
    private List<Vehicles> vehicles;

    public CarBrand(){}

    public CarBrand(BrandName brandName, CarType carType, double fuelTankVolume, double carryingCapacity, int numberOfSeats, int maxSpeed) {
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
