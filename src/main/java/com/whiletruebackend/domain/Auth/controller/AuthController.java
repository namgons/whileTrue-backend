package com.whiletruebackend.domain.Auth.controller;

import com.whiletruebackend.domain.Auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/oauth/{accessCode}")
    public ResponseEntity<?> getAccessToken(@PathVariable String accessCode) {
        authService.requestAccessToken(accessCode);
        return ResponseEntity.ok().build();
    }
}
