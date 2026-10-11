package com.rikkei.microservice.orderservice.controller;

import com.rikkei.microservice.orderservice.dto.ApiResponseError;
import com.rikkei.microservice.orderservice.exception.GlobalExceptionHandler;
import com.rikkei.microservice.orderservice.exception.ResourceNotFoundException;
import com.rikkei.microservice.orderservice.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class OrderControllerTest {

    @Test
    void testHealthCheck() {
        OrderService orderService = Mockito.mock(OrderService.class);
        Mockito.when(orderService.getHealthCheck()).thenReturn("Order Service is Up");

        OrderController controller = new OrderController(orderService);
        String response = controller.healthCheck();

        Assertions.assertEquals("Order Service is Up", response);
    }

    @Test
    void testOrderNotFoundExceptionHandledByGlobalExceptionHandler() {
        Long nonExistentId = 999L;
        String errorMessage = "Order với ID " + nonExistentId + " không tồn tại trên hệ thống!";
        ResourceNotFoundException ex = new ResourceNotFoundException(errorMessage);

        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ResponseEntity<ApiResponseError> response = handler.handleResourceNotFoundException(ex);

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNotNull(response.getBody());
        Assertions.assertEquals(404, response.getBody().getStatus());
        Assertions.assertEquals("Not Found", response.getBody().getError());
        Assertions.assertEquals("Order với ID 999 không tồn tại trên hệ thống!", response.getBody().getMessage());
        Assertions.assertNotNull(response.getBody().getTimestamp());
    }
}
