package ru.patrakhin.VehicleFleet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.patrakhin.VehicleFleet.models.VehicleDrivers;

@Repository
public interface VehicleDriverRepository extends JpaRepository<VehicleDrivers, Integer> {
}
