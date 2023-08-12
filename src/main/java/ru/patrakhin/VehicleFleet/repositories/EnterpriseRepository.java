package ru.patrakhin.VehicleFleet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.patrakhin.VehicleFleet.models.Enterprises;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprises, Integer> {
}
