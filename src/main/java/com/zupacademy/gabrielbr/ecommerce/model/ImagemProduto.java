package com.zupacademy.gabrielbr.ecommerce.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;

    @ManyToOne
    private Produto produto;

    @Deprecated
    public ImagemProduto() {}

    public ImagemProduto(String url, Produto produto) {
        this.url = url;
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImagemProduto that = (ImagemProduto) o;
        return url.equals(that.url) && produto.equals(that.produto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, produto);
    }
}
