package br.com.alura.ForumHub.repository;

import br.com.alura.ForumHub.dto.RespostaListagemDTO;
import br.com.alura.ForumHub.model.Resposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

    @Query("SELECT r FROM Resposta r WHERE r.topico.id = :id AND r.ativo = true")
    Optional<List<RespostaListagemDTO>> buscaPorIdDoTopico(Long id);

    Optional<Resposta> findByIdAndAtivoTrue(Long id);
}
