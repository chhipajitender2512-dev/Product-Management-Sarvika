package com.sarvika.productmanagement.security.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class SarvikaAuthenticationFilter extends OncePerRequestFilter {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private final AuthenticationManager authenticate;

    private boolean checkSwaggerUrls(HttpServletRequest request) {
        return request.getServletPath().contains("/swagger-ui") ||
                request.getServletPath().contains("/favicon.ico") ||
                request.getServletPath().contains("/api-docs") ||
                request.getServletPath().contains("/health") ||
                request.getServletPath().contains("/actuator") ||
                request.getServletPath().contains("/submitFormThroughService") ||
                request.getServletPath().contains("/callback/profile-status");
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        if (checkSwaggerUrls(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        logger.info(request.getRequestURL().toString());
        String authorizationToken = request.getHeader(AUTHORIZATION_HEADER);

        if (authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
            authorizationToken = authorizationToken.substring(7);
        }

        try {
            if (authorizationToken != null) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(null, authorizationToken);
                Authentication authResult = authenticate.authenticate(authentication);

                log.info("AuthResult {}", authResult);
                if (authResult.isAuthenticated()) {
                    log.info("AuthResult.isAuthenticated {}", authResult.isAuthenticated());
                    SecurityContextHolder.getContext().setAuthentication(authResult);
                    filterChain.doFilter(request, response);
                    SecurityContextHolder.clearContext();
                } else {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
                }
            } else {
                log.debug("Auth token is null. Bypassing the authentication process for request [{}] .",
                        request.getRequestURL().toString());
                filterChain.doFilter(request, response);
            }
        } catch (AuthenticationException authException) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
    }

}
