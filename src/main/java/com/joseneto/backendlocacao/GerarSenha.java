package com.joseneto.backendlocacao;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenha {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String senha = "123456";
        String senhaCriptografada = encoder.encode(senha);

        System.out.println("Senha criptografada");
        System.out.println(senhaCriptografada);
    }
}
