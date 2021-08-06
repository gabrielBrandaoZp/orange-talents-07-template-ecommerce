package com.zupacademy.gabrielbr.ecommerce.model.enums;

import com.zupacademy.gabrielbr.ecommerce.model.Compra;
import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

    PAYPAL {
        @Override
        public String criaUrlRetorno(Compra compra, UriComponentsBuilder builder) {
            String urlRetornoPaypal = builder.path("/retorno-paypal/{id}")
                    .buildAndExpand(compra.getId()).toString();
            return "paypal.com/" + compra.getId() + "?redirectUrl=" +
                    urlRetornoPaypal;
        }
    },
    PAGSEGURO {
        @Override
        public String criaUrlRetorno(Compra compra, UriComponentsBuilder builder) {
            String urlRetornoPagSeguro = builder.path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();
            return "pagseguro.com/" + compra.getId() + "?redirectUrl=" +
                    urlRetornoPagSeguro;
        }
    };

    public abstract String criaUrlRetorno(Compra compra, UriComponentsBuilder builder);
}
