package ru.patrakhin.VehicleFleet.dto;

import ru.patrakhin.VehicleFleet.models.Drivers;
import ru.patrakhin.VehicleFleet.models.Enterprises;
import ru.patrakhin.VehicleFleet.models.VehicleDrivers;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

public class DriversDTO {

    private int id;

    private String driverName;

    private String driverAddress;

    private String driverPhone;

    //private Enterprises enterprises;

    private Integer enterprise_id;

    //private List<VehicleDrivers> vehicleDrivers;

    public DriversDTO(){}

    public DriversDTO(int id, String driverName, String driverAddress, String driverPhone, Integer enterprise_id) {
        this.id = id;
        this.driverName = driverName;
        this.driverAddress = driverAddress;
        this.driverPhone = driverPhone;
        this.enterprise_id = enterprise_id;
    }

    public DriversDTO(Drivers drivers){
        this.id = drivers.getId();
        this.driverName = drivers.getDriverName();
        this.driverAddress = drivers.getDriverAddress();
        this.driverPhone = drivers.getDriverPhone();
        if (drivers.getEnterprises() != null) {
            this.enterprise_id = drivers.getEnterprises().getId();
        }
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

/*    public Enterprises getEnterprises() {
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
    }*/

    public Integer getEnterprise_id() {
        return enterprise_id;
    }

    public void setEnterprise_id(Integer enterprise_id) {
        this.enterprise_id = enterprise_id;
    }

    @Override
    public String toString() {
        return "DriversDTO{" +
                "id=" + id +
                ", driverName='" + driverName + '\'' +
                ", driverAddress='" + driverAddress + '\'' +
                ", driverPhone='" + driverPhone + '\'' +
                '}';
    }
}
