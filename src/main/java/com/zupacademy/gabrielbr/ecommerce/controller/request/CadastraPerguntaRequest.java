package com.zupacademy.gabrielbr.ecommerce.controller.request;

import com.zupacademy.gabrielbr.ecommerce.model.Pergunta;
import com.zupacademy.gabrielbr.ecommerce.model.Produto;
import com.zupacademy.gabrielbr.ecommerce.model.Usuario;

import javax.validation.constraints.NotBlank;

public class CadastraPerguntaRequest {

    @NotBlank
    private String titulo;

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pergunta converter(Produto produto, Usuario autor) {
        return new Pergunta(titulo, autor, produto);
    }
}
