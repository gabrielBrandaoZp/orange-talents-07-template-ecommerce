package com.zupacademy.gabrielbr.ecommerce.controller.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.zupacademy.gabrielbr.ecommerce.model.enums.StatusTransacao;

public class TransacaoResponse {

    private StatusTransacao statusTransacao;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public TransacaoResponse(StatusTransacao statusTransacao) {
        this.statusTransacao = statusTransacao;
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }
}
