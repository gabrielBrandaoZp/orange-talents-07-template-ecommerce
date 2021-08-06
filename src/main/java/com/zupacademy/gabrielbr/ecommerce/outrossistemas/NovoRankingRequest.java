package com.zupacademy.gabrielbr.ecommerce.outrossistemas;

import javax.validation.constraints.NotNull;

public class NovoRankingRequest {

    @NotNull
    private Long idCompra;
    @NotNull
    private Long idVendedor;

    public NovoRankingRequest(Long idCompra, Long idVendedor) {
        this.idCompra = idCompra;
        this.idVendedor = idVendedor;
    }

    @Override
    public String toString() {
        return "NovoRankingRequest{" +
                "idCompra=" + idCompra +
                ", idVendedor=" + idVendedor +
                '}';
    }
}
