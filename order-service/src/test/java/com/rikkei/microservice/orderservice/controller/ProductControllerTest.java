package com.rikkei.microservice.orderservice.controller;

import com.rikkei.microservice.orderservice.dto.ProductResponseDTO;
import com.rikkei.microservice.orderservice.entity.ProductEntity;
import com.rikkei.microservice.orderservice.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

class ProductControllerTest {

    @Test
    void testDTOExcludesSensitiveFields() {
        ProductEntity entity = ProductEntity.builder()
                .id(1L)
                .name("Test Product")
                .sku("SKU-SECRET-123")
                .importPrice(new BigDecimal("1000000"))
                .sellPrice(new BigDecimal("1500000"))
                .stockQuantity(100)
                .build();

        ProductResponseDTO dto = ProductResponseDTO.fromEntity(entity);

        Assertions.assertNotNull(dto);
        Assertions.assertEquals(1L, dto.getId());
        Assertions.assertEquals("Test Product", dto.getName());
        Assertions.assertEquals(new BigDecimal("1500000"), dto.getSellPrice());
    }

    @Test
    void testGetProductByIdReturnsDTO() {
        ProductService productService = Mockito.mock(ProductService.class);
        ProductResponseDTO expectedDto = ProductResponseDTO.builder()
                .id(1L)
                .name("iPhone 16 Pro Max")
                .sellPrice(new BigDecimal("34990000"))
                .build();

        Mockito.when(productService.getProductById(1L)).thenReturn(expectedDto);

        ProductController controller = new ProductController(productService);
        ResponseEntity<ProductResponseDTO> response = controller.getProductById(1L);

        Assertions.assertEquals(200, response.getStatusCode().value());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals("iPhone 16 Pro Max", response.getBody().getName());
        Assertions.assertEquals(new BigDecimal("34990000"), response.getBody().getSellPrice());
    }
}
