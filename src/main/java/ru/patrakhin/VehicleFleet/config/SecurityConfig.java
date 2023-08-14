package ru.patrakhin.VehicleFleet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.patrakhin.VehicleFleet.services.ManagerDetailService;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final ManagerDetailService managerDetailService;

    public SecurityConfig(ManagerDetailService managerDetailService) {
        this.managerDetailService = managerDetailService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(managerDetailService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
