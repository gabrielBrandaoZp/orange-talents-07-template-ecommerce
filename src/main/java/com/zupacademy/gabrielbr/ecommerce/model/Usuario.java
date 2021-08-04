package com.zupacademy.gabrielbr.ecommerce.model;

import com.zupacademy.gabrielbr.ecommerce.model.util.SenhaLimpa;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String senha;
    private LocalDateTime dataCriacao;

    @Deprecated
    public Usuario(){}

    public Usuario(String email, SenhaLimpa senhaLimpa) {
        this.email = email;
        this.senha = senhaLimpa.hash();
        dataCriacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return email.equals(usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
