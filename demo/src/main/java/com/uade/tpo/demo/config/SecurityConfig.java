package com.uade.tpo.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Solo para pruebas, no recomendado en producción
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/catalogo.html","/uploadImg.html", "/css/**", "/js/**", "/images/**", "/vehicles/**").permitAll()
                .anyRequest().authenticated()
            );
        return http.build();
    }
}