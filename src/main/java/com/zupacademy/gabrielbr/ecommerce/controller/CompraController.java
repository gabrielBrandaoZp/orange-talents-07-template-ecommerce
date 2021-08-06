package com.zupacademy.gabrielbr.ecommerce.controller;

import com.zupacademy.gabrielbr.ecommerce.controller.component.service.Emails;
import com.zupacademy.gabrielbr.ecommerce.controller.request.CadastraCompraRequest;
import com.zupacademy.gabrielbr.ecommerce.model.Compra;
import com.zupacademy.gabrielbr.ecommerce.model.Usuario;
import com.zupacademy.gabrielbr.ecommerce.model.enums.GatewayPagamento;
import com.zupacademy.gabrielbr.ecommerce.repository.CompraRepository;
import com.zupacademy.gabrielbr.ecommerce.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/compras")
public class CompraController {

    private CompraRepository compraRepository;
    private ProdutoRepository produtoRepository;
    private Emails emails;

    public CompraController(CompraRepository compraRepository, ProdutoRepository produtoRepository, Emails emails) {
        this.compraRepository = compraRepository;
        this.produtoRepository = produtoRepository;
        this.emails = emails;
    }

    @PostMapping
    public ResponseEntity<Void> comprar(@RequestBody @Valid CadastraCompraRequest request, @AuthenticationPrincipal Usuario usuario, UriComponentsBuilder builder) {
        Optional<Compra> compraObj = request.converter(produtoRepository, usuario);
        if (compraObj.isPresent()) {
            Compra compra = compraObj.get();
            compraRepository.save(compra);
            emails.enviaEmailNovaCompra(compra);

            GatewayPagamento gateway = compra.getGateway();
            String urlDoGateway = gateway.criaUrlRetorno(compra, builder);
            URI uri = URI.create(urlDoGateway);
            return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
        }

        return ResponseEntity.badRequest().build();
    }
}
