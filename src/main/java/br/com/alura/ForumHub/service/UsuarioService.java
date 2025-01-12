package br.com.alura.ForumHub.service;

import br.com.alura.ForumHub.model.Usuario;
import br.com.alura.ForumHub.repository.UsuarioRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    TokenService tokenService;

    public void salvar(Usuario usuario){
        usuarioRepository.save(usuario);
    }

    public Usuario buscarPorEmail(@NotBlank @Email String email) {
        var usuario = usuarioRepository.findByEmail(email);
        if(usuario.isPresent()) {
            return usuario.get();
        } else {
            throw new RuntimeException("Não há usuários com o e-mail informado!");
        }
    }

    public Usuario buscaPorToken(String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        String email = tokenService.verificaJWT(token);
        return buscarPorEmail(email);
    }
}
