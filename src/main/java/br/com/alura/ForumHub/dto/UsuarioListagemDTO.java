package br.com.alura.ForumHub.dto;

import br.com.alura.ForumHub.model.Usuario;
import jakarta.validation.constraints.NotBlank;

public record UsuarioListagemDTO(@NotBlank String username) {
    public UsuarioListagemDTO(Usuario usuario) {
        this(usuario.getUsername());
    }
}
