package com.authentication.security.controller.auth;

import com.authentication.security.models.AuthenticationRequest;
import com.authentication.security.models.AuthenticationResponse;
import com.authentication.security.models.RegisterRequest;
import com.authentication.security.services.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication", description = "This controller allows users to authenticate and register")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthenticationController {

  private final AuthenticationService authService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    return ResponseEntity.ok(authService.register(request));
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody AuthenticationRequest request
  ) {
    return ResponseEntity.ok(authService.authenticate(request));
  }
}
