package com.rikkei.microservice.userservice.config;

import com.rikkei.microservice.userservice.entity.User;
import com.rikkei.microservice.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                userRepository.save(User.builder()
                        .username("nguyenvana")
                        .email("vana@gmail.com")
                        .fullName("Nguyễn Văn A")
                        .role("CUSTOMER")
                        .createdAt(LocalDateTime.now())
                        .build());

                userRepository.save(User.builder()
                        .username("tranthib")
                        .email("thib@gmail.com")
                        .fullName("Trần Thị B")
                        .role("ADMIN")
                        .createdAt(LocalDateTime.now())
                        .build());
            }
        };
    }
}
