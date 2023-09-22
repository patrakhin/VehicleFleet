package ru.patrakhin.VehicleFleet.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.patrakhin.VehicleFleet.models.Vehicles;

public interface VehicleAdminRepository extends PagingAndSortingRepository <Vehicles, Integer>{
}
