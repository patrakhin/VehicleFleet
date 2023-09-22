package ru.patrakhin.VehicleFleet.dto;


import ru.patrakhin.VehicleFleet.models.EquipmentType;
import ru.patrakhin.VehicleFleet.models.Vehicles;

public class VehicleAdminDTO {
    private int id;
    private String numberVehicle;
    private double price;
    private int yearOfManufacture;
    private int mileage;
    private EquipmentType equipmentType;
    private int carBrand;
    private String enterprises;

    public VehicleAdminDTO(Vehicles vehicles) {
        this.id = vehicles.getId();
        this.numberVehicle = vehicles.getNumberVehicle();
        this.price = vehicles.getPrice();
        this.yearOfManufacture = vehicles.getYearOfManufacture();
        this.mileage = vehicles.getMileage();
        this.equipmentType = vehicles.getEquipmentType();
        this.carBrand = vehicles.getCarBrand().getId();
        this.enterprises = vehicles.getEnterprises().getEnterpriseName();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberVehicle() {
        return numberVehicle;
    }

    public void setNumberVehicle(String numberVehicle) {
        this.numberVehicle = numberVehicle;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    public int getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(int carBrand) {
        this.carBrand = carBrand;
    }

    public String getEnterprises() {
        return enterprises;
    }

    public void setEnterprises(String enterprises) {
        this.enterprises = enterprises;
    }
}
