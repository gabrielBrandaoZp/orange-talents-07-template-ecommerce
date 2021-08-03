package com.zupacademy.gabrielbr.ecommerce.model;

import com.zupacademy.gabrielbr.ecommerce.controller.request.CadastraCaracteristicaRequest;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private final Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne
    private Usuario donoProduto;

    @ManyToOne
    private Categoria categoria;

    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private LocalDateTime dataCriacao;

    public Produto(Set<CadastraCaracteristicaRequest> caracteristicaRequests, String descricao, Usuario donoProduto, String nome, BigDecimal valor,
                   Integer quantidade, Categoria categoria) {

        caracteristicaRequests.forEach(cr -> this.caracteristicas.add(cr.converter(this)));
        this.descricao = descricao;
        this.donoProduto = donoProduto;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.dataCriacao = LocalDateTime.now();

        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no mínimo 3 características");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
