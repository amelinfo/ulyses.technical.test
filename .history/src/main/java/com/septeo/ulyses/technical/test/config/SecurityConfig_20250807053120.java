package com.septeo.ulyses.technical.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity  //Enables method-level security annotations
public class SecurityConfig {

    // method for global rules
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disables CSRF for APIs
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/api/brands/**").hasRole("ADMIN")  // Requires ADMIN role
                .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults());  // Enables HTTP Basic Auth
        return http.build();
    }

}
