package com.zupacademy.gabrielbr.ecommerce.controller.request;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class CadastraImagemProdutoRequest {

    @Size(min = 1)
    @NotNull
    private List<MultipartFile> imagens;

    public void setImagens(List<MultipartFile> imagens) {
        this.imagens = imagens;
    }

    public List<MultipartFile> getImagens() {
        return this.imagens;
    }
}
