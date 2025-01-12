package br.com.alura.ForumHub.dto;
import br.com.alura.ForumHub.model.Topico;
import java.time.LocalDateTime;

public record TopicoListagemDTO(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        UsuarioListagemDTO autor,
        String curso) {
    public TopicoListagemDTO(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getDataCriacao(), new UsuarioListagemDTO(topico.getAutor()), topico.getCurso());
    }
}
