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
  * {
  * "id":1,"name":"Renault","description":"French automobile manufacturer",
  * "vehicles":
  * [{"id":1,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},
  * {"id":2,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"model":"Megane","year":"2021","color":"Blue"},
  * {"id":3,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"model":"Captur","year":"2023","color":"White"}]
  * },
  * {
  * "id":2,"name":"Opel","description":"German automobile
  * manufacturer","vehicles":
  * [{"id":4,"brand":{"id":2,"name":"Opel","description":"German automobile
  * manufacturer"},"model":"Corsa","year":"2022","color":"Black"},
  * {"id":5,"brand":{"id":2,"name":"Opel","description":"German automobile
  * manufacturer"},"model":"Astra","year":"2021","color":"Silver"},
  * {"id":6,"brand":{"id":2,"name":"Opel","description":"German automobile
  * manufacturer"},"model":"Mokka","year":"2023","color":"Green"}]
  * }
  * ,{
  * "id":3,"name":"Volkswagen","description":"German automobile
  * manufacturer","vehicles":
  * [{"id":7,"brand":{"id":3,"name":"Volkswagen","description":"German automobile
  * manufacturer"},"model":"Golf","year":"2022","color":"Blue"},
  * {"id":8,"brand":{"id":3,"name":"Volkswagen","description":"German automobile
  * manufacturer"},"model":"Polo","year":"2021","color":"Red"},
  * {"id":9,"brand":{"id":3,"name":"Volkswagen","description":"German automobile
  * manufacturer"},"model":"Tiguan","year":"2023","color":"Black"}]
  * }
  * ]
  * 
  * 2- Second call : curl -X POST -u admin:admin123 -H "Content-Type:
  * application/json" -d '{"name":"Hyundai"}' http://localhost:8080/api/brands
  * 
  * {"id":4,"name":"Hyundai","description":null,"vehicles":[]}
  * 
  * 
  * 3- Third call : curl http://localhost:8080/api/brands
  * [{"id":1,"name":"Renault","description":"French automobile
  * manufacturer","vehicles":[{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},{"id":2,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Megane","year":"2021","color":"Blue"},{"id":3,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Captur","year":"2023","color":"White"}]},{"id":2,"name":"Opel","description":"German
  * automobile
  * manufacturer","vehicles":[{"id":4,"brand":{"id":2,"name":"Opel","description":"German
  * automobile
  * manufacturer"},"model":"Corsa","year":"2022","color":"Black"},{"id":5,"brand":{"id":2,"name":"Opel","description":"German
  * automobile
  * manufacturer"},"model":"Astra","year":"2021","color":"Silver"},{"id":6,"brand":{"id":2,"name":"Opel","description":"German
  * automobile
  * manufacturer"},"model":"Mokka","year":"2023","color":"Green"}]},{"id":3,"name":"Volkswagen","description":"German
  * automobile
  * manufacturer","vehicles":[{"id":7,"brand":{"id":3,"name":"Volkswagen","description":"German
  * automobile
  * manufacturer"},"model":"Golf","year":"2022","color":"Blue"},{"id":8,"brand":{"id":3,"name":"Volkswagen","description":"German
  * automobile
  * manufacturer"},"model":"Polo","year":"2021","color":"Red"},{"id":9,"brand":{"id":3,"name":"Volkswagen","description":"German
  * automobile
  * manufacturer"},"model":"Tiguan","year":"2023","color":"Black"}]},
  * {"id":4,"name":"Hyundai","description":null,"vehicles":[]}] <------------ the
  * last one added
  * 
  * 
  * 
  * curl http://localhost:8080/api/sales/vehicles/1
  * [
  * {"id":1,"saleDate":"2025-01-01","price":14850.75,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":2,"saleDate":"2025-01-01","price":15125.50,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":3,"saleDate":"2025-01-02","price":14975.25,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":4,"saleDate":"2025-01-02","price":15210.80,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":5,"saleDate":"2025-01-03","price":15050.60,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":6,"saleDate":"2025-01-03","price":14890.30,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":7,"saleDate":"2025-01-04","price":15175.45,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":8,"saleDate":"2025-01-04","price":15025.90,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":9,"saleDate":"2025-01-05","price":14950.75,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":10,"saleDate":"2025-01-05","price":15225.60,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":11,"saleDate":"2025-01-06","price":15100.25,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":12,"saleDate":"2025-01-06","price":14925.80,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":13,"saleDate":"2025-01-07","price":15275.45,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":14,"saleDate":"2025-01-07","price":15150.30,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":15,"saleDate":"2025-01-08","price":14875.90,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":16,"saleDate":"2025-01-08","price":15325.75,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":17,"saleDate":"2025-01-09","price":15075.60,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":18,"saleDate":"2025-01-09","price":14825.25,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":19,"saleDate":"2025-01-10","price":15375.80,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":20,"saleDate":"2025-01-10","price":15200.45,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":21,"saleDate":"2025-01-11","price":14775.30,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":22,"saleDate":"2025-01-11","price":15425.90,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":23,"saleDate":"2025-01-12","price":15250.75,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":24,"saleDate":"2025-01-12","price":14725.60,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":25,"saleDate":"2025-01-13","price":15475.25,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":26,"saleDate":"2025-01-13","price":15300.80,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":27,"saleDate":"2025-01-14","price":14675.45,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":28,"saleDate":"2025-01-14","price":15525.30,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":29,"saleDate":"2025-01-15","price":15350.90,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":30,"saleDate":"2025-01-15","price":14625.75,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":31,"saleDate":"2025-01-01","price":15575.60,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":32,"saleDate":"2025-01-02","price":15400.25,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":33,"saleDate":"2025-01-03","price":14575.80,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":34,"saleDate":"2025-01-04","price":15625.45,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":35,"saleDate":"2025-01-05","price":15450.30,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":36,"saleDate":"2025-01-06","price":14525.90,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":37,"saleDate":"2025-01-07","price":15675.75,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":38,"saleDate":"2025-01-08","price":15500.60,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":39,"saleDate":"2025-01-09","price":14475.25,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":40,"saleDate":"2025-01-10","price":15725.80,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":41,"saleDate":"2025-01-11","price":15550.45,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":42,"saleDate":"2025-01-12","price":14425.30,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":43,"saleDate":"2025-01-13","price":15775.90,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":44,"saleDate":"2025-01-14","price":15600.75,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":45,"saleDate":"2025-01-15","price":14375.60,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":46,"saleDate":"2025-01-01","price":15825.25,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":47,"saleDate":"2025-01-02","price":15650.80,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":48,"saleDate":"2025-01-03","price":14325.45,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}},
  * {"id":49,"saleDate":"2025-01-04","price":15875.30,"vehicle":{"id":1,"model":"Clio","year":"2022","color":"Red","brand":{"id":1,"name":"Renault","description":"French
  * automobile manufacturer"}}}]
  * 
  * 
  * curl http://localhost:8080/api/sales <----- the first page
  * {"content":
  * [{"id":1,
  * "brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},
  * "vehicle":
  * {"id":1,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-01","price":14850.75},
  * {"id":2,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-01","price":15125.50},
  * {"id":3,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-02","price":14975.25},
  * {"id":4,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-02","price":15210.80},
  * {"id":5,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-03","price":15050.60},
  * {"id":6,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-03","price":14890.30},
  * {"id":7,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-04","price":15175.45},
  * {"id":8,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-04","price":15025.90},
  * {"id":9,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-05","price":14950.75},
  * {"id":10,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-05","price":15225.60}
  * ],
  * "pageable":{"pageNumber":0,"pageSize":10,"sort":{"empty":true,"sorted":false,"unsorted":true},"offset":0,"paged":true,"unpaged":false},"last":false,"totalPages":45,"totalElements":441,"size":10,"number":0,"sort":{"empty":true,"sorted":false,"unsorted":true},"first":true,"numberOfElements":10,"empty":false}
  * 
  * 
  * curl "http://localhost:8080/api/sales?page=1&sort=saleDate,desc" <--- the
  * second page sorted by saleDate
  * {"content":
  * [
  * {"id":11,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-06","price":15100.25},
  * {"id":12,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-06","price":14925.80},
  * {"id":13,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-07","price":15275.45},
  * {"id":14,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-07","price":15150.30},
  * {"id":15,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-08","price":14875.90},
  * {"id":16,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-08","price":15325.75},
  * {"id":17,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-09","price":15075.60},
  * {"id":18,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-09","price":14825.25},
  * {"id":19,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-10","price":15375.80},
  * {"id":20,"brand":{"id":1,"name":"Renault","description":"French automobile
  * manufacturer"},"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French
  * automobile
  * manufacturer"},"model":"Clio","year":"2022","color":"Red"},"saleDate":"2025-01-10","price":15200.45}],
  * "pageable":{"pageNumber":1,"pageSize":10,"sort":{"empty":true,"sorted":false,"unsorted":true},"offset":10,"paged":true,"unpaged":false},"last":false,"totalPages":45,"totalElements":441,"size":10,"number":1,"sort":{"empty":true,"sorted":false,"unsorted":true},"first":false,"numberOfElements":10,"empty":false}
  * 
  * 
  * i got this
  * 
  * curl http://localhost:8080/api/sales/vehicles/bestSelling
  * [
  {"salesCount":49,"vehicle":{"id":1,"brand":{"id":1,"name":"Renault","description":"French automobile manufacturer"},"model":"Clio","year":"2022","color":"Red"}},
  {"salesCount":49,"vehicle":{"id":8,"brand":{"id":3,"name":"Volkswagen","description":"German automobile manufacturer"},"model":"Polo","year":"2021","color":"Red"}},
  {"salesCount":49,"vehicle":{"id":7,"brand":{"id":3,"name":"Volkswagen","description":"German automobile manufacturer"},"model":"Golf","year":"2022","color":"Blue"}},
  {"salesCount":49,"vehicle":{"id":2,"brand":{"id":1,"name":"Renault","description":"French automobile manufacturer"},"model":"Megane","year":"2021","color":"Blue"}},
  {"salesCount":49,"vehicle":{"id":6,"brand":{"id":2,"name":"Opel","description":"German automobile manufacturer"},"model":"Mokka","year":"2023","color":"Green"}}
  ]
  */