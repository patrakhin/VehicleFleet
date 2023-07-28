package ru.patrakhin.VehicleFleet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.patrakhin.VehicleFleet.models.CarBrand;
import ru.patrakhin.VehicleFleet.repositories.CarBrandRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CarBrandService {
    private final CarBrandRepository carBrandRepository;

    @Autowired
    public CarBrandService(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    public List<CarBrand> findAll() {
        return carBrandRepository.findAll();
    }

    public CarBrand findOne(int id) {
        Optional<CarBrand> foundBrand = carBrandRepository.findById(id);
        return foundBrand.orElse(null);
    }

    @Transactional
    public void save(CarBrand carBrand) {
        carBrandRepository.save(carBrand);
    }

    @Transactional
    public void update(int id, CarBrand updatedBrand) {
        updatedBrand.setId(id);
        carBrandRepository.save(updatedBrand);
    }

    @Transactional
    public void delete(int id) {
        carBrandRepository.deleteById(id);
    }
}
