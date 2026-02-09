package com.joseneto.backendlocacao.service;

import com.joseneto.backendlocacao.dto.LoginRequestDTO;
import com.joseneto.backendlocacao.entity.Usuario;
import com.joseneto.backendlocacao.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UsuarioRepository repository;

    @Mock
    private PasswordEncoder encoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    private Usuario usuario;
    private LoginRequestDTO loginDTO;

    @BeforeEach
    void setup() {
        usuario = new Usuario();
        usuario.setEmail("admin@teste.com");
        usuario.setSenha("senha_criptografada");

        loginDTO = new LoginRequestDTO();
        loginDTO.setEmail("admin@teste.com");
        loginDTO.setSenha("123456");
    }

    @Test
    @DisplayName("Deve retornar o token quando as credenciais forem válidas")
    void loginSucesso() {
        when(repository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(usuario));
        when(encoder.matches(eq("123456"), eq("senha_criptografada"))).thenReturn(true);
        when(jwtService.gerarToken(anyString())).thenReturn("token_fake_123");

        String token = authService.login(loginDTO);

        assertNotNull(token);
        assertEquals("token_fake_123", token);
        verify(repository, times(1)).findByEmail(loginDTO.getEmail());
    }

    @Test
    @DisplayName("Deve lançar exceção quando a senha for inválida")
    void loginSenhaInvalida() {
        when(repository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(usuario));
        when(encoder.matches(anyString(), anyString())).thenReturn(false);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            authService.login(loginDTO);
        });

        assertEquals("Senha inválida", exception.getMessage());
    }
}