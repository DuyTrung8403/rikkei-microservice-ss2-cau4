package com.rikkei.microservice.inventoryservice.controller;

import com.rikkei.microservice.inventoryservice.dto.InventoryResponse;
import com.rikkei.microservice.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryResponse>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllInventory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryResponse> getInventoryById(@PathVariable Long id) {
        InventoryResponse response = inventoryService.getInventoryById(id);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/db-info")
    public ResponseEntity<Map<String, Object>> getDatabaseInfo() {
        return ResponseEntity.ok(inventoryService.getDatabaseInfo());
    }
}
