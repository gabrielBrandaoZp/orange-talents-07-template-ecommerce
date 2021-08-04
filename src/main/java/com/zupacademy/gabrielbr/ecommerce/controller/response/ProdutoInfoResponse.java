package com.zupacademy.gabrielbr.ecommerce.controller.response;


import com.zupacademy.gabrielbr.ecommerce.model.Produto;
import com.zupacademy.gabrielbr.ecommerce.model.util.Opinioes;

import java.math.BigDecimal;
import java.util.Set;
import java.util.SortedSet;

public class ProdutoInfoResponse {

    private String nome;
    private BigDecimal preco;
    private String descricao;
    private Set<CaracteristicaProdutoResponse> caracteristicas;
    private Set<String> imagens;
    private Double mediaNotas;
    private Integer qntNotas;
    private Set<OpiniaoProdutoResponse> opinioes;
    private SortedSet<PerguntaProdutoResponse> perguntas;

    public ProdutoInfoResponse(Produto produto) {
        this.nome = produto.getNome();
        this.preco = produto.getValor();
        this.descricao = produto.getDescricao();
        this.caracteristicas = produto.mapearCaracteristicas(caracteristica -> new CaracteristicaProdutoResponse(caracteristica));
        this.imagens = produto.mapearImagens(imagem -> imagem.getUrl());
        this.perguntas = produto.mapearPerguntas(pergunta -> new PerguntaProdutoResponse(pergunta));

        Opinioes opinioes = produto.getOpinioes();
        this.opinioes = opinioes.mapearOpinioes(opiniao -> new OpiniaoProdutoResponse(opiniao));
        this.mediaNotas = opinioes.media();
        this.qntNotas = opinioes.qntNotas();
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public Set<String> getImagens() {
        return imagens;
    }

    public Set<CaracteristicaProdutoResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public SortedSet<PerguntaProdutoResponse> getPerguntas() {
        return perguntas;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Integer getQntNotas() {
        return qntNotas;
    }

    public Set<OpiniaoProdutoResponse> getOpinioes() {
        return opinioes;
    }
}
