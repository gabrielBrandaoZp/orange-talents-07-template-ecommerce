package com.zupacademy.gabrielbr.ecommerce.controller.response;

public class LoginUsuarioResponse {

    private String token;
    private String tipo;

    public LoginUsuarioResponse(String token, String tipo) {
        this.token = token;
        this.tipo = tipo;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }
}
