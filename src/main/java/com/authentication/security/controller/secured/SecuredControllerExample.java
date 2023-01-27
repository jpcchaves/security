package com.authentication.security.controller.secured;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Secured Routes", description = "All Routes Above are secured and need authentication to access them")
@RequestMapping("/api/v1/secured-example")
public class SecuredControllerExample {

  @GetMapping
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("Hello from secured endpoint!");
  }

  @GetMapping(value = "/sayHello2")
  public ResponseEntity<String> sayHello2(){
    return ResponseEntity.ok("Testando");
  }

}
