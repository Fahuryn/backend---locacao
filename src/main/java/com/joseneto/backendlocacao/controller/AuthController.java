package com.joseneto.backendlocacao.controller;

import com.joseneto.backendlocacao.dto.LoginRequestDTO;
import com.joseneto.backendlocacao.dto.LoginResponseDTO;
import com.joseneto.backendlocacao.security.JwtUtil;
import com.joseneto.backendlocacao.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody @Valid LoginRequestDTO dto
    ) {
        String token = service.login(dto);
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

}
