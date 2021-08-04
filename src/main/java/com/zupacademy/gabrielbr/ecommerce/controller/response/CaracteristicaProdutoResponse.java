package com.zupacademy.gabrielbr.ecommerce.controller.response;

import com.zupacademy.gabrielbr.ecommerce.model.CaracteristicaProduto;

public class CaracteristicaProdutoResponse {

    private String nome;
    private String descricao;

    public CaracteristicaProdutoResponse(CaracteristicaProduto caracteristica) {
        this.nome = caracteristica.getNome();
        this.descricao = caracteristica.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
