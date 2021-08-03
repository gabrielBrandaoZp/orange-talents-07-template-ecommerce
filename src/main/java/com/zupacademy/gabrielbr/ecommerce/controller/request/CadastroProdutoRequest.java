package com.zupacademy.gabrielbr.ecommerce.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zupacademy.gabrielbr.ecommerce.model.Categoria;
import com.zupacademy.gabrielbr.ecommerce.model.Produto;
import com.zupacademy.gabrielbr.ecommerce.model.Usuario;
import com.zupacademy.gabrielbr.ecommerce.repository.CategoriaRepository;
import com.zupacademy.gabrielbr.ecommerce.validation.CampoUnico;
import com.zupacademy.gabrielbr.ecommerce.validation.ExisteId;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public class CadastroProdutoRequest {

    @NotBlank
    @CampoUnico(domainClass = Produto.class, fieldName = "nome")
    private String nome;

    @NotNull
    @Min(1)
    private BigDecimal valor;

    @NotNull
    @Min(0)
    private Integer quantidade;

    @JsonProperty(value = "caracteristicas")
    @Size(min = 3)
    private Set<CadastraCaracteristicaRequest> caracteristicaRequests;

    @NotBlank
    @Size(max = 1000)
    private String descricao;

    @JsonProperty(value = "categoria")
    @NotNull
    @ExisteId(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaId;

    public CadastroProdutoRequest(String nome, BigDecimal valor, Integer quantidade, Set<CadastraCaracteristicaRequest> caracteristicaRequests,
                                  String descricao, Long categoriaId) {
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicaRequests = caracteristicaRequests;
        this.descricao = descricao;
        this.categoriaId = categoriaId;
    }

    public Optional<Produto> converter(CategoriaRepository categoriaRepository, Usuario usuario) {
        Optional<Categoria> categoriaObj = categoriaRepository.findById(categoriaId);
        Categoria categoria = categoriaObj.get();

        Produto produto = new Produto(caracteristicaRequests, descricao, usuario, nome, valor, quantidade, categoria);

        return Optional.of(produto);
    }
}
