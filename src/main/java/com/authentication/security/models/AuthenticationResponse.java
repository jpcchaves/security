package com.authentication.security.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"firstname", "lastname", "email", "token"})
public class AuthenticationResponse {

  private String token;
  private String firstname;
  private String lastname;
  private String email;

}
