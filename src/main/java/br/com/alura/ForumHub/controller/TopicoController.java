package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.dto.TopicoAtualizadoDTO;
import br.com.alura.ForumHub.dto.TopicoPostagemDTO;
import br.com.alura.ForumHub.dto.TopicoListagemDTO;
import br.com.alura.ForumHub.model.Topico;
import br.com.alura.ForumHub.service.TopicoService;
import br.com.alura.ForumHub.service.UsuarioService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {
    @Autowired
    TopicoService topicoService;
    @Autowired
    HttpServletRequest request;
    @Autowired
    UsuarioService usuarioService;

    @PostMapping
    @Transactional
    public ResponseEntity postaTopico(@RequestBody @Valid TopicoPostagemDTO topicoPostagemDTO){
        String authorizationHeader = request.getHeader("Authorization");
        var autor = usuarioService.buscaPorToken(authorizationHeader);
        var topico = new Topico(topicoPostagemDTO.titulo(), topicoPostagemDTO.mensagem(),  autor, topicoPostagemDTO.curso());
        TopicoListagemDTO topicoListagemDTO = topicoService.postaTopico(topico);
        return ResponseEntity.ok(topicoListagemDTO);
    }

    @GetMapping
    public ResponseEntity listaTopicos(){
        List<TopicoListagemDTO> topicoListagemDTOList = topicoService.listaTopicos();
        return ResponseEntity.ok(topicoListagemDTOList);
    }

    @GetMapping("{id}")
    public ResponseEntity listaTopicos(@PathVariable Long id){
        var topicoListagemDTO = topicoService.buscaTopicoListagemPorId(id);
        return ResponseEntity.ok(topicoListagemDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizaTopico(@PathVariable Long id, @RequestBody TopicoAtualizadoDTO topicoAtualizadoDTO){
        String authorizationHeader = request.getHeader("Authorization");
        var autor = usuarioService.buscaPorToken(authorizationHeader);
        Topico topico = topicoService.atualizaTopico(id, topicoAtualizadoDTO);
        if (topico.getAutor() == autor){
            return ResponseEntity.ok(new TopicoListagemDTO(topico));
        } else {
            throw new RuntimeException("Você não é dono deste tópico para alterá-lo!");
        }

    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletaTopico(@PathVariable Long id){
        String authorizationHeader = request.getHeader("Authorization");
        var autor = usuarioService.buscaPorToken(authorizationHeader);
        var topico = topicoService.buscaTopicoPorId(id);
        if(topico.getAutor() == autor){
            topicoService.deletaTopico(id);
            return ResponseEntity.ok("Tópico de id " + id + " deletado.");
        } else {
            throw new RuntimeException("Você não é dono deste tópico para deletá-lo!");
        }

    }

}
