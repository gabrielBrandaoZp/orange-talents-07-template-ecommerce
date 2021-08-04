package com.zupacademy.gabrielbr.ecommerce.controller.component;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EnviadorEmailFake implements EnviadorEmail{

    @Override
    public void enviar(String corpo, String subject, String nameFrom, String from, String to) {
        System.out.println("corpo> " + corpo);
        System.out.println("assunto> " + subject);
        System.out.println("nome no provedor de email> " + nameFrom);
        System.out.println("de> " + from);
        System.out.println("para> " + to);
    }
}
