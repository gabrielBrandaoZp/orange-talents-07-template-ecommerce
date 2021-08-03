package com.zupacademy.gabrielbr.ecommerce.controller.request;

import com.zupacademy.gabrielbr.ecommerce.model.CaracteristicaProduto;
import com.zupacademy.gabrielbr.ecommerce.model.Produto;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CadastraCaracteristicaRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String descricao;

    public CadastraCaracteristicaRequest(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public CaracteristicaProduto converter(Produto produto) {
        return new CaracteristicaProduto(nome, descricao, produto);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CadastraCaracteristicaRequest that = (CadastraCaracteristicaRequest) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
