package ru.patrakhin.VehicleFleet.models;

import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

@Entity
@Table(name = "managers")
public class Managers {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "password")
    private String password;

    @ManyToOne
    @JoinColumn (name = "enterprise_id", referencedColumnName = "id")
    private Enterprises enterprises;

    public Managers(){}

    public Managers(String name, String password, Enterprises enterprises) {
        this.name = name;
        this.password = password;
        this.enterprises = enterprises;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Enterprises getEnterprises() {
        return enterprises;
    }

    public void setEnterprises(Enterprises enterprises) {
        this.enterprises = enterprises;
    }

    @Override
    public String toString() {
        return "Managers{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
