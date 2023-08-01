package ru.patrakhin.VehicleFleet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.patrakhin.VehicleFleet.dto.CarBrandDTO;
import ru.patrakhin.VehicleFleet.models.BrandName;
import ru.patrakhin.VehicleFleet.models.CarBrand;
import ru.patrakhin.VehicleFleet.models.CarType;
import ru.patrakhin.VehicleFleet.repositories.CarBrandRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class CarBrandService {
    private final CarBrandRepository carBrandRepository;

    @Autowired
    public CarBrandService(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    public List<CarBrandDTO> getAllCarBrands() {
        List<CarBrand> carBrandList = carBrandRepository.findAll();
        return carBrandList.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CarBrandDTO getCarBrandById(int id) {
        Optional<CarBrand> carBrand = carBrandRepository.findById(id);
        return carBrand.map(this::convertToDTO).orElse(null);
    }

    @Transactional
    public void saveCarBrand(CarBrandDTO carBrandDTO) {
        CarBrand carBrand = convertToEntity(carBrandDTO);
        carBrandRepository.save(carBrand);
    }

    @Transactional
    public void updateCarBrand(int id, CarBrandDTO updatedCarBrandDTO) {
        CarBrand carBrand = carBrandRepository.findById(id).orElse(null);
        if (carBrand != null) {
            updateEntityFromDTO(carBrand, updatedCarBrandDTO);
            carBrandRepository.save(carBrand);
        }
    }

    @Transactional
    public void deleteCarBrand(int id) {
        carBrandRepository.deleteById(id);
    }

    private CarBrandDTO convertToDTO(CarBrand carBrand) {
        return new CarBrandDTO(
                carBrand.getId(),
                carBrand.getBrandName(),
                carBrand.getCarType(),
                carBrand.getFuelTankVolume(),
                carBrand.getCarryingCapacity(),
                carBrand.getNumberOfSeats(),
                carBrand.getMaxSpeed()
        );
    }

    private CarBrand convertToEntity(CarBrandDTO carBrandDTO) {
        CarBrand carBrand = new CarBrand();
        updateEntityFromDTO(carBrand, carBrandDTO);
        return carBrand;
    }

    private void updateEntityFromDTO(CarBrand carBrand, CarBrandDTO carBrandDTO) {
        carBrand.setBrandName(carBrandDTO.getBrandName());
        carBrand.setCarType(carBrandDTO.getCarType());
        carBrand.setFuelTankVolume(carBrandDTO.getFuelTankVolume());
        carBrand.setCarryingCapacity(carBrandDTO.getCarryingCapacity());
        carBrand.setNumberOfSeats(carBrandDTO.getNumberOfSeats());
        carBrand.setMaxSpeed(carBrandDTO.getMaxSpeed());
    }

    public List<BrandName> getAllBrandNames() {
        return Arrays.asList(BrandName.values());
    }

    public List<CarType> getAllCarTypes() {
        return Arrays.asList(CarType.values());
    }


}
