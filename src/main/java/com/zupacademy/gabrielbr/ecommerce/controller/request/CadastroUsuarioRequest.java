package com.zupacademy.gabrielbr.ecommerce.controller.request;

import com.zupacademy.gabrielbr.ecommerce.model.Usuario;
import com.zupacademy.gabrielbr.ecommerce.model.util.SenhaLimpa;
import com.zupacademy.gabrielbr.ecommerce.validation.CampoUnico;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Optional;

public class CadastroUsuarioRequest {

    @NotBlank
    @Email
    @CampoUnico(domainClass = Usuario.class, fieldName = "email")
    private String email;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public CadastroUsuarioRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Optional<Usuario> converter() {
        Usuario usuario = new Usuario(email, new SenhaLimpa(senha));
        return Optional.of(usuario);
    }
}
