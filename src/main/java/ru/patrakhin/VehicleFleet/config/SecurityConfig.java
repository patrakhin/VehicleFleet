package ru.patrakhin.VehicleFleet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.patrakhin.VehicleFleet.security.ManagersSecurityFilter;
import ru.patrakhin.VehicleFleet.security.UserSecurityFilter;
import ru.patrakhin.VehicleFleet.services.PersonDetailService;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PersonDetailService personDetailService;
    private final JWTFilter jwtFilter;
    private final UserSecurityFilter userSecurityFilter;
    private final ManagersSecurityFilter managersSecurityFilter;

    @Autowired
    public SecurityConfig(PersonDetailService personDetailService, JWTFilter jwtFilter,
                          UserSecurityFilter userSecurityFilter, ManagersSecurityFilter managersSecurityFilter) {
        this.personDetailService = personDetailService;
        this.jwtFilter = jwtFilter;
        this.userSecurityFilter = userSecurityFilter;
        this.managersSecurityFilter = managersSecurityFilter;
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        //конфигурир сам спринг секьюрити и авторизацию
        http
                .csrf().disable()//откл защ от межсайт поддел запросов .csrf().disable()
                //.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // включаем CSRF защиту
                //.and()// союз и для сцепки настроек (создания цепочки)
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")//настр
                .antMatchers("/manager1/**").hasRole("MANAGER1")
                .antMatchers("/manager2/**").hasRole("MANAGER2")
                .antMatchers("/hello").hasRole("USER") //добавил в цепочку юзера
                .antMatchers("/auth/login", "/error").permitAll()    //авторизации
                .anyRequest().hasAnyRole("MANAGER1", "MANAGER2")
                .and()
                .formLogin().loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/hello", true)
                .failureUrl("/auth/login?error")
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                // запретили пользователю запросы PUT/POST/DELETE кроме GET на "/hello"
                .addFilterBefore(userSecurityFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(managersSecurityFilter, UsernamePasswordAuthenticationFilter.class);
    }

    // Настраиваем аутентификацию
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(personDetailService)
                .passwordEncoder(getPasswordEncoder());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
