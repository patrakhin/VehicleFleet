package ru.patrakhin.VehicleFleet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.patrakhin.VehicleFleet.dto.DriversDTO;
import ru.patrakhin.VehicleFleet.models.Drivers;
import ru.patrakhin.VehicleFleet.models.VehicleDrivers;
import ru.patrakhin.VehicleFleet.repositories.DriverRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class DriversService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriversService(DriverRepository driverRepository){this.driverRepository = driverRepository;}

    public List<DriversDTO> getAllDrivers(){
        List<Drivers> driversList = driverRepository.findAll();
        return driversList.stream()
                .map(DriversDTO::new)
                .collect(Collectors.toList());
    }
    public List<Drivers> getAllDriversByEnterpriseId(int id){
        return driverRepository.findAllByEnterprises_Id(id);
    }

    public DriversDTO getDriverById(int id){
        Optional<Drivers> drivers = driverRepository.findById(id);
        return drivers.map(this::convertToDTO).orElse(null);
    }

    /*public List<VehicleDrivers> getListDrivers(){
        List<VehicleDrivers> vehicleDrivers = driverRepository.getVehicleDrivers();
    }
*/
    @Transactional
    public void saveDriver(DriversDTO driversDTO){
        Drivers drivers = convertToEntity(driversDTO);
        driverRepository.save(drivers);
    }

    @Transactional
    public void updateDriver(int id, DriversDTO updateDriverDTO){
        Drivers driver = driverRepository.findById(id).orElse(null);
        if (driver != null){
            updateEntityFromDTO(driver, updateDriverDTO);
            driverRepository.save(driver);
        }
    }

    @Transactional
    public void deleteDriver(int id){
        driverRepository.deleteById(id);
    }

    private Drivers convertToEntity(DriversDTO driversDTO){
        Drivers drivers = new Drivers();
        updateEntityFromDTO(drivers, driversDTO);
        return drivers;
    }

    private void updateEntityFromDTO(Drivers drivers, DriversDTO driversDTO) {
        drivers.setDriverName(driversDTO.getDriverName());
        drivers.setDriverAddress(driversDTO.getDriverAddress());
        drivers.setDriverPhone(driversDTO.getDriverPhone());
    }

    private DriversDTO convertToDTO(Drivers drivers){
        return new DriversDTO(
                drivers.getId(),
                drivers.getDriverName(),
                drivers.getDriverAddress(),
                drivers.getDriverPhone(),
                drivers.getEnterprises().getId()
        );

    }
}
