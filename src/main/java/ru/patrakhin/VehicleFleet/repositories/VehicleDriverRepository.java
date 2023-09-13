package ru.patrakhin.VehicleFleet.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.patrakhin.VehicleFleet.models.VehicleDrivers;

import java.util.List;

@Repository
public interface VehicleDriverRepository extends JpaRepository<VehicleDrivers, Integer> {
}
