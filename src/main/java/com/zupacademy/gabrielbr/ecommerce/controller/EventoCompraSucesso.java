package com.zupacademy.gabrielbr.ecommerce.controller;

import com.zupacademy.gabrielbr.ecommerce.model.Compra;

/**
 * Todo evento relacionado ao sucesso de uma compra deve implementar essa interface
 */
public interface EventoCompraSucesso {

    void processa(Compra compra);
}
