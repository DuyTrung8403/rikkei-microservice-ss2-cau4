package com.rikkei.microservice.userservice.service;

import com.rikkei.microservice.userservice.dto.UserResponse;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUserById(Long id);
    Map<String, Object> getDatabaseInfo();
}
