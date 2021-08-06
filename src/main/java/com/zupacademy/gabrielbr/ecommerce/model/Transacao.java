package com.zupacademy.gabrielbr.ecommerce.model;

import com.zupacademy.gabrielbr.ecommerce.model.enums.StatusTransacao;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String idTransacaoGateway;

    @ManyToOne
    private Compra compra;

    @Enumerated(EnumType.STRING)
    private StatusTransacao statusTransacao;

    private LocalDateTime dataTransacao;

    @Deprecated
    public Transacao() {}

    public Transacao(StatusTransacao status, String idTransacaoGateway, Compra compra) {
        this.idTransacaoGateway = idTransacaoGateway;
        this.statusTransacao = status;
        this.compra = compra;
        dataTransacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }

    public boolean concluidaComSucesso() {
        return this.statusTransacao.equals(StatusTransacao.SUCESSO);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transacao transacao = (Transacao) o;
        return idTransacaoGateway.equals(transacao.idTransacaoGateway);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransacaoGateway);
    }

}
