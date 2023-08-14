package ru.patrakhin.VehicleFleet.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.patrakhin.VehicleFleet.models.Managers;

import java.util.Collection;
import java.util.Collections;

public class ManagerDetails implements UserDetails {

    private final Managers managers;

    public ManagerDetails(Managers managers) {
        this.managers = managers;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.managers.getPassword();
    }

    @Override
    public String getUsername() {
        return this.managers.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Managers getManagers(){
        return this.managers;
    }
}
