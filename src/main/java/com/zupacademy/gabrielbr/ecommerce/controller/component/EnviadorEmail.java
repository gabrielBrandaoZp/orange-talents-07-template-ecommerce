package com.zupacademy.gabrielbr.ecommerce.controller.component;


public interface EnviadorEmail {

    /**
     *
     * @param corpo corpo do email
     * @param subject assunto do email
     * @param nameFrom nome para aparecer no provedor de email
     * @param from email de origim
     * @param to email de destino
     */
    void enviar(String corpo, String subject, String nameFrom, String from, String to);
}
