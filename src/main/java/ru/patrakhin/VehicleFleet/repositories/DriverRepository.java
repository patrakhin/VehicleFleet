package ru.patrakhin.VehicleFleet.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.patrakhin.VehicleFleet.models.Drivers;
import ru.patrakhin.VehicleFleet.models.VehicleDrivers;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Drivers, Integer> {
    List<Drivers> findAllByEnterprises_Id(int id);
}
