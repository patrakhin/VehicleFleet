package ru.patrakhin.VehicleFleet.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class UserSecurityFilter extends OncePerRequestFilter {
    @Override
    public void doFilterInternal(HttpServletRequest httpservletRequest, HttpServletResponse httpservletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_USER"))) {
            // Если пользователь имеет роль ROLE_USER
            System.out.println(auth.getAuthorities());
            if (!httpservletRequest.getMethod().equals("GET") && !httpservletRequest.getRequestURI().equals("/hello")) {
                // и отправляет запросы PUT/POST/DELETE на другие URL кроме "/hello", возвращаем ошибку 401 Unauthorized
                httpservletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        // В противном случае, продолжаем цепочку фильтров
        filterChain.doFilter(httpservletRequest, httpservletResponse);
    }

}
