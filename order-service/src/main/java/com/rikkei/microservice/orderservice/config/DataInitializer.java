package com.rikkei.microservice.orderservice.config;

import com.rikkei.microservice.orderservice.entity.ProductEntity;
import com.rikkei.microservice.orderservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initProducts(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                productRepository.save(ProductEntity.builder()
                        .name("Laptop Dell XPS 15")
                        .sku("DELL-XPS-15-2026")
                        .importPrice(new BigDecimal("25000000"))
                        .sellPrice(new BigDecimal("32000000"))
                        .stockQuantity(50)
                        .build());

                productRepository.save(ProductEntity.builder()
                        .name("iPhone 16 Pro Max")
                        .sku("IP16-PROMAX-256")
                        .importPrice(new BigDecimal("28000000"))
                        .sellPrice(new BigDecimal("34990000"))
                        .stockQuantity(120)
                        .build());
            }
        };
    }
}
