package com.zupacademy.gabrielbr.ecommerce.repository;

import com.zupacademy.gabrielbr.ecommerce.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}
