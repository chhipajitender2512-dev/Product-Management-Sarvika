package com.sarvika.productmanagement.service;

import com.sarvika.productmanagement.domain.request.LoginRequest;

public interface AuthService {
    String loginUser(LoginRequest request);
}
