package com.zupacademy.gabrielbr.ecommerce.model;

import com.zupacademy.gabrielbr.ecommerce.controller.RetornoGatewayPagamento;
import com.zupacademy.gabrielbr.ecommerce.model.enums.GatewayPagamento;
import com.zupacademy.gabrielbr.ecommerce.model.enums.StatusCompra;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Usuario comprador;

    @Enumerated(EnumType.STRING)
    private GatewayPagamento gatewayPagamento;

    @Enumerated(EnumType.STRING)
    private StatusCompra status;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
    private Set<Transacao> transacoes = new HashSet<>();

    private BigDecimal precoUnitario;

    private Integer quantidade;

    @Deprecated
    public Compra() {}

    public Compra(Produto produto, Usuario comprador, GatewayPagamento gatewayPagamento, Integer quantidade) {
        this.produto = produto;
        this.comprador = comprador;
        this.gatewayPagamento = gatewayPagamento;
        this.precoUnitario = produto.getValor();
        this.quantidade = quantidade;
        this.status = StatusCompra.INICIADA;
    }

    public Long getId() {
        return id;
    }

    public GatewayPagamento getGateway() {
        return gatewayPagamento;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Produto getProduto() {
        return produto;
    }

    public StatusCompra getStatus() {
        return status;
    }

    public void setStatus(StatusCompra status) {
        this.status = status;
    }

    public void adicionaTransacao(RetornoGatewayPagamento request) {
        Transacao novaTransacao = request.paraTransacao(this);
        Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já exista uma transacao igual a essa processada");

        Assert.isTrue(transacoesConcluidasComSucesso().isEmpty(), "Essa compra já foi concluida com sucesso");

        this.transacoes.add(novaTransacao);
    }

    public boolean processadaComSucesso() {
        return !transacoesConcluidasComSucesso().isEmpty();
    }

    private Set<Transacao> transacoesConcluidasComSucesso() {
        return this.transacoes
                .stream()
                .filter(Transacao::concluidaComSucesso)
                .collect(Collectors.toSet());
    }
}
