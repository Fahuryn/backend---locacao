package com.joseneto.backendlocacao.service;

import com.joseneto.backendlocacao.dto.LoginRequestDTO;
import com.joseneto.backendlocacao.entity.Usuario;
import com.joseneto.backendlocacao.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder encoder;
    private final JwtService jwtService;

    public AuthService(
            UsuarioRepository repository,
            PasswordEncoder encoder,
            JwtService jwtService
    ) {
        this.repository = repository;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    public String login(LoginRequestDTO dto) {
        Usuario usuario = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!encoder.matches(dto.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        return jwtService.gerarToken(usuario.getEmail());
    }
}
