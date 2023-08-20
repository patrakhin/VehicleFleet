package ru.patrakhin.VehicleFleet.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "drivers")
public class Drivers {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "driver_name")
    private String driverName;

    @Column(name = "driver_address")
    private String driverAddress;

    @Column(name = "driver_phone")
    private String driverPhone;

    @ManyToOne
    @JoinColumn (name = "enterprise_id", referencedColumnName = "id")
    private Enterprises enterprises;

    @OneToMany(mappedBy = "drivers")
    private List<VehicleDrivers> vehicleDrivers;

    public Drivers(){}

    public Drivers(String driverName, String driverAddress, String driverPhone, Enterprises enterprises) {
        this.driverName = driverName;
        this.driverAddress = driverAddress;
        this.driverPhone = driverPhone;
        this.enterprises = enterprises;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverAddress() {
        return driverAddress;
    }

    public void setDriverAddress(String driverAddress) {
        this.driverAddress = driverAddress;
    }

    public String getDriverPhone() {
        return driverPhone;
    }

    public void setDriverPhone(String driverPhone) {
        this.driverPhone = driverPhone;
    }

    public Enterprises getEnterprises() {
        return enterprises;
    }

    public void setEnterprises(Enterprises enterprises) {
        this.enterprises = enterprises;
    }

    public List<VehicleDrivers> getVehicleDrivers() {
        return vehicleDrivers;
    }

    public void setVehicleDrivers(List<VehicleDrivers> vehicleDrivers) {
        this.vehicleDrivers = vehicleDrivers;
    }


    @Override
    public String toString() {
        return "Drivers{" +
                "id=" + id +
                ", driverName='" + driverName + '\'' +
                ", driverAddress='" + driverAddress + '\'' +
                ", driverPhone='" + driverPhone + '\'' +
                '}';
    }
}
