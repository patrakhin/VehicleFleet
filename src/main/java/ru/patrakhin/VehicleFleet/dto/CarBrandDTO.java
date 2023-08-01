package ru.patrakhin.VehicleFleet.dto;

public class CarBrandDTO {

    private Long id;
    private String brandName;
    private String carType;
    private double fuelTankVolume;
    private double carryingCapacity;
    private int numberOfSeats;
    private int maxSpeed;

    // Конструкторы (пустой и с аргументами)
    public CarBrandDTO() {
    }

    public CarBrandDTO(String brandName, String carType, double fuelTankVolume, double carryingCapacity,
                       int numberOfSeats, int maxSpeed) {
        this.brandName = brandName;
        this.carType = carType;
        this.fuelTankVolume = fuelTankVolume;
        this.carryingCapacity = carryingCapacity;
        this.numberOfSeats = numberOfSeats;
        this.maxSpeed = maxSpeed;
    }

    // Геттеры и сеттеры для всех полей
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

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
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

    // Метод toString() для отладки и вывода содержимого объекта
    @Override
    public String toString() {
        return "CarBrandDTO{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", carType='" + carType + '\'' +
                ", fuelTankVolume=" + fuelTankVolume +
                ", carryingCapacity=" + carryingCapacity +
                ", numberOfSeats=" + numberOfSeats +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}
