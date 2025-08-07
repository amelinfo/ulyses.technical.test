package com.septeo.ulyses.technical.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * This class is for testing
 */
@Configuration
public class UserConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin123") // {noop} = plaintext (for testing)
                .roles("ADMIN") // Grants ROLE_ADMIN
                .build();
        System.out.println(admin.getAuthorities());        
        return new InMemoryUserDetailsManager(admin);
    }
}
