package ru.patrakhin.VehicleFleet.models;


import javax.persistence.*;

@Entity
@Table(name = "vehicle_drivers")
public class VehicleDrivers {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicles vehicles;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private Drivers drivers;

    @Column(name = "is_active")
    private boolean isActive;

    public VehicleDrivers(){}

    public VehicleDrivers(Vehicles vehicles, Drivers drivers, boolean isActive) {
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
        return "VehicleDrivers{" +
                "id=" + id +
                ", vehicles=" + vehicles +
                ", drivers=" + drivers +
                ", isActive=" + isActive +
                '}';
    }
}
