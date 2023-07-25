package ru.patrakhin.VehicleFleet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.patrakhin.VehicleFleet.models.Vehicle;
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

    public List<Vehicle> findAll(){
        return vehicleRepository.findAll();
    }

    public Vehicle findOne(int id) {
        Optional<Vehicle> foundPerson = vehicleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Transactional
    public void update(int id, Vehicle updatedVehicle) {
        updatedVehicle.setId(id);
        vehicleRepository.save(updatedVehicle);
    }

    @Transactional
    public void delete(int id) {
        vehicleRepository.deleteById(id);
    }
}
