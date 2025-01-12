package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.dto.*;
import br.com.alura.ForumHub.model.Resposta;
import br.com.alura.ForumHub.service.RespostaService;
import br.com.alura.ForumHub.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/respostas")
public class RespostaController {
    @Autowired
    RespostaService respostaService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    UsuarioService usuarioService;

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity postaResposta(@PathVariable Long id, @RequestBody @Valid RespostaDTO respostaDTO){
        RespostaListagemDTO respostaListagemDTO = respostaService.postaComentario(id, respostaDTO);
        return ResponseEntity.ok(respostaListagemDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity listaRespostasPorTopico(@PathVariable Long id){
        List<RespostaListagemDTO> respostaListagemDTOList = respostaService.buscaComentariosPorTopico(id);
        return ResponseEntity.ok(respostaListagemDTOList);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizaResposta(@PathVariable Long id, @RequestBody RespostaAtualizadaDTO respostaAtualizadaDTO){
        String authorizationHeader = request.getHeader("Authorization");
        var autor = usuarioService.buscaPorToken(authorizationHeader);
        Resposta resposta = respostaService.atualizaResposta(id, respostaAtualizadaDTO);
        if(resposta.getAutor() == autor){
            return ResponseEntity.ok(new RespostaListagemDTO(resposta));
        } else {
            throw new RuntimeException("Você não é dono deste comentário para editá-lo!");
        }

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletaResposta(@PathVariable Long id){
        String authorizationHeader = request.getHeader("Authorization");
        var autor = usuarioService.buscaPorToken(authorizationHeader);
        Resposta resposta = respostaService.buscaComentarioPorId(id);
        if(resposta.getAutor() == autor) {
            respostaService.deletaResposta(id);
            return ResponseEntity.ok("Resposta de id " + id + " deletada.");
        } else{
            throw new RuntimeException("Você não é dono deste comentário para deletá-lo!");
        }

    }

}
