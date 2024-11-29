package com.amtron.akn_inventory.controller.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers("/login", "/signup").permitAll();
                    authConfig.anyRequest().authenticated();
                })
                .formLogin(login -> {
                    login.loginPage("/login");
                    login.successHandler((request, response, authentication) -> {
                        var authorities = authentication.getAuthorities();
                        String redirectUrl = "/default-dashboard";

                        if (authorities.stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"))) {
                            redirectUrl = "/admin-dashboard";
                        } else if (authorities.stream()
                                .anyMatch(auth -> auth.getAuthority().equals("ROLE_USER"))) {
                            redirectUrl = "/user-dashboard";
                        }

                        response.sendRedirect(redirectUrl);
                    });
                    login.failureHandler((request, response, exception) -> {
                        response.sendRedirect("/login?error=true");
                    });
                    login.permitAll();
                })
                .logout(logout -> logout
                        .permitAll());

        return http.build();
    }
}
