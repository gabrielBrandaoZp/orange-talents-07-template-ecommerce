package com.zupacademy.gabrielbr.ecommerce.controller;

import com.zupacademy.gabrielbr.ecommerce.model.Compra;
import com.zupacademy.gabrielbr.ecommerce.model.Transacao;

public interface RetornoGatewayPagamento {

    /**
     *
     * @param compra a compra a ser transacionada
     * @return Transação de acordo com gateway específico
     */
    Transacao paraTransacao(Compra compra);
}
