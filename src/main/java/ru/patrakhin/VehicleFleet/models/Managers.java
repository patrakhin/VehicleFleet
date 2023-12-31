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

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person person;

    @ManyToOne
    @JoinColumn (name = "enterprise_id", referencedColumnName = "id")
    private Enterprises enterprises;

    public Managers(){}

    public Managers(Person person, Enterprises enterprises) {
        this.person = person;
        this.enterprises = enterprises;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
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
                ", person=" + person +
                ", enterprises=" + enterprises +
                '}';
    }
}
