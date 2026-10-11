package com.rikkei.microservice.inventoryservice.repository;

import com.rikkei.microservice.inventoryservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryProductRepository extends JpaRepository<Product, Long> {
}
