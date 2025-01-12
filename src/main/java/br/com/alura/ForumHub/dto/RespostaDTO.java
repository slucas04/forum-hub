package br.com.alura.ForumHub.dto;

import jakarta.validation.constraints.NotBlank;

public record RespostaDTO(@NotBlank String mensagem) {
}
