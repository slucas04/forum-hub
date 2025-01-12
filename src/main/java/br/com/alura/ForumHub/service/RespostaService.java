package br.com.alura.ForumHub.service;

import br.com.alura.ForumHub.dto.RespostaAtualizadaDTO;
import br.com.alura.ForumHub.dto.RespostaDTO;
import br.com.alura.ForumHub.dto.RespostaListagemDTO;
import br.com.alura.ForumHub.model.Resposta;
import br.com.alura.ForumHub.repository.RespostaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RespostaService {
    @Autowired
    RespostaRepository respostaRepository;
    @Autowired
    HttpServletRequest request;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    TopicoService topicoService;

    public RespostaListagemDTO postaComentario(Long id, @Valid RespostaDTO respostaDTO) {
        String authorizationHeader = request.getHeader("Authorization");
        var autor = usuarioService.buscaPorToken(authorizationHeader);
        var topico = topicoService.buscaTopicoPorId(id);
        var resposta = new Resposta(topico, respostaDTO.mensagem(),  autor);
        respostaRepository.save(resposta);
        return new RespostaListagemDTO(resposta);
    }

    public List<RespostaListagemDTO> buscaComentariosPorTopico(Long id) {
        var respostaListagemDTOList = respostaRepository.buscaPorIdDoTopico(id);
        if (respostaListagemDTOList.isPresent()){
            return respostaListagemDTOList.get();
        } else {
            throw new RuntimeException("Não há respostas para o tópico de id informado na url!");
        }
    }

    public Resposta atualizaResposta(Long id, RespostaAtualizadaDTO respostaAtualizadaDTO) {
        var resposta = respostaRepository.findByIdAndAtivoTrue(id);
        if(resposta.isPresent()){
            resposta.get().setMensagem(respostaAtualizadaDTO.mensagem());
            return resposta.get();
        } else{
            throw new RuntimeException("O id informado na url não existe!");
        }
    }

    public void deletaResposta(Long id){
        var resposta = respostaRepository.findById(id);
        if(resposta.isPresent()){
            resposta.get().setAtivo(false);
        } else {
            throw new RuntimeException("A resposta de id " + id + " não existe!");
        }

    }

    public Resposta buscaComentarioPorId(Long id) {
        var resposta = respostaRepository.findById(id);
        if(resposta.isPresent()){
            return resposta.get();
        } else{
            throw new RuntimeException("Id inválido!");
        }
    }
}
