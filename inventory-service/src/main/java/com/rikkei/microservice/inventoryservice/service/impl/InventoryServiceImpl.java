package com.rikkei.microservice.inventoryservice.service.impl;

import com.rikkei.microservice.inventoryservice.dto.InventoryResponse;
import com.rikkei.microservice.inventoryservice.entity.Product;
import com.rikkei.microservice.inventoryservice.repository.InventoryProductRepository;
import com.rikkei.microservice.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryProductRepository productRepository;
    private final DataSource dataSource;

    @Override
    public List<InventoryResponse> getAllInventory() {
        return productRepository.findAll().stream()
                .map(InventoryResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public InventoryResponse getInventoryById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        return InventoryResponse.fromEntity(product);
    }

    @Override
    public Map<String, Object> getDatabaseInfo() {
        Map<String, Object> info = new HashMap<>();
        try (Connection connection = dataSource.getConnection()) {
            info.put("service", "inventory-service");
            info.put("databaseName", connection.getCatalog());
            info.put("jdbcUrl", connection.getMetaData().getURL());
            info.put("status", "CONNECTED_ISOLATED");
        } catch (Exception e) {
            info.put("error", e.getMessage());
        }
        return info;
    }
}
