package br.com.alura.ForumHub.service;

import br.com.alura.ForumHub.dto.TopicoAtualizadoDTO;
import br.com.alura.ForumHub.dto.TopicoListagemDTO;
import br.com.alura.ForumHub.dto.TopicoPostagemDTO;
import br.com.alura.ForumHub.model.Topico;
import br.com.alura.ForumHub.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    @Autowired
    TopicoRepository topicoRepository;


    @Transactional
    public TopicoListagemDTO postaTopico(@Valid Topico topico) {
        boolean exists = topicoRepository.existsByTituloAndMensagemAndCurso(topico.getTitulo(), topico.getMensagem(), topico.getCurso());
        if (exists) {
            throw new RuntimeException("Tópico duplicado: já existe um tópico com o mesmo título e mensagem.");
        }

        topicoRepository.save(topico);
        return new TopicoListagemDTO(topico);
    }

    public void deletaTopico(Long id){
        var topico = topicoRepository.findById(id);
        if(topico.isPresent()){
            topico.get().setAtivo(false);
        } else {
            throw new RuntimeException("O tópico de id " + id + " não existe!");
        }

    }


    public List<TopicoListagemDTO> listaTopicos() {
        var lista = topicoRepository.findByAtivoTrue();
        if (lista.isPresent()){
            return lista.get().stream().map(t -> converteTopicoEmTopicoListagemDTO(t)).collect(Collectors.toList());
        } else {
            throw new RuntimeException("Não há tópicos!");
        }

    }

    public TopicoListagemDTO converteTopicoEmTopicoListagemDTO(Topico topico) {
        return new TopicoListagemDTO(topico);
    }

    public Topico atualizaTopico(Long id, TopicoAtualizadoDTO topicoAtualizadoDTO) {
        var topico = topicoRepository.findByIdAndAtivoTrue(id);
        if(topico.isPresent()){
            if(topicoAtualizadoDTO.titulo() != null){
                topico.get().setTitulo(topicoAtualizadoDTO.titulo());
            }
            if(topicoAtualizadoDTO.mensagem() != null){
                topico.get().setMensagem(topicoAtualizadoDTO.mensagem());
            }
            return topico.get();
        } else{
            throw new RuntimeException("O id informado na url não existe!");
        }
    }

    public TopicoListagemDTO buscaTopicoListagemPorId(Long id) {
        var topico = topicoRepository.findByIdAndAtivoTrue(id);
        if(topico.isPresent()){
            return new TopicoListagemDTO(topico.get());
        } else {
            throw new RuntimeException("Tópico de id " + id + " não existe!");
        }
    }

    public Topico buscaTopicoPorId(Long idTopico) {
        var topico = topicoRepository.findById(idTopico);
        if (topico.isPresent()){
            return topico.get();
        } else {
            throw new RuntimeException("O id informado na url não existe!");
        }
    }
}
