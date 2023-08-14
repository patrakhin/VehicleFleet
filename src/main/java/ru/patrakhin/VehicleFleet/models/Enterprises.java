package ru.patrakhin.VehicleFleet.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "enterprises")
public class Enterprises {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "enterprise_name")
    private String enterpriseName;

    @Column(name = "enterprise_address")
    private String enterpriseAddress;

    @Column(name = "enterprise_phone")
    private String enterprisePhone;

    @OneToMany(mappedBy = "enterprises")
    private List<Drivers> drivers;

    @OneToMany(mappedBy = "enterprises")
    private List<Vehicles> vehicles;

    @OneToMany(mappedBy = "enterprises")
    private List<Managers> managers;

    public Enterprises(){}

    public Enterprises(String enterpriseName, String enterpriseAddress, String enterprisePhone) {
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

    public List<Drivers> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Drivers> drivers) {
        this.drivers = drivers;
    }

    @Override
    public String toString() {
        return "Enterprises{" +
                "id=" + id +
                ", enterpriseName='" + enterpriseName + '\'' +
                ", enterpriseAddress='" + enterpriseAddress + '\'' +
                ", enterprisePhone='" + enterprisePhone + '\'' +
                '}';
    }
}
