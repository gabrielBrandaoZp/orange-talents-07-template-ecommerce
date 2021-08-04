package com.zupacademy.gabrielbr.ecommerce.controller.component;

import com.zupacademy.gabrielbr.ecommerce.model.Pergunta;
import com.zupacademy.gabrielbr.ecommerce.model.Produto;
import com.zupacademy.gabrielbr.ecommerce.model.Usuario;
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
}
