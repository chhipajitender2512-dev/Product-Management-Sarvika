package com.sarvika.productmanagement.service;

import com.sarvika.productmanagement.domain.reponse.LoginResponse;
import com.sarvika.productmanagement.domain.request.LoginRequest;

public interface AuthService {
    LoginResponse loginUser(LoginRequest request);
}
