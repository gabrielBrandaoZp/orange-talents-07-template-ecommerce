package com.zupacademy.gabrielbr.ecommerce.repository;

import com.zupacademy.gabrielbr.ecommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
