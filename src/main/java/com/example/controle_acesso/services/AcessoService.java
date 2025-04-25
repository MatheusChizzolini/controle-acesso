package com.example.controle_acesso.services;

import com.example.controle_acesso.entities.Usuario;
import com.example.controle_acesso.repositories.UsuarioRepository;
import com.example.controle_acesso.security.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcessoService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String autenticar(String nome, String senha) {
        Usuario usuario = usuarioRepository.findByNome(nome);
        String token = null;
        if (usuario != null && usuario.getSenha().equals(senha)) {
            token = JWTTokenProvider.getToken(nome, "" + usuario.getNivel());
        }

        return token;
    }
}
