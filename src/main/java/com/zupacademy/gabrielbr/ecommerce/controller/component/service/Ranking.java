package com.zupacademy.gabrielbr.ecommerce.controller.component.service;

import com.zupacademy.gabrielbr.ecommerce.controller.EventoCompraSucesso;
import com.zupacademy.gabrielbr.ecommerce.model.Compra;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements EventoCompraSucesso {

    @Override
    public void processa(Compra compra) {
        RestTemplate rt = new RestTemplate();
        Map<String, Object> request = Map.of("idCompra", compra.getId(), "idVendedor", compra.getProduto().getDonoProduto().getId());
        rt.postForEntity("http://localhost:8080/ranking", request, String.class);
    }
}
