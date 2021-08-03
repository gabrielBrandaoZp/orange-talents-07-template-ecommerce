package com.zupacademy.gabrielbr.ecommerce.controller;

import com.zupacademy.gabrielbr.ecommerce.controller.component.UploaderFake;
import com.zupacademy.gabrielbr.ecommerce.controller.request.CadastraImagemProduto;
import com.zupacademy.gabrielbr.ecommerce.controller.request.CadastroProdutoRequest;
import com.zupacademy.gabrielbr.ecommerce.model.Produto;
import com.zupacademy.gabrielbr.ecommerce.model.Usuario;
import com.zupacademy.gabrielbr.ecommerce.repository.CategoriaRepository;
import com.zupacademy.gabrielbr.ecommerce.repository.ProdutoRepository;
import com.zupacademy.gabrielbr.ecommerce.security.AutenticacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private AutenticacaoService autenticacaoService;
    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;
    private UploaderFake uploaderFake;


    public ProdutoController(AutenticacaoService autenticacaoService, ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository,
                             UploaderFake uploaderFake) {
        this.autenticacaoService = autenticacaoService;
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.uploaderFake = uploaderFake;
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

    @PostMapping("/{id}/imagens")
    public ResponseEntity<Void> inserirImagens(@PathVariable Long id, @Valid CadastraImagemProduto imagensProduto) {
        Optional<Produto> produtoObj = produtoRepository.findById(id);
        if(produtoObj.isPresent()) {
            Produto produto = produtoObj.get();
            Optional<Usuario> usuarioObj = autenticacaoService.usuarioAutenticado();
            if(!produto.pertenceAoDono(usuarioObj.get())) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

            Set<String> links = uploaderFake.envia(imagensProduto.getImagens());
            produto.associaImagens(links);
            produtoRepository.save(produto);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }
}
