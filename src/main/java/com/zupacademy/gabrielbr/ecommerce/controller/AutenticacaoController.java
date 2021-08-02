package com.zupacademy.gabrielbr.ecommerce.controller;

import com.zupacademy.gabrielbr.ecommerce.controller.request.LoginUsuarioRequest;
import com.zupacademy.gabrielbr.ecommerce.controller.response.LoginUsuarioResponse;
import com.zupacademy.gabrielbr.ecommerce.security.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    private final AuthenticationManager authManager;
    private final TokenService tokenService;

    public AutenticacaoController(AuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<LoginUsuarioResponse> autenticar(@RequestBody @Valid LoginUsuarioRequest loginRequest) {
        UsernamePasswordAuthenticationToken dadosLogin = loginRequest.converter();

        try{
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new LoginUsuarioResponse(token, "Bearer"));
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
