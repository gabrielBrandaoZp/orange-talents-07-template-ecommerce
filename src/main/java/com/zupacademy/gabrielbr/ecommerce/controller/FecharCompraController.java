package com.zupacademy.gabrielbr.ecommerce.controller;

import com.zupacademy.gabrielbr.ecommerce.controller.component.service.EventosNovaCompra;
import com.zupacademy.gabrielbr.ecommerce.controller.component.service.NotaFiscal;
import com.zupacademy.gabrielbr.ecommerce.controller.component.service.Ranking;
import com.zupacademy.gabrielbr.ecommerce.controller.exception.RecursoException;
import com.zupacademy.gabrielbr.ecommerce.controller.request.RetornoPagseguroRequest;
import com.zupacademy.gabrielbr.ecommerce.controller.request.RetornoPaypalRequest;
import com.zupacademy.gabrielbr.ecommerce.controller.response.TransacaoResponse;
import com.zupacademy.gabrielbr.ecommerce.model.Compra;
import com.zupacademy.gabrielbr.ecommerce.model.enums.StatusTransacao;
import com.zupacademy.gabrielbr.ecommerce.repository.CompraRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController

public class FecharCompraController {

    private CompraRepository compraRepository;
    private NotaFiscal notaFiscal;
    private Ranking ranking;
    private EventosNovaCompra eventosNovaCompra;

    public FecharCompraController(CompraRepository compraRepository, NotaFiscal notaFiscal, Ranking ranking, EventosNovaCompra eventosNovaCompra) {
        this.compraRepository = compraRepository;
        this.notaFiscal = notaFiscal;
        this.ranking = ranking;
        this.eventosNovaCompra = eventosNovaCompra;
    }

    @PostMapping("/retorno-pagseguro/{id}")
    public ResponseEntity<TransacaoResponse> processamentoPagseguro(@PathVariable Long id, @Valid RetornoPagseguroRequest request) {
        return processa(id, request);
    }

    @PostMapping("/retorno-paypal/{id}")
    public ResponseEntity<TransacaoResponse> processamentoPagseguro(@PathVariable Long id, @Valid RetornoPaypalRequest request) {
        return processa(id, request);
    }

    private ResponseEntity<TransacaoResponse> processa(Long compraId, RetornoGatewayPagamento retorno) {
        Compra compra = compraRepository
                .findById(compraId)
                .orElseThrow(() -> new RecursoException("Recurso não encontrado para o id informado"));
        compra.adicionaTransacao(retorno);
        compraRepository.save(compra);
        StatusTransacao status = eventosNovaCompra.processa(compra);

        return ResponseEntity.ok(new TransacaoResponse(status));
    }
}
