package ru.patrakhin.VehicleFleet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.patrakhin.VehicleFleet.dto.EnterprisesDTO;
import ru.patrakhin.VehicleFleet.models.Enterprises;
import ru.patrakhin.VehicleFleet.repositories.EnterpriseRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class EnterprisesService {
    private final EnterpriseRepository enterpriseRepository;

    @Autowired
    public EnterprisesService(EnterpriseRepository enterpriseRepository){
        this.enterpriseRepository = enterpriseRepository;
    }

    public List<EnterprisesDTO> getAllEnterprises(){
        List<Enterprises> enterprisesList = enterpriseRepository.findAll();
        return enterprisesList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public EnterprisesDTO getEnterpriseById(int id){
        Optional<Enterprises> enterprises = enterpriseRepository.findById(id);
        return enterprises.map(this::convertToDTO).orElse(null);
    }


    public Enterprises getEnterpriseByName(EnterprisesDTO enterprisesDTO){

        Optional<Enterprises> enterprises1 = enterpriseRepository.findByEnterpriseName(enterprisesDTO.getEnterpriseName());
        return enterprises1.orElse(null);
    }

    @Transactional
    public void saveEnterprise(EnterprisesDTO enterprisesDTO){
        Enterprises enterprises = convertToEntity(enterprisesDTO);
        enterpriseRepository.save(enterprises);
    }

    @Transactional
    public void updateEnterprise(int id, EnterprisesDTO updateEnterprisesDTO){
        Enterprises enterprise = enterpriseRepository.findById(id).orElse(null);
        if (enterprise != null){
            updateEntityFromDTO(enterprise, updateEnterprisesDTO);
            enterpriseRepository.save(enterprise);
        }
    }

    @Transactional
    public void deleteEnterprise(int id){
        enterpriseRepository.deleteById(id);
    }

    private EnterprisesDTO convertToDTO(Enterprises enterprises){
        return new EnterprisesDTO(
                enterprises.getId(),
                enterprises.getEnterpriseName(),
                enterprises.getEnterpriseAddress(),
                enterprises.getEnterprisePhone()
        );
    }


    private Enterprises convertToEntity(EnterprisesDTO enterprisesDTO){
        Enterprises enterprises = new Enterprises();
        updateEntityFromDTO(enterprises, enterprisesDTO);
        return enterprises;
    }

    private void updateEntityFromDTO(Enterprises enterprises, EnterprisesDTO enterprisesDTO) {
        enterprises.setEnterpriseName(enterprisesDTO.getEnterpriseName());
        enterprises.setEnterpriseAddress(enterprisesDTO.getEnterpriseAddress());
        enterprises.setEnterprisePhone(enterprisesDTO.getEnterprisePhone());
    }
}
