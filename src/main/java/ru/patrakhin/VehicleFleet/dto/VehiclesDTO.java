package ru.patrakhin.VehicleFleet.dto;


import ru.patrakhin.VehicleFleet.models.CarBrand;
import ru.patrakhin.VehicleFleet.models.EquipmentType;
import ru.patrakhin.VehicleFleet.models.Vehicles;
import ru.patrakhin.VehicleFleet.models.Enterprises;

import java.util.List;


public class VehiclesDTO {

    private int id;
    private String numberVehicle;
    private double price;
    private int yearOfManufacture;
    private int mileage;
    private EquipmentType equipmentType;
    private int carBrand;
    private int enterprises;

    public VehiclesDTO(){}

    public VehiclesDTO(int id, String numberVehicle, double price, int yearOfManufacture, int mileage,
                       EquipmentType equipmentType) {
        this.id = id;
        this.numberVehicle = numberVehicle;
        this.price = price;
        this.yearOfManufacture = yearOfManufacture;
        this.mileage = mileage;
        this.equipmentType = equipmentType;
    }

    public VehiclesDTO(Vehicles vehicles) {
        this.id = vehicles.getId();
        this.numberVehicle = vehicles.getNumberVehicle();
        this.price = vehicles.getPrice();
        this.yearOfManufacture = vehicles.getYearOfManufacture();
        this.mileage = vehicles.getMileage();
        this.equipmentType = vehicles.getEquipmentType();
        this.carBrand = vehicles.getCarBrand().getId();
        this.enterprises = vehicles.getEnterprises().getId();
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

    public void setCarBrand(CarBrand carBrand) {
        this.carBrand = carBrand.getId();
    }

    public void setCarBrand(int carBrand) {
        this.carBrand = carBrand;
    }

    public int getEnterprises() {
        return enterprises;
    }


    public void setEnterprises(int enterprises) {
        this.enterprises = enterprises;
    }

 /*   public List<CarBrandDTO> getCarBrands() {
        return carBrands;
    }*/

    //дополнительные методы
    private List<CarBrandDTO> carBrands;

   /* public List<CarBrandDTO> getCarBrands() {
        return carBrands;
    }*/

    public void setCarBrands(List<CarBrandDTO> carBrands) {
        this.carBrands = carBrands;
    }

    public Vehicles toVehicles() {
        Vehicles vehicles = new Vehicles();
        vehicles.setId(id);
        vehicles.setNumberVehicle(numberVehicle);
        vehicles.setPrice(price);
        vehicles.setYearOfManufacture(yearOfManufacture);
        vehicles.setMileage(mileage);
        vehicles.setEquipmentType(equipmentType);
        return vehicles;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", numberVehicle=" + numberVehicle +
                ", price=" + price +
                ", yearOfManufacture=" + yearOfManufacture +
                ", mileage=" + mileage +
                ", equipmentType=" + equipmentType +
                '}';
    }
}
