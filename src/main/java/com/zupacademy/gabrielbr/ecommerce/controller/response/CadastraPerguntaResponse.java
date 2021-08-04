package com.zupacademy.gabrielbr.ecommerce.controller.response;

import com.zupacademy.gabrielbr.ecommerce.model.Pergunta;

import java.time.LocalDateTime;

public class CadastraPerguntaResponse {

    private String titulo;
    private LocalDateTime dataCriacao;

    public CadastraPerguntaResponse(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
        this.dataCriacao = pergunta.getDataCriacao();
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
