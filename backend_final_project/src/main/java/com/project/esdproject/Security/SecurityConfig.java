package com.project.esdproject.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection for stateless APIs
                .csrf(csrf -> csrf.disable())

                // Configure HTTP request authorization
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/**","/", "/index.html", "/static/**", "/css/**", "/js/**", "/images/**", "/api/admin/login","/course/**", "/courseSchedule/**","/department/**","/emp/**").permitAll()  // Allow login endpoint
                                .anyRequest().authenticated()  // All other requests must be authenticated
                );

        return http.build();
    }
}

