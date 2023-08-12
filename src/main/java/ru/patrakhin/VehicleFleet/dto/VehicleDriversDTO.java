package ru.patrakhin.VehicleFleet.dto;

import ru.patrakhin.VehicleFleet.models.Drivers;
import ru.patrakhin.VehicleFleet.models.Vehicles;

public class VehicleDriversDTO {

    private int id;

    private Vehicles vehicles;

    private Drivers drivers;

    private boolean isActive;

    public VehicleDriversDTO(){}

    public VehicleDriversDTO(int id, Vehicles vehicles, Drivers drivers, boolean isActive) {
        this.id = id;
        this.vehicles = vehicles;
        this.drivers = drivers;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicles getVehicles() {
        return vehicles;
    }

    public void setVehicles(Vehicles vehicles) {
        this.vehicles = vehicles;
    }

    public Drivers getDrivers() {
        return drivers;
    }

    public void setDrivers(Drivers drivers) {
        this.drivers = drivers;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "VehicleDriversDTO{" +
                "id=" + id +
                ", vehicles=" + vehicles +
                ", drivers=" + drivers +
                ", isActive=" + isActive +
                '}';
    }
}
