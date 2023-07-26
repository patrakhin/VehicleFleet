package ru.patrakhin.VehicleFleet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.patrakhin.VehicleFleet.models.Vehicles;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicles, Integer> {
}
