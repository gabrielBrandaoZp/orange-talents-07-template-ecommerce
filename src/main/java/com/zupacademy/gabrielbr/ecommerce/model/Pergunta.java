package com.zupacademy.gabrielbr.ecommerce.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Pergunta implements Comparable<Pergunta> {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pergunta pergunta = (Pergunta) o;
        return titulo.equals(pergunta.titulo) && autor.equals(pergunta.autor) && produto.equals(pergunta.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, autor, produto);
    }

    @Override
    public int compareTo(Pergunta o) {
        return this.titulo.compareTo(o.getTitulo());
    }
}
