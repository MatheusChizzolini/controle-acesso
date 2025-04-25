package com.example.controle_acesso.services;

import com.example.controle_acesso.entities.Usuario;
import com.example.controle_acesso.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario getById(Long id) {
        return usuarioRepository.findById(id).orElse(null); // Se não achou o objeto, retorna nulo.
    }

    public Usuario save(Usuario usuario) {
        try {
            return usuarioRepository.save(usuario);
        } catch (Exception error) {
            return null;
        }
    }

    public boolean delete(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        try {
            usuarioRepository.delete(usuario);
            return true;
        } catch (Exception erro) {
            return false;
        }
    }
}
