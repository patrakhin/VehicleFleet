package ru.patrakhin.VehicleFleet.models;

import javax.persistence.*;

@Entity
@Table(name = "car_brand")
public class CarBrand {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private BrandName brandName;

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

    public CarBrand(){}

    public CarBrand(BrandName brandName, CarType carType, double fuelTankVolume, double carryingCapacity, int numberOfSeats, int maxSpeed) {
        this.brandName = brandName;
        this.carType = carType;
        this.fuelTankVolume = fuelTankVolume;
        this.carryingCapacity = carryingCapacity;
        this.numberOfSeats = numberOfSeats;
        this.maxSpeed = maxSpeed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
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
