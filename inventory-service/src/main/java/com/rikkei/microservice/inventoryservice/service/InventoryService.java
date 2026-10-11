package com.rikkei.microservice.inventoryservice.service;

import com.rikkei.microservice.inventoryservice.dto.InventoryResponse;

import java.util.List;
import java.util.Map;

public interface InventoryService {
    List<InventoryResponse> getAllInventory();
    InventoryResponse getInventoryById(Long id);
    Map<String, Object> getDatabaseInfo();
}
