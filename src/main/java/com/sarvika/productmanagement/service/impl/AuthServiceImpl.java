package com.sarvika.productmanagement.service.impl;

import com.sarvika.productmanagement.domain.reponse.LoginResponse;
import com.sarvika.productmanagement.domain.request.LoginRequest;
import com.sarvika.productmanagement.entity.User;
import com.sarvika.productmanagement.exception.InvalidCredentialException;
import com.sarvika.productmanagement.exception.UserNotFoundException;
import com.sarvika.productmanagement.repository.UserRepository;
import com.sarvika.productmanagement.service.AuthService;
import com.sarvika.productmanagement.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User Not Found!"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new InvalidCredentialException("Invalid Credentials");
        }
        return LoginResponse.builder()
                .accessToken(jwtUtil.generateToken(user.getUsername()))
                .build();
    }
}
