package ru.patrakhin.VehicleFleet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.patrakhin.VehicleFleet.dto.DriversDTO;
import ru.patrakhin.VehicleFleet.dto.EnterprisesDTO;
import ru.patrakhin.VehicleFleet.dto.ManagersDTO;

import ru.patrakhin.VehicleFleet.dto.VehiclesDTO;
import ru.patrakhin.VehicleFleet.models.*;
import ru.patrakhin.VehicleFleet.repositories.DriverRepository;
import ru.patrakhin.VehicleFleet.repositories.EnterpriseRepository;
import ru.patrakhin.VehicleFleet.repositories.ManagerRepository;
import ru.patrakhin.VehicleFleet.repositories.VehicleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ManagersService {
    private final ManagerRepository managerRepository;
    private final EnterpriseRepository enterpriseRepository;
    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;

    @Autowired
    public ManagersService(ManagerRepository managerRepository, EnterpriseRepository enterpriseRepository,
                           VehicleRepository vehicleRepository, DriverRepository driverRepository) {
        this.managerRepository = managerRepository;
        this.enterpriseRepository = enterpriseRepository;
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
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


    public List<DriversDTO> getDriversIdsByEnterprisesId(int id){ // added id managers
        List<EnterprisesDTO> enterprisesDTOList2 = getEnterpriseIdsByPersonId(id);
        System.out.println(enterprisesDTOList2);
        List<Drivers> driversList = driverRepository.findAll();
        Integer driverBuf = 0;
        System.out.println(driversList);
        List<DriversDTO> resultDriversList = new ArrayList<>();
        for (EnterprisesDTO enterprisesDTO : enterprisesDTOList2) {
            Integer buf = enterprisesDTO.getId();
            for (Drivers drivers : driversList) {
                System.out.println(drivers);
                if (drivers.getEnterprises() != null) {
                     driverBuf = drivers.getEnterprises().getId();
                }
                if (Objects.equals(buf, driverBuf)) {
                    resultDriversList.add(convertDriversToDTO(drivers));
                    System.out.println("resultList = " + resultDriversList);
                }
                driverBuf = 0;
            }
        }
        return resultDriversList;
    }

    private DriversDTO convertDriversToDTO(Drivers drivers){
        DriversDTO dto = new DriversDTO();
        dto.setId(drivers.getId());
        dto.setDriverName(drivers.getDriverName());
        dto.setDriverAddress(drivers.getDriverAddress());
        dto.setDriverPhone(drivers.getDriverPhone());
        dto.setEnterprise_id(drivers.getEnterprises().getId());
        System.out.println("drivers from dto" + dto);
        return dto;
    }

    public List<VehiclesDTO> getVehiclesIdsByEnterprisesId(int id) { // added id managers
        List<EnterprisesDTO> enterprisesDTOList1 = getEnterpriseIdsByPersonId(id);
        List<Vehicles> vehiclesList = vehicleRepository.findAll();
        System.out.println(vehiclesList);
        List<VehiclesDTO> resultList = new ArrayList<>();
        for (EnterprisesDTO enterprisesDTO : enterprisesDTOList1) {
            Integer buf = enterprisesDTO.getId();
            for (Vehicles vehicles : vehiclesList) {
                System.out.println(vehicles);
                Integer vehicleBuf = vehicles.getEnterprises().getId();
                if (Objects.equals(buf, vehicleBuf)) {
                    resultList.add(convertToDTO(vehicles));
                }
            }
        }
        return resultList;
    }

    private VehiclesDTO convertToDTO(Vehicles vehicle) {
        VehiclesDTO dto = new VehiclesDTO();
        dto.setId(vehicle.getId());
        dto.setEquipmentType(vehicle.getEquipmentType());
        dto.setMileage(vehicle.getMileage());
        dto.setNumberVehicle(vehicle.getNumberVehicle());
        dto.setPrice(vehicle.getPrice());
        dto.setYearOfManufacture(vehicle.getYearOfManufacture());
        dto.setCarBrand(vehicle.getCarBrand().getId());
        dto.setEnterprises(vehicle.getEnterprises().getId());
        System.out.println("vehicle = " + dto);
        return dto;
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
