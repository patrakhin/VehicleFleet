package ru.patrakhin.VehicleFleet.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.patrakhin.VehicleFleet.models.Managers;
import ru.patrakhin.VehicleFleet.repositories.ManagerRepository;
import ru.patrakhin.VehicleFleet.security.ManagerDetails;

import java.util.Optional;

@Service
public class ManagerDetailService implements UserDetailsService {
    private final ManagerRepository managerRepository;

    @Autowired
    public ManagerDetailService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Managers> managers = managerRepository.findManagersByName(username);
        if (managers.isEmpty()){
            throw new UsernameNotFoundException("User not found!");
        }
        return new ManagerDetails(managers.get());
    }
}
