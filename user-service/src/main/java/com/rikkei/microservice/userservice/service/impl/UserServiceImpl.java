package com.rikkei.microservice.userservice.service.impl;

import com.rikkei.microservice.userservice.dto.UserResponse;
import com.rikkei.microservice.userservice.entity.User;
import com.rikkei.microservice.userservice.repository.UserRepository;
import com.rikkei.microservice.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final DataSource dataSource;

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return UserResponse.fromEntity(user);
    }

    @Override
    public Map<String, Object> getDatabaseInfo() {
        Map<String, Object> info = new HashMap<>();
        try (Connection connection = dataSource.getConnection()) {
            info.put("service", "user-service");
            info.put("databaseName", connection.getCatalog());
            info.put("jdbcUrl", connection.getMetaData().getURL());
            info.put("status", "CONNECTED_ISOLATED");
        } catch (Exception e) {
            info.put("error", e.getMessage());
        }
        return info;
    }
}
