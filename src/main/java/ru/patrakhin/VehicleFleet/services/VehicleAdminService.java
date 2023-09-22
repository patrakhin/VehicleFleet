package ru.patrakhin.VehicleFleet.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.patrakhin.VehicleFleet.dto.VehicleAdminDTO;
import ru.patrakhin.VehicleFleet.models.Vehicles;
import ru.patrakhin.VehicleFleet.repositories.VehicleAdminRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleAdminService {
    private final VehicleAdminRepository vehicleAdminRepository;

    public VehicleAdminService(VehicleAdminRepository vehicleAdminRepository) {
        this.vehicleAdminRepository = vehicleAdminRepository;
    }

    public List<VehicleAdminDTO> getAdminVehicle(PageRequest pageRequest) {
        Page <Vehicles> vehiclesList = vehicleAdminRepository.findAll(pageRequest);
        return vehiclesList.stream()
                .map(VehicleAdminDTO::new)
                .collect(Collectors.toList());
    }
}
