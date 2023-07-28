package ru.patrakhin.VehicleFleet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.patrakhin.VehicleFleet.models.CarBrand;

@Repository
public interface CarBrandRepository extends JpaRepository<CarBrand, Integer> {

}
