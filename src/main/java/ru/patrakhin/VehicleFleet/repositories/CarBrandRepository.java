package ru.patrakhin.VehicleFleet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.patrakhin.VehicleFleet.models.CarBrand;

import java.util.Optional;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, Integer> {

    Optional<CarBrand> findByBrandName(String name);
}
