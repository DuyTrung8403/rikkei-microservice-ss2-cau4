package com.rikkei.microservice.orderservice.service.impl;

import com.rikkei.microservice.orderservice.dto.ProductResponseDTO;
import com.rikkei.microservice.orderservice.entity.ProductEntity;
import com.rikkei.microservice.orderservice.repository.ProductRepository;
import com.rikkei.microservice.orderservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO getProductById(Long id) {
        ProductEntity entity = productRepository.findById(id).orElse(null);
        return ProductResponseDTO.fromEntity(entity);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
