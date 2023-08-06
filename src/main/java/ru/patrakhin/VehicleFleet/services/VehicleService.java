package ru.patrakhin.VehicleFleet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.patrakhin.VehicleFleet.dto.VehiclesDTO;
import ru.patrakhin.VehicleFleet.models.Vehicles;
import ru.patrakhin.VehicleFleet.repositories.VehicleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<VehiclesDTO> getAllVehicles() {
        List<Vehicles> vehiclesList = vehicleRepository.findAll();
        return vehiclesList.stream()
                .map(VehiclesDTO::new)
                .collect(Collectors.toList());
    }

    public Vehicles findOne(int id) {
        Optional<Vehicles> foundVehicle = vehicleRepository.findById(id);
        return foundVehicle.orElse(null);
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

    @Transactional
    public void addVehicle(VehiclesDTO vehicleDTO) {
        Vehicles newVehicle = new Vehicles();

        newVehicle.setCarBrand(vehicleDTO.getCarBrand());
        newVehicle.setEquipmentType(vehicleDTO.getEquipmentType());
        newVehicle.setMileage(vehicleDTO.getMileage());
        newVehicle.setNumberVehicle(vehicleDTO.getNumberVehicle());
        newVehicle.setPrice(vehicleDTO.getPrice());
        newVehicle.setYearOfManufacture(vehicleDTO.getYearOfManufacture());

        vehicleRepository.save(newVehicle);
    }
}