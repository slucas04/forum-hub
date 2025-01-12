package br.com.alura.ForumHub.repository;

import br.com.alura.ForumHub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Optional<List<Topico>> findByAtivoTrue();

    boolean existsByTituloAndMensagemAndCurso(String titulo, String mensagem, String curso);

    Optional<Topico> findByIdAndAtivoTrue(Long id);
}
