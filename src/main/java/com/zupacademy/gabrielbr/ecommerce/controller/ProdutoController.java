package com.zupacademy.gabrielbr.ecommerce.controller;

import com.zupacademy.gabrielbr.ecommerce.controller.request.CadastroProdutoRequest;
import com.zupacademy.gabrielbr.ecommerce.model.Produto;
import com.zupacademy.gabrielbr.ecommerce.model.Usuario;
import com.zupacademy.gabrielbr.ecommerce.repository.CategoriaRepository;
import com.zupacademy.gabrielbr.ecommerce.repository.ProdutoRepository;
import com.zupacademy.gabrielbr.ecommerce.security.AutenticacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private AutenticacaoService autenticacaoService;
    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;

    public ProdutoController(AutenticacaoService autenticacaoService, ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.autenticacaoService = autenticacaoService;
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    public ResponseEntity<Void> cadastrar(@RequestBody @Valid CadastroProdutoRequest produtoRequest) {
        Optional<Usuario> usuario = autenticacaoService.usuarioAutenticado();
        if(usuario.isPresent()) {
            Optional<Produto> produtoObj = produtoRequest.converter(categoriaRepository, usuario.get());
            if(produtoObj.isPresent()) {
                produtoRepository.save(produtoObj.get());
                return ResponseEntity.ok().build();
            }
        }

        return ResponseEntity.badRequest().build();
    }
}
