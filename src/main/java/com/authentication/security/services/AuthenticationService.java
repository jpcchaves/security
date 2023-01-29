package com.authentication.security.services;

import com.authentication.security.data.vo.v1.AuthenticationResponseVO;
import com.authentication.security.data.vo.v1.RegisterResponseVO;
import com.authentication.security.models.auth.AuthenticationRequest;
import com.authentication.security.models.auth.RegisterRequest;
import com.authentication.security.models.user.User;
import com.authentication.security.models.user.enums.Role;
import com.authentication.security.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public RegisterResponseVO register(RegisterRequest request) {
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();

    repository.save(user);

    var jwtToken = jwtService.generateToken(user);

    return RegisterResponseVO.builder()
        .firstname(user.getFirstname())
        .lastname(user.getLastname())
        .email(user.getEmail())
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponseVO authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );

    var user = repository.findByEmail(request.getEmail())
        .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

    var jwtToken = jwtService.generateToken(user);

    return AuthenticationResponseVO.builder()
        .token(jwtToken)
        .build();
  }
}
