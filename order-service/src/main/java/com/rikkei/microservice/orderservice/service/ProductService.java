package com.rikkei.microservice.orderservice.service;

import com.rikkei.microservice.orderservice.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {
    ProductResponseDTO getProductById(Long id);
    List<ProductResponseDTO> getAllProducts();
}
