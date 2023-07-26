package ru.patrakhin.VehicleFleet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.patrakhin.VehicleFleet.models.Vehicles;
import ru.patrakhin.VehicleFleet.repositories.VehicleRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicles> findAll(){
        return vehicleRepository.findAll();
    }

    public Vehicles findOne(int id) {
        Optional<Vehicles> foundPerson = vehicleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Vehicles vehicles) {
        vehicleRepository.save(vehicles);
    }

    @Transactional
    public void update(int id, Vehicles updatedVehicles) {
        updatedVehicles.setId(id);
        vehicleRepository.save(updatedVehicles);
    }

    @Transactional
    public void delete(int id) {
        vehicleRepository.deleteById(id);
    }
}
