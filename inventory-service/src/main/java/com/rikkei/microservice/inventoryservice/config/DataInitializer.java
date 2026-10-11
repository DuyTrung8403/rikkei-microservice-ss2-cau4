package com.rikkei.microservice.inventoryservice.config;

import com.rikkei.microservice.inventoryservice.entity.Product;
import com.rikkei.microservice.inventoryservice.repository.InventoryProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initInventory(InventoryProductRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(Product.builder()
                        .productCode("LAPTOP-DELL-XPS")
                        .productName("Laptop Dell XPS 15")
                        .quantity(50)
                        .reservedQuantity(5)
                        .updatedAt(LocalDateTime.now())
                        .build());

                repository.save(Product.builder()
                        .productCode("IPHONE-16-PROMAX")
                        .productName("iPhone 16 Pro Max")
                        .quantity(120)
                        .reservedQuantity(12)
                        .updatedAt(LocalDateTime.now())
                        .build());
            }
        };
    }
}
