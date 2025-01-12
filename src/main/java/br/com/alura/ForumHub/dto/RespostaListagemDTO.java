package br.com.alura.ForumHub.dto;

import br.com.alura.ForumHub.model.Resposta;
import br.com.alura.ForumHub.model.Topico;
import br.com.alura.ForumHub.model.Usuario;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public record RespostaListagemDTO(Long id, String mensagem, LocalDateTime data, UsuarioListagemDTO autor, TopicoListagemDTO topico) {
    public RespostaListagemDTO(Resposta resposta) {
        this(resposta.getId(), resposta.getMensagem(), resposta.getData(), new UsuarioListagemDTO(resposta.getAutor()), new TopicoListagemDTO(resposta.getTopico()));
    }
}
