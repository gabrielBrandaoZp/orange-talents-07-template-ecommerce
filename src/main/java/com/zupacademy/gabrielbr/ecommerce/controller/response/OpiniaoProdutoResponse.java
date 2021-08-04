package com.zupacademy.gabrielbr.ecommerce.controller.response;

import com.zupacademy.gabrielbr.ecommerce.model.Opiniao;

public class OpiniaoProdutoResponse {

    private String titulo;
    private Integer nota;
    private String descricao;

    public OpiniaoProdutoResponse(Opiniao opiniao) {
        this.titulo = opiniao.getTitulo();
        this.nota = opiniao.getNota();
        this.descricao = opiniao.getDescricao();
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }
}
