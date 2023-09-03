package ru.patrakhin.VehicleFleet.dto;

import ru.patrakhin.VehicleFleet.models.CarBrand;
import ru.patrakhin.VehicleFleet.models.EquipmentType;
import ru.patrakhin.VehicleFleet.models.Vehicles;
import ru.patrakhin.VehicleFleet.repositories.CarBrandRepository;
import ru.patrakhin.VehicleFleet.services.CarBrandService;

public class VehiclesForManagersDTO {
    private int id;
    private String numberVehicle;
    private double price;
    private int yearOfManufacture;
    private int mileage;
    private EquipmentType equipmentType;
    private String carBrandName;
    private String enterprisesName;

    public VehiclesForManagersDTO(){}

    public VehiclesForManagersDTO(int id, String numberVehicle, double price, int yearOfManufacture,
                                  int mileage, EquipmentType equipmentType, String carBrandName, String enterprisesName) {
        this.id = id;
        this.numberVehicle = numberVehicle;
        this.price = price;
        this.yearOfManufacture = yearOfManufacture;
        this.mileage = mileage;
        this.equipmentType = equipmentType;
        this.carBrandName = carBrandName;
        this.enterprisesName = enterprisesName;
    }

    public VehiclesForManagersDTO(Vehicles vehicles) {
        this.id = vehicles.getId();
        this.numberVehicle = vehicles.getNumberVehicle();
        this.price = vehicles.getPrice();
        this.yearOfManufacture = vehicles.getYearOfManufacture();
        this.mileage = vehicles.getMileage();
        this.equipmentType = vehicles.getEquipmentType();
        this.carBrandName = String.valueOf(vehicles.getCarBrand().getBrandName());
        this.enterprisesName = vehicles.getEnterprises().getEnterpriseName();
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

    public String getCarBrandName() {
        return carBrandName;
    }

    public void setCarBrandName(String carBrandName) {
        this.carBrandName = carBrandName;
    }

    public String getEnterprisesName() {
        return enterprisesName;
    }

    public void setEnterprisesName(String enterprisesName) {
        this.enterprisesName = enterprisesName;
    }
}
