package br.com.alura.ForumHub.dto;
import jakarta.validation.constraints.NotBlank;


public record TopicoPostagemDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensagem,
        @NotBlank
        String curso) {
}
