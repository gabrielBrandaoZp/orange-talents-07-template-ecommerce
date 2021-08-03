package com.zupacademy.gabrielbr.ecommerce.controller.request;

import com.zupacademy.gabrielbr.ecommerce.model.Opiniao;
import com.zupacademy.gabrielbr.ecommerce.model.Produto;
import com.zupacademy.gabrielbr.ecommerce.model.Usuario;

import javax.validation.constraints.*;

public class CadastraOpiniaoRequest {

    @NotBlank
    private String titulo;

    @NotBlank
    @Size(max = 500)
    private String descricao;

    @Min(1)
    @Max(5)
    private Integer nota;

    public CadastraOpiniaoRequest(String titulo, String descricao, Integer nota) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.nota = nota;
    }

    public Opiniao converter(Produto produto, Usuario autor) {
        return new Opiniao(titulo, nota, descricao, autor, produto);
    }
}
