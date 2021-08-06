package com.zupacademy.gabrielbr.ecommerce.controller.component.service;

import com.zupacademy.gabrielbr.ecommerce.controller.EventoCompraSucesso;
import com.zupacademy.gabrielbr.ecommerce.model.Compra;
import com.zupacademy.gabrielbr.ecommerce.model.enums.StatusTransacao;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@Service
public class EventosNovaCompra {

    private Set<EventoCompraSucesso> eventos;
    private Emails emails;

    public EventosNovaCompra(Set<EventoCompraSucesso> eventos, Emails emails) {
        this.eventos = eventos;
        this.emails = emails;
    }


    public StatusTransacao processa(Compra compra) {
        if (compra.processadaComSucesso()) {
            eventos.forEach(e -> e.processa(compra));
            emails.enviaEmailCompraConfirmada(compra);
            return StatusTransacao.SUCESSO;
        } else {
            String uri = compra.getGateway().criaUrlRetorno(compra, UriComponentsBuilder.fromHttpUrl("http://localhost:8080/"));
            emails.enviarEmailCompraNegada(compra, uri);
            return StatusTransacao.FALHA;
        }
    }
}
