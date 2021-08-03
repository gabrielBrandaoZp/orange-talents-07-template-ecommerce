package com.zupacademy.gabrielbr.ecommerce.model;

import javax.persistence.*;

@Entity
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Integer nota;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Produto produto;

    public Opiniao(String titulo, Integer nota, String descricao, Usuario autor, Produto produto) {
        this.titulo = titulo;
        this.nota = nota;
        this.descricao = descricao;
        this.autor = autor;
        this.produto = produto;
    }
}
