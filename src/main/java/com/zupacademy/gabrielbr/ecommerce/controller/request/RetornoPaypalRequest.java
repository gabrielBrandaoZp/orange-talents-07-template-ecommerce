package com.zupacademy.gabrielbr.ecommerce.controller.request;

import com.zupacademy.gabrielbr.ecommerce.controller.RetornoGatewayPagamento;
import com.zupacademy.gabrielbr.ecommerce.model.Compra;
import com.zupacademy.gabrielbr.ecommerce.model.Transacao;
import com.zupacademy.gabrielbr.ecommerce.model.enums.StatusTransacao;
import com.zupacademy.gabrielbr.ecommerce.validation.CampoUnico;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class RetornoPaypalRequest implements RetornoGatewayPagamento {

    @NotBlank
    @CampoUnico(domainClass = Transacao.class, fieldName = "id_transacao_gateway")
    private String idTransacao;

    @Min(0)
    @Max(1)
    private int status;

    public RetornoPaypalRequest(String idTransacao, int status) {
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public Transacao paraTransacao(Compra compra) {
        StatusTransacao status = this.status == 0 ? StatusTransacao.FALHA : StatusTransacao.SUCESSO;
        return new Transacao(status, idTransacao, compra);
    }
}
