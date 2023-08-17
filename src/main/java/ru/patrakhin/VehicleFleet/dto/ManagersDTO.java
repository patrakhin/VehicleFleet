package ru.patrakhin.VehicleFleet.dto;

import ru.patrakhin.VehicleFleet.models.Enterprises;
import ru.patrakhin.VehicleFleet.models.Person;


public class ManagersDTO {

    private int id;

    private Person person;

    private Enterprises enterprises;

    public ManagersDTO(){}

    public ManagersDTO(int id, Person person, Enterprises enterprises) {
        this.id = id;
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
        return "ManagersDTO{" +
                "id=" + id +
                ", person=" + person +
                ", enterprises=" + enterprises +
                '}';
    }
}
