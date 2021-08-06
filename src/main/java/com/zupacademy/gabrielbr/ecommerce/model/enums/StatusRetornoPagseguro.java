package com.zupacademy.gabrielbr.ecommerce.model.enums;

public enum StatusRetornoPagseguro {

    SUCESSO,
    ERRO;

    public StatusTransacao normaliza() {
        if (this.equals(SUCESSO)) {
            return StatusTransacao.SUCESSO;
        }

        return StatusTransacao.FALHA;
    }
}
