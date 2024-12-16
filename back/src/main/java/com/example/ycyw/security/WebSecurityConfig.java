package com.example.ycyw.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // Autorise l'accès à la liste des messages
                )
                .httpBasic(httpBasic -> {
                }) // Désactive la sécurité HTTP de base
                .formLogin(formLogin -> formLogin.disable()) // Désactive le formulaire de connexion
                .csrf(csrf -> csrf.disable()) // Désactive la protection CSRF
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable())); // Désactive les
                                                                                                   // options de frame
        return http.build();
    }
}
