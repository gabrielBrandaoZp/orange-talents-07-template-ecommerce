package com.zupacademy.gabrielbr.ecommerce.controller.request;

import com.zupacademy.gabrielbr.ecommerce.model.Compra;
import com.zupacademy.gabrielbr.ecommerce.model.Produto;
import com.zupacademy.gabrielbr.ecommerce.model.Usuario;
import com.zupacademy.gabrielbr.ecommerce.model.enums.GatewayPagamento;
import com.zupacademy.gabrielbr.ecommerce.repository.ProdutoRepository;
import com.zupacademy.gabrielbr.ecommerce.validation.ExisteId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class CadastraCompraRequest {

    @NotNull
    @ExisteId(domainClass = Produto.class, fieldName = "id")
    private Long produtoId;

    @NotNull
    @Positive
    private Integer quantidade;
    private GatewayPagamento gatewayPagamento;

    public CadastraCompraRequest(Long produtoId, Integer quantidade, GatewayPagamento gatewayPagamento) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.gatewayPagamento = gatewayPagamento;
    }

    public Optional<Compra> converter(ProdutoRepository produtoRepository, Usuario comprador) {
        Optional<Produto> produtoObj = produtoRepository.findById(produtoId);

        // Already validated by annotation ExisteId
        Produto produto = produtoObj.get();
        if(produto.abateQuantidade(quantidade)) {
            return Optional.of(new Compra(produto, comprador, gatewayPagamento, quantidade));
        }

        return Optional.empty();
    }
}
