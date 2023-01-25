package com.authentication.security.config;

import com.authentication.security.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  private static boolean hasHeader(String authHeader) {
    return authHeader == null;
  }

  private static boolean hasBearer(String authHeader) {
    return !authHeader.startsWith("Bearer ");
  }

  private static boolean validateHeader(String authHeader) {
    return hasHeader(authHeader) || hasBearer(authHeader);
  }

  @Override
  protected void doFilterInternal(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain) throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userEmail;

    if (validateHeader(authHeader)) {
      filterChain.doFilter(request, response);
      return;
    }

    jwt = authHeader.substring(7);
    userEmail = jwtService.extractUsername(jwt);
  }
}
