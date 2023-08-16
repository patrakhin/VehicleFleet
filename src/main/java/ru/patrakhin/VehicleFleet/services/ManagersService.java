package ru.patrakhin.VehicleFleet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.patrakhin.VehicleFleet.dto.CarBrandDTO;
import ru.patrakhin.VehicleFleet.dto.ManagersDTO;

import ru.patrakhin.VehicleFleet.models.CarBrand;
import ru.patrakhin.VehicleFleet.models.Managers;
import ru.patrakhin.VehicleFleet.repositories.ManagerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ManagersService {
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagersService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
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


    public List<Integer> getEnterpriseIdsByPersonId(Integer personId) {
        return managerRepository.findEnterpriseIdsByPersonId(personId);
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
