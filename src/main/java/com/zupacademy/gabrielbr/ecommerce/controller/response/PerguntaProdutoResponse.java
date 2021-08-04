package com.zupacademy.gabrielbr.ecommerce.controller.response;

import com.zupacademy.gabrielbr.ecommerce.model.Pergunta;

public class PerguntaProdutoResponse implements Comparable<PerguntaProdutoResponse>{

    private String titulo;
    private String emailAutor;
    private String dataCriacao;

    public PerguntaProdutoResponse(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
        this.emailAutor = pergunta.getAutor().getEmail();
        this.dataCriacao = pergunta.getDataCriacao().toString();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEmailAutor() {
        return emailAutor;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    @Override
    public int compareTo(PerguntaProdutoResponse o) {
        return this.titulo.compareTo(o.getTitulo());
    }
}
