package com.example.controle_acesso.rest_controllers;

import com.example.controle_acesso.entities.Usuario;
import com.example.controle_acesso.repositories.UsuarioRepository;
import com.example.controle_acesso.security.JWTTokenProvider;
import com.example.controle_acesso.services.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("apis/user")
public class UsuarioRestController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @GetMapping
    public ResponseEntity<Object> getUsuarios() {
        List<Usuario> usuarioList = usuarioService.getAll();
        if (usuarioList.isEmpty()) 
            return ResponseEntity.badRequest().body("Nenhum usuário encontrado.");
        return ResponseEntity.ok(usuarioList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUsuarioId(@PathVariable Long id) {
        Usuario usuario = usuarioService.getById(id);
        if (usuario == null)
            return ResponseEntity.badRequest().body("Usuário não encontrado.");
        return ResponseEntity.ok(usuario);
    }

    @PostMapping
    public ResponseEntity<Object> addUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.save(usuario);
        if (novoUsuario != null)
            return ResponseEntity.ok(novoUsuario);
        return ResponseEntity.badRequest().body("Erro ao cadastrar usuário.");
    }

    @PutMapping
    public ResponseEntity<Object> updateUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.save(usuario);
        if (novoUsuario != null)
            return ResponseEntity.ok(novoUsuario);
        return ResponseEntity.badRequest().body("Erro ao atualizar usuário.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delUsuario(@PathVariable Long id) {
        if (usuarioService.delete(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.badRequest().body("Erro ao apagar o usuário.");
    }

    /*
    @GetMapping("get-by-name/{name}")
    public ResponseEntity<Object> getUsuarioNome(@PathVariable(name = "name") String nome) {
        Usuario usuario;
        usuario = usuarioRepository.findByNome(nome);
        if (usuario == null)
                return ResponseEntity.badRequest().body("Usuário não encontrado.");
        return ResponseEntity.ok(usuario);
    }
    */
}
