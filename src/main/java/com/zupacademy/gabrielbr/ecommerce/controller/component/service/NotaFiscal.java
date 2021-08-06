package com.zupacademy.gabrielbr.ecommerce.controller.component.service;

import com.zupacademy.gabrielbr.ecommerce.controller.EventoCompraSucesso;
import com.zupacademy.gabrielbr.ecommerce.model.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class NotaFiscal implements EventoCompraSucesso {

    @Override
    public void processa(Compra compra) {
        RestTemplate rt = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idComprador", compra.getComprador().getId());
        rt.postForEntity("http://localhost:8080/notas-fiscais", request, String.class);
    }
}
