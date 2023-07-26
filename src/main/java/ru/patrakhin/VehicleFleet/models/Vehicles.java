package ru.patrakhin.VehicleFleet.models;


import javax.persistence.*;

@Entity
@Table(name = "vehicle")
public class Vehicles {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number_vehicle")
    private String numberVehicle;

    @Column(name = "price")
    private double price;

    @Column(name = "year_of_manufacture")
    private int yearOfManufacture;

    @Column(name = "mileage")
    private int mileage;

    @Enumerated(EnumType.STRING)
    private EquipmentType equipmentType;

    public Vehicles(){}

    public Vehicles(String numberVehicle, double price, int yearOfManufacture, int mileage, EquipmentType equipmentType) {
        this.numberVehicle = numberVehicle;
        this.price = price;
        this.yearOfManufacture = yearOfManufacture;
        this.mileage = mileage;
        this.equipmentType = equipmentType;
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