package ru.patrakhin.VehicleFleet.dto;

import ru.patrakhin.VehicleFleet.models.Drivers;
import ru.patrakhin.VehicleFleet.models.Vehicles;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

public class EnterprisesDTO {

    private int id;

    private String enterpriseName;

    private String enterpriseAddress;

    private String enterprisePhone;

    //private List<Drivers> drivers;

    //private List<Vehicles> vehicles;

    public EnterprisesDTO(){}

    public EnterprisesDTO(int id, String enterpriseName, String enterpriseAddress, String enterprisePhone) {
        this.id = id;
        this.enterpriseName = enterpriseName;
        this.enterpriseAddress = enterpriseAddress;
        this.enterprisePhone = enterprisePhone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnterpriseName() {
        return enterpriseName;
    }

    public void setEnterpriseName(String enterpriseName) {
        this.enterpriseName = enterpriseName;
    }

    public String getEnterpriseAddress() {
        return enterpriseAddress;
    }

    public void setEnterpriseAddress(String enterpriseAddress) {
        this.enterpriseAddress = enterpriseAddress;
    }

    public String getEnterprisePhone() {
        return enterprisePhone;
    }

    public void setEnterprisePhone(String enterprisePhone) {
        this.enterprisePhone = enterprisePhone;
    }

/*    public List<Drivers> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Drivers> drivers) {
        this.drivers = drivers;
    }

    public List<Vehicles> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicles> vehicles) {
        this.vehicles = vehicles;
    }*/

    @Override
    public String toString() {
        return "EnterprisesDTO{" +
                "id=" + id +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", enterpriseAddress='" + enterpriseAddress + '\'' +
                ", enterprisePhone='" + enterprisePhone + '\'' +
                '}';
    }
}
