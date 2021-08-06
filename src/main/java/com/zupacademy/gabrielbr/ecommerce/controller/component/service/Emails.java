package com.zupacademy.gabrielbr.ecommerce.controller.component.service;

import com.zupacademy.gabrielbr.ecommerce.controller.component.EnviadorEmail;
import com.zupacademy.gabrielbr.ecommerce.model.*;
import org.springframework.stereotype.Service;

@Service
public class Emails {

    private EnviadorEmail enviadorEmail;

    public Emails(EnviadorEmail enviadorEmail) {
        this.enviadorEmail = enviadorEmail;
    }

    public void enviaEmailNovaPergunta(Pergunta pergunta) {
        Usuario autor = pergunta.getAutor();
        Produto produto = pergunta.getProduto();
        enviadorEmail.enviar("<html>...</html>", "Nova pergunta sobre o produto...", "novapergunta@nossomercadolivre.com",
                autor.getEmail(), produto.getDonoProduto().getEmail());
    }

    public void enviaEmailNovaCompra(Compra compra) {
        Usuario comprador = compra.getComprador();
        Produto produto = compra.getProduto();
        enviadorEmail.enviar("<html>...</html>", "Um comprador está interessado no seu produto...", "compras@nossomercadolivre.com",
                comprador.getEmail(), produto.getDonoProduto().getEmail());
    }

    public void enviaEmailCompraConfirmada(Compra compra) {
        Usuario comprador = compra.getComprador();

        enviadorEmail.enviar("<html>...</html>", "Olá, estamos enviando esse e-mail porque confirmamos o pagamento da sua compra: " + compra.getId(), "compras@nossomercadolivre.com",
                "compras@nossomercadolivre.com", comprador.getEmail());
    }

    public void enviarEmailCompraNegada(Compra compra, String uri) {
        Usuario comprador = compra.getComprador();

        enviadorEmail.enviar("<html>...</html>", "Olá, estamos enviando esse e-mail porque o pagamento da sua compra: #" + compra.getId() + " falhou. Você pode tentar comprar novamente em: " + uri, "compras@nossomercadolivre.com",
                "compras@nossomercadolivre.com", comprador.getEmail());
    }
}
