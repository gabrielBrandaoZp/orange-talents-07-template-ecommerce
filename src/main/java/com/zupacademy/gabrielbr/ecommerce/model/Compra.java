package com.zupacademy.gabrielbr.ecommerce.model;

import com.zupacademy.gabrielbr.ecommerce.model.enums.GatewayPagamento;
import com.zupacademy.gabrielbr.ecommerce.model.enums.StatusCompra;

import javax.persistence.*;
import java.math.BigDecimal;

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
}
