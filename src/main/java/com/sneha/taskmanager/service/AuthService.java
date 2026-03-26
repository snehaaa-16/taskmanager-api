package com.sneha.taskmanager.service;

import com.sneha.taskmanager.dto.AuthResponse;
import com.sneha.taskmanager.dto.LoginRequest;
import com.sneha.taskmanager.entity.User;
import com.sneha.taskmanager.repository.UserRepository;
import com.sneha.taskmanager.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}