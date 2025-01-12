package br.com.alura.ForumHub.dto;

import jakarta.validation.constraints.NotBlank;

public record RespostaAtualizadaDTO(@NotBlank String mensagem) {
}
