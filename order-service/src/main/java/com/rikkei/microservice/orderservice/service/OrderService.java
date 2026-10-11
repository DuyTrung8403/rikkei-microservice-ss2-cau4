package com.rikkei.microservice.orderservice.service;

import com.rikkei.microservice.orderservice.dto.OrderResponse;

public interface OrderService {
    String getHealthCheck();
    OrderResponse getOrderById(Long id);
}
