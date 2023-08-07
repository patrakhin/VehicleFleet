package ru.patrakhin.VehicleFleet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.patrakhin.VehicleFleet.dto.CarBrandDTO;
import ru.patrakhin.VehicleFleet.dto.VehiclesDTO;
import ru.patrakhin.VehicleFleet.models.BrandName;
import ru.patrakhin.VehicleFleet.models.CarBrand;
import ru.patrakhin.VehicleFleet.models.EquipmentType;
import ru.patrakhin.VehicleFleet.models.Vehicles;
import ru.patrakhin.VehicleFleet.repositories.VehicleRepository;

import java.util.Arrays;
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

    private VehiclesDTO convertToDTO(Vehicles vehicle) {
        VehiclesDTO dto = new VehiclesDTO();

        dto.setId(vehicle.getId());
        dto.setEquipmentType(vehicle.getEquipmentType());
        dto.setMileage(vehicle.getMileage());
        dto.setNumberVehicle(vehicle.getNumberVehicle());
        dto.setPrice(vehicle.getPrice());
        dto.setYearOfManufacture(vehicle.getYearOfManufacture());
        return dto;
    }

    public VehiclesDTO getVehicleById(int id) {
        Optional<Vehicles> vehicles = vehicleRepository.findById(id);
        return vehicles.map(this::convertToDTO).orElse(null);
    }


    @Transactional
    public void save(Vehicles vehicles) {
        vehicleRepository.save(vehicles);
    }


    private void updateEntityFromDTO(Vehicles vehicle, VehiclesDTO vehiclesDTO) {
        vehicle.setEquipmentType(vehiclesDTO.getEquipmentType());
        vehicle.setMileage(vehiclesDTO.getMileage());
        vehicle.setNumberVehicle(vehiclesDTO.getNumberVehicle());
        vehicle.setPrice(vehiclesDTO.getPrice());
        vehicle.setYearOfManufacture(vehiclesDTO.getYearOfManufacture());
    }

    @Transactional
    public void updateVehicle(int id, VehiclesDTO updatedVehiclesDTO) {
        Vehicles vehicle = vehicleRepository.findById(id).orElse(null);
        if (vehicle != null) {
            updateEntityFromDTO(vehicle, updatedVehiclesDTO);
            vehicleRepository.save(vehicle);
        }
    }

    @Transactional
    public void delete(int id) {
        vehicleRepository.deleteById(id);
    }

    @Transactional
    public void addVehicle(VehiclesDTO vehicleDTO) {
        Vehicles newVehicle = new Vehicles();

        newVehicle.setCarBrand(vehicleDTO.toVehicles().getCarBrand());
        newVehicle.setEquipmentType(vehicleDTO.getEquipmentType());
        newVehicle.setMileage(vehicleDTO.getMileage());
        newVehicle.setNumberVehicle(vehicleDTO.getNumberVehicle());
        newVehicle.setPrice(vehicleDTO.getPrice());
        newVehicle.setYearOfManufacture(vehicleDTO.getYearOfManufacture());

        vehicleRepository.save(newVehicle);
    }

    public List<EquipmentType> getAllEquipmentType() {
        return Arrays.asList(EquipmentType.values());
    }

}