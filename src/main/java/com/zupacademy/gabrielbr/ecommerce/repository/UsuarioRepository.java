package com.zupacademy.gabrielbr.ecommerce.repository;

import com.zupacademy.gabrielbr.ecommerce.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
