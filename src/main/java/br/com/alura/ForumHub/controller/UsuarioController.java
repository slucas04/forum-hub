package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.dto.UsuarioCadastroDTO;
import br.com.alura.ForumHub.dto.*;
import br.com.alura.ForumHub.model.Usuario;
import br.com.alura.ForumHub.service.TokenService;
import br.com.alura.ForumHub.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UsuarioLoginDTO dados) {
        Usuario usuario = usuarioService.buscarPorEmail(dados.email());

        if (usuario == null || !passwordEncoder.matches(dados.senha(), usuario.getSenha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciais inválidas.");
        }

        return ResponseEntity.ok(new TokenDTO(tokenService.criaJWT(usuario)));
    }


    @PostMapping("/cadastro")
    @Transactional
    public ResponseEntity cadastro(@RequestBody @Valid UsuarioCadastroDTO dados) {
        Usuario usuario = new Usuario(dados);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuarioService.salvar(usuario);
        return ResponseEntity.ok(dados);
    }
}
