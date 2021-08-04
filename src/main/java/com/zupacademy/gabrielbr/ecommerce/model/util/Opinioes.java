package com.zupacademy.gabrielbr.ecommerce.model.util;

import com.zupacademy.gabrielbr.ecommerce.model.Opiniao;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Isola as operações sobre um conjunto de opiniões
 */
public class Opinioes {

    private Set<Opiniao> opinioes;

    public Opinioes(Set<Opiniao> opinioes) {
        this.opinioes = opinioes;
    }

    public <T> Set<T> mapearOpinioes(Function<Opiniao, T> funcaoMapeadora) {
        return this.opinioes
                .stream()
                .map(funcaoMapeadora)
                .collect(Collectors.toSet());
    }

    public Double media() {
        Set<Integer> notas = mapearOpinioes(opiniao -> opiniao.getNota());
        OptionalDouble possivelMedia = notas.stream().mapToInt(nota -> nota).average();
        return possivelMedia.orElseGet(() -> 0.0);
    }

    public Integer qntNotas() {
        return this.opinioes.size();
    }
}
