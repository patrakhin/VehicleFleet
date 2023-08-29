package ru.patrakhin.VehicleFleet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.patrakhin.VehicleFleet.models.Enterprises;
import ru.patrakhin.VehicleFleet.models.Person;

import java.util.Optional;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprises, Integer> {

    Optional<Enterprises> findByEnterpriseName(String enterpriseName);
}
