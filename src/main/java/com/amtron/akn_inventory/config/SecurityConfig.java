package com.amtron.akn_inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authConfig -> {
                    authConfig.requestMatchers("/", "/login", "/error", "/login-error", "/logout",
                            "/css/**", "/img/**", "/forgot-password/**", "/plugin/**", "/js/**").permitAll();
                    authConfig.anyRequest().authenticated();
                })
                .formLogin(login -> {
                    login.loginPage("/login");
                    login.successHandler(myAuthenticationSuccessHandler());
                    login.failureHandler(myAuthenticationFailureHandler());
                })
                .logout(logout -> {
                    logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
                    logout.logoutSuccessUrl("/");
                    logout.deleteCookies("JSESSIONID");
                    logout.invalidateHttpSession(true);
                })
                .sessionManagement(session -> {
                    session
                            .maximumSessions(1).expiredUrl("/expired");
                    // session.sessionFixation().migrateSession();
                    // session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).invalidSessionUrl("/login");

                });

        return http.build();
    }

    private AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MyAuthenticationSuccessHandler();
    }

    private AuthenticationFailureHandler myAuthenticationFailureHandler() {
        return new MyAuthenticationFailureHandler();
    }

}