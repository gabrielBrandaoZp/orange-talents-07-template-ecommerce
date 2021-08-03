package com.zupacademy.gabrielbr.ecommerce.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zupacademy.gabrielbr.ecommerce.model.Categoria;
import com.zupacademy.gabrielbr.ecommerce.repository.CategoriaRepository;
import com.zupacademy.gabrielbr.ecommerce.validation.CampoUnico;
import com.zupacademy.gabrielbr.ecommerce.validation.ExisteId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.Optional;

public class CadastroCategoriaRequest {

    @NotBlank
    @CampoUnico(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @JsonProperty(value = "categoriaMae")
    @Positive
    @ExisteId(domainClass = Categoria.class, fieldName = "id", allowNull = true)
    private Long categoriaMaeId;

    public CadastroCategoriaRequest(String nome, Long categoriaMaeId) {
        this.nome = nome;
        this.categoriaMaeId = categoriaMaeId;
    }

    public Optional<Categoria> converter(CategoriaRepository categoriaRepository) {
        Categoria categoria = new Categoria(nome);

        if (categoriaMaeId != null) {
            Optional<Categoria> categoriaMaeObj = categoriaRepository.findById(categoriaMaeId);

            //Already validated by ExisteId annotation if categoriaMaeId is not null
            categoria.setCategoriaMae(categoriaMaeObj.get());
        }

        return Optional.of(categoria);
    }
}
