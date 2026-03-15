package com.sarvika.productmanagement.security;

import com.sarvika.productmanagement.exception.TokenExpiredException;
import com.sarvika.productmanagement.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class SarvikaAuthenticationProvider implements AuthenticationProvider {
    private final JwtUtil jwtUtil;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        String token = (String) authentication.getCredentials();

        if (jwtUtil.isTokenExpired(token)) {
            throw new TokenExpiredException("Token has expired");
        }

        return new UsernamePasswordAuthenticationToken(token, null, Collections.emptyList());
    }

    @Override
    public boolean supports(@NonNull Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
