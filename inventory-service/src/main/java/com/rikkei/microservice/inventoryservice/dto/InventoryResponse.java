package com.rikkei.microservice.inventoryservice.dto;

import com.rikkei.microservice.inventoryservice.entity.Product;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryResponse {

    private Long id;
    private String productCode;
    private String productName;
    private Integer quantity;
    private Integer reservedQuantity;
    private LocalDateTime updatedAt;

    public static InventoryResponse fromEntity(Product product) {
        if (product == null) return null;
        return InventoryResponse.builder()
                .id(product.getId())
                .productCode(product.getProductCode())
                .productName(product.getProductName())
                .quantity(product.getQuantity())
                .reservedQuantity(product.getReservedQuantity())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
}
