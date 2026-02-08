package com.joseneto.backendlocacao.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginResponseDTO {

    private String token;

    public LoginResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
