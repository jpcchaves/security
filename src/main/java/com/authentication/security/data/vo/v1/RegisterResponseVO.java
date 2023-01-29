package com.authentication.security.data.vo.v1;

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
public class RegisterResponseVO {

  private String token;
  private String firstname;
  private String lastname;
  private String email;

}
