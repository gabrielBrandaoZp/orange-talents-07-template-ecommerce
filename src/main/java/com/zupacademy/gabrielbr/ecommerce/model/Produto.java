package com.zupacademy.gabrielbr.ecommerce.model;

import com.zupacademy.gabrielbr.ecommerce.controller.request.CadastraCaracteristicaRequest;
import com.zupacademy.gabrielbr.ecommerce.model.util.Opinioes;
import io.jsonwebtoken.lang.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
    private final Set<CaracteristicaProduto> caracteristicas = new HashSet<>();

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @ManyToOne
    private Usuario donoProduto;

    @ManyToOne
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
    private Set<ImagemProduto> imagens = new HashSet<>();

    @OneToMany(mappedBy = "produto")
    @OrderBy("titulo asc")
    private SortedSet<Pergunta> perguntas = new TreeSet<>();

    @OneToMany(mappedBy = "produto")
    private Set<Opiniao> opinioes = new HashSet<>();

    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private LocalDateTime dataCriacao;

    @Deprecated
    public Produto() {}

    public Produto(Set<CadastraCaracteristicaRequest> caracteristicaRequests, String descricao, Usuario donoProduto, String nome, BigDecimal valor,
                   Integer quantidade, Categoria categoria) {

        caracteristicaRequests.forEach(cr -> this.caracteristicas.add(cr.converter(this)));
        this.descricao = descricao;
        this.donoProduto = donoProduto;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.categoria = categoria;
        this.dataCriacao = LocalDateTime.now();

        Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no mínimo 3 características");
    }


    public void associaImagens(Set<String> links) {
        links.forEach(link -> this.imagens.add(new ImagemProduto(link, this)));
    }

    public List<String> linksImages() {
        List<String> linkImagens = new ArrayList<>();
        imagens.forEach(imagem -> linkImagens.add(imagem.getUrl()));
        return linkImagens;
    }

    public boolean pertenceAoDono(Usuario possivelDono) {
        return this.donoProduto.equals(possivelDono);
    }

    public Usuario getDonoProduto() {
        return donoProduto;
    }

    public <T> Set<T> mapearCaracteristicas(Function<CaracteristicaProduto, T> funcaoMapeadora) {
        return this.caracteristicas
                .stream()
                .map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }

    public <T> Set<T> mapearImagens(Function<ImagemProduto, T> funcaoMapeadora) {
        return this.imagens
                .stream()
                .map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }

    public <T extends Comparable<T>> SortedSet<T> mapearPerguntas(Function<Pergunta, T> funcaoMapeadora) {
        return this.perguntas
                .stream()
                .map(funcaoMapeadora)
                .collect(Collectors.toCollection(() -> new TreeSet<>()));
    }

    public boolean abateQuantidade(Integer quantidade) {
        if(this.quantidade >= quantidade) {
            this.quantidade -= quantidade;
            return true;
        }

        return false;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return nome.equals(produto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    public Opinioes getOpinioes() {
        return new Opinioes(this.opinioes);
    }
}
