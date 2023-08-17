package ru.patrakhin.VehicleFleet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.patrakhin.VehicleFleet.dto.EnterprisesDTO;
import ru.patrakhin.VehicleFleet.dto.ManagersDTO;

import ru.patrakhin.VehicleFleet.dto.VehiclesDTO;
import ru.patrakhin.VehicleFleet.models.Enterprises;
import ru.patrakhin.VehicleFleet.models.Managers;
import ru.patrakhin.VehicleFleet.models.Vehicles;
import ru.patrakhin.VehicleFleet.repositories.EnterpriseRepository;
import ru.patrakhin.VehicleFleet.repositories.ManagerRepository;
import ru.patrakhin.VehicleFleet.repositories.VehicleRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ManagersService {
    private final ManagerRepository managerRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public ManagersService(ManagerRepository managerRepository, EnterpriseRepository enterpriseRepository,
                           VehicleRepository vehicleRepository) {
        this.managerRepository = managerRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public List<ManagersDTO> getAllManagers(){
        List<Managers> managersList = managerRepository.findAll();
        return managersList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ManagersDTO getManagerById(int id){
        Optional<Managers> managers = managerRepository.findById(id);
        return managers.map(this::convertToDTO).orElse(null);
    }

    @Transactional(readOnly = true)
    public void saveManager(ManagersDTO managersDTO){
        Managers managers = convertToEntity(managersDTO);
        managerRepository.save(managers);
    }

    @Transactional(readOnly = true)
    public void updateManager(int id, ManagersDTO updatedManagersDTO) {
        Managers managers = managerRepository.findById(id).orElse(null);
        if (managers != null) {
            updateEntityFromDTO(managers, updatedManagersDTO);
            managerRepository.save(managers);
        }
    }

    @Transactional(readOnly = true)
    public void deleteManager(int id){
        managerRepository.deleteById(id);
    }

    public List<VehiclesDTO> getVehiclesIdsByEnterprisesId(List<EnterprisesDTO> enterprisesDTOList, List<Vehicles> allVehicles) {
        List<Integer> enterpriseIds = enterprisesDTOList.stream()
                .map(EnterprisesDTO::getId)
                .collect(Collectors.toList());

        List<Vehicles> filteredVehicles = allVehicles.stream()
                .filter(vehicle -> enterpriseIds.contains(vehicle.getEnterprises().getId()))
                .collect(Collectors.toList());

        return filteredVehicles;
    }

    private VehiclesDTO convertVehiclesToDTO(Vehicles vehicles){
        Integer a = vehicles.getEnterprises().getId();
        Optional<Vehicles> vehicles1 = vehicleRepository.findById(a);
        return vehicles1.map(this::convertToDTO).orElse(null);
    }

    private VehiclesDTO convertToDTO(Vehicles vehicles){
        return new VehiclesDTO(
                vehicles.getId(),
        vehicles.getNumberVehicle(),
         vehicles.getPrice(),
        vehicles.getYearOfManufacture(),
        vehicles.getMileage(),
        vehicles.getEquipmentType(),
        vehicles.getCarBrand().getId()
        );
    }

    public List<EnterprisesDTO> getEnterpriseIdsByPersonId(Integer personId) {
        List<Managers> managersList = managerRepository.findAll();

        return managersList.stream()
                .filter(manager -> ((Integer)(manager.getPerson().getId())).equals(personId))
                .map(this::convertEnterprisesToDTO)
                .collect(Collectors.toList());
    }

    private EnterprisesDTO convertEnterprisesToDTO(Managers managers){
        Integer a = managers.getEnterprises().getId();
        Optional<Enterprises> enterprises = enterpriseRepository.findById(a);
        return enterprises.map(this::convertToDTO).orElse(null);
    }

    private EnterprisesDTO convertToDTO(Enterprises enterprises){
        return new EnterprisesDTO(
                enterprises.getId(),
                enterprises.getEnterpriseName(),
                enterprises.getEnterpriseAddress(),
                enterprises.getEnterprisePhone()
        );
    }

    private ManagersDTO convertToDTO(Managers managers){
        return new ManagersDTO(
                managers.getId(),
                managers.getPerson(),
                managers.getEnterprises()
        );
    }

    private Managers convertToEntity(ManagersDTO managersDTO) {
        Managers managers = new Managers();
        updateEntityFromDTO(managers, managersDTO);
        return managers;
    }

    private void updateEntityFromDTO(Managers managers, ManagersDTO managersDTO) {
        managers.setPerson(managersDTO.getPerson());
        managers.setEnterprises(managersDTO.getEnterprises());
    }
}
