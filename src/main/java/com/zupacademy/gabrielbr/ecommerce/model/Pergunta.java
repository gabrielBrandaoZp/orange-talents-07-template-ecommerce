package com.zupacademy.gabrielbr.ecommerce.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private LocalDateTime dataCriacao;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public Pergunta() {}

    public Pergunta(String titulo, Usuario autor, Produto produto) {
        this.titulo = titulo;
        this.autor = autor;
        this.produto = produto;
        dataCriacao = LocalDateTime.now();
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public Usuario getAutor() {
        return autor;
    }

    public Produto getProduto() {
        return produto;
    }
}
