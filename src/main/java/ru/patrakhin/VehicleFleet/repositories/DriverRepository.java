package ru.patrakhin.VehicleFleet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.patrakhin.VehicleFleet.models.Drivers;

@Repository
public interface DriverRepository extends JpaRepository<Drivers, Integer> {
}
