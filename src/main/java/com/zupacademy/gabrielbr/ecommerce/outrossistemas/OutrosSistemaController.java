package com.zupacademy.gabrielbr.ecommerce.outrossistemas;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OutrosSistemaController {

    @PostMapping("/notas-fiscais")
    public void criaNota(@RequestBody @Valid NovaCompraNFRequest request) throws InterruptedException {
        System.out.println("Criando nota fiscal para: " + request.toString());
        Thread.sleep(150);
    }

    @PostMapping("/ranking")
    public void ranking(@RequestBody @Valid NovoRankingRequest request) throws InterruptedException {
        System.out.println("Criando ranking para: " + request.toString());
        Thread.sleep(150);
    }
}
