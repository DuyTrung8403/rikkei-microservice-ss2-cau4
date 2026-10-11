package com.rikkei.microservice.orderservice.dto;

import com.rikkei.microservice.orderservice.entity.ProductEntity;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

    private Long id;
    private String name;
    private BigDecimal sellPrice;

    /**
     * Chuyển đổi (map) từ ProductEntity sang ProductResponseDTO.
     * Chỉ giữ lại id, name, sellPrice; bảo mật hoàn toàn sku, importPrice, stockQuantity.
     */
    public static ProductResponseDTO fromEntity(ProductEntity entity) {
        if (entity == null) {
            return null;
        }
        return ProductResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .sellPrice(entity.getSellPrice())
                .build();
    }
}
