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
public class ManagersSecurityFilter extends OncePerRequestFilter{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && (auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("MANAGER1")) || auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("MANAGER2")))) {
            // Если пользователь имеет роль MANAGER1 или MANAGER2
            if (!request.getRequestURI().startsWith("/manager1/") && !request.getRequestURI().startsWith("/manager2/")) {
                // и отправляет запросы на другие URL, возвращаем ошибку 403 Forbidden
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }

        // В противном случае, продолжаем цепочку фильтров
        filterChain.doFilter(request, response);
    }
}
