package com.rikkei.microservice.orderservice.service.impl;

import com.rikkei.microservice.orderservice.dto.OrderResponse;
import com.rikkei.microservice.orderservice.entity.Order;
import com.rikkei.microservice.orderservice.exception.ResourceNotFoundException;
import com.rikkei.microservice.orderservice.repository.OrderRepository;
import com.rikkei.microservice.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public String getHealthCheck() {
        return "Order Service is Up";
    }

    @Override
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order với ID " + id + " không tồn tại trên hệ thống!"));
        return OrderResponse.fromEntity(order);
    }
}
