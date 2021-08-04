package com.zupacademy.gabrielbr.ecommerce.controller;

import com.zupacademy.gabrielbr.ecommerce.controller.component.Emails;
import com.zupacademy.gabrielbr.ecommerce.controller.request.CadastraPerguntaRequest;
import com.zupacademy.gabrielbr.ecommerce.controller.response.PerguntaResponse;
import com.zupacademy.gabrielbr.ecommerce.model.Pergunta;
import com.zupacademy.gabrielbr.ecommerce.model.Produto;
import com.zupacademy.gabrielbr.ecommerce.model.Usuario;
import com.zupacademy.gabrielbr.ecommerce.repository.PerguntaRepository;
import com.zupacademy.gabrielbr.ecommerce.repository.ProdutoRepository;
import com.zupacademy.gabrielbr.ecommerce.security.AutenticacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PerguntaController {

    private AutenticacaoService autenticacaoService;
    private ProdutoRepository produtoRepository;
    private PerguntaRepository perguntaRepository;
    private Emails emails;

    public PerguntaController(AutenticacaoService autenticacaoService, ProdutoRepository produtoRepository, PerguntaRepository perguntaRepository, Emails emails) {
        this.autenticacaoService = autenticacaoService;
        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
        this.emails = emails;
    }

    @PostMapping("produtos/{id}/perguntas")
    public ResponseEntity<PerguntaResponse> cadastraPergunta(@PathVariable Long id, @RequestBody @Valid CadastraPerguntaRequest request) {
        Optional<Produto> produtoObj = produtoRepository.findById(id);
        if(produtoObj.isPresent()) {
            Produto produto = produtoObj.get();
            Optional<Usuario> usuario = autenticacaoService.usuarioAutenticado();
            Pergunta pergunta = request.converter(produto, usuario.get());
            perguntaRepository.save(pergunta);
            emails.enviaEmailNovaPergunta(pergunta);

            return ResponseEntity.ok(new PerguntaResponse(pergunta));
        }

        return ResponseEntity.badRequest().build();
    }
}
