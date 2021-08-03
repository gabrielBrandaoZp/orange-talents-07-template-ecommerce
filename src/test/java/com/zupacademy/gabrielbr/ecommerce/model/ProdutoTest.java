package com.zupacademy.gabrielbr.ecommerce.model;

import com.zupacademy.gabrielbr.ecommerce.controller.request.CadastraCaracteristicaRequest;
import com.zupacademy.gabrielbr.ecommerce.model.util.SenhaLimpa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    @DisplayName("Um produto deve ter no mínimo 3 características")
    @ParameterizedTest
    @MethodSource("geradorTeste1")
    void test1(Set<CadastraCaracteristicaRequest> caracteristicas) throws Exception {
        Categoria categoria = new Categoria("Categoria");
        Usuario dono = new Usuario("test@email.com", new SenhaLimpa("123"));
        new Produto(caracteristicas, "descricao", dono, "nome", BigDecimal.TEN, 10, categoria);
    }

    static Stream<Arguments> geradorTeste1() {
        return Stream.of(
                Arguments.of(
                        Set.of(new CadastraCaracteristicaRequest("key", "value"),
                                new CadastraCaracteristicaRequest("key2", "value2"),
                                new CadastraCaracteristicaRequest("key3", "value3")
                        )
                ),
                Arguments.of(
                        Set.of(new CadastraCaracteristicaRequest("key", "value"),
                                new CadastraCaracteristicaRequest("key2", "value2"),
                                new CadastraCaracteristicaRequest("key3", "value3"),
                                new CadastraCaracteristicaRequest("key4", "value5"))));
    }

    @DisplayName("Um produto não deve ser criado com menos de 3 características")
    @ParameterizedTest
    @MethodSource("geradorTeste2")
    void test2(Set<CadastraCaracteristicaRequest> caracteristicas) throws Exception {
        Categoria categoria = new Categoria("Categoria");
        Usuario dono = new Usuario("test@email.com", new SenhaLimpa("123"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Produto(caracteristicas, "descricao", dono, "nome", BigDecimal.TEN, 10, categoria);
        });
    }

    static Stream<Arguments> geradorTeste2() {
        return Stream.of(
                Arguments.of(
                        Set.of(new CadastraCaracteristicaRequest("key", "value"),
                                new CadastraCaracteristicaRequest("key3", "value3"))));
    }
}