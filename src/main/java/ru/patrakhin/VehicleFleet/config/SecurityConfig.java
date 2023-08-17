package ru.patrakhin.VehicleFleet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.patrakhin.VehicleFleet.services.PersonDetailService;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonDetailService personDetailService;

    @Autowired
    public SecurityConfig(PersonDetailService personDetailService) {
        this.personDetailService = personDetailService;
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        //конфигурир сам спринг секьюрити и авторизацию
        http.csrf().disable() //откл защ от межсайт поддел запросов
                .authorizeRequests()                                         //настр
                .antMatchers("/manager1/**").hasRole("MANAGER1")
                .antMatchers("/manager2/**").hasRole("MANAGER2")
                .antMatchers("/auth/login", "/error").permitAll()    //авторизации
                .anyRequest().hasAnyRole("MANAGER1", "MANAGER2")
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login?error");
    }

    // Настраиваем аутентификацию
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }
}
