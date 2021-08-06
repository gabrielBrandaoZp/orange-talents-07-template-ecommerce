package com.zupacademy.gabrielbr.ecommerce.controller.request;

import com.zupacademy.gabrielbr.ecommerce.controller.RetornoGatewayPagamento;
import com.zupacademy.gabrielbr.ecommerce.model.Compra;
import com.zupacademy.gabrielbr.ecommerce.model.Transacao;
import com.zupacademy.gabrielbr.ecommerce.model.enums.StatusRetornoPagseguro;
import com.zupacademy.gabrielbr.ecommerce.validation.CampoUnico;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RetornoPagseguroRequest implements RetornoGatewayPagamento {

    @NotBlank
    @CampoUnico(domainClass = Transacao.class, fieldName = "id_transacao_gateway")
    private String idTransacao;
    @NotNull
    private StatusRetornoPagseguro status;

    public RetornoPagseguroRequest(String idTransacao, StatusRetornoPagseguro status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "RetornoPagseguroRequest{" +
                "idTransacao='" + idTransacao + '\'' +
                ", status=" + status +
                '}';
    }

    public Transacao paraTransacao(Compra compra) {
        return new Transacao(status.normaliza(), idTransacao, compra);
    }
}
