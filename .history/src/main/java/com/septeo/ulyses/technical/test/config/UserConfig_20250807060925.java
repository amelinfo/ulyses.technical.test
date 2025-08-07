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
 /**
  * 1- First call : curl http://localhost:8080/api/brands
  * [
  {
  "id":1,"name":"Renault","description":"French automobile manufacturer",
  "vehicles":
    [{"id":1,"brand":{"id":1,"name":"Renault","description":"French automobile manufacturer"},"model":"Clio","year":"2022","color":"Red"},
    {"id":2,"brand":{"id":1,"name":"Renault","description":"French automobile manufacturer"},"model":"Megane","year":"2021","color":"Blue"},
    {"id":3,"brand":{"id":1,"name":"Renault","description":"French automobile manufacturer"},"model":"Captur","year":"2023","color":"White"}]
  },
  {
  "id":2,"name":"Opel","description":"German automobile manufacturer","vehicles":
    [{"id":4,"brand":{"id":2,"name":"Opel","description":"German automobile manufacturer"},"model":"Corsa","year":"2022","color":"Black"},
    {"id":5,"brand":{"id":2,"name":"Opel","description":"German automobile manufacturer"},"model":"Astra","year":"2021","color":"Silver"},
    {"id":6,"brand":{"id":2,"name":"Opel","description":"German automobile manufacturer"},"model":"Mokka","year":"2023","color":"Green"}]
  }
  ,{
  "id":3,"name":"Volkswagen","description":"German automobile manufacturer","vehicles":
  [{"id":7,"brand":{"id":3,"name":"Volkswagen","description":"German automobile manufacturer"},"model":"Golf","year":"2022","color":"Blue"},
  {"id":8,"brand":{"id":3,"name":"Volkswagen","description":"German automobile manufacturer"},"model":"Polo","year":"2021","color":"Red"},
  {"id":9,"brand":{"id":3,"name":"Volkswagen","description":"German automobile manufacturer"},"model":"Tiguan","year":"2023","color":"Black"}]
  }
  * ]
  * 
  */