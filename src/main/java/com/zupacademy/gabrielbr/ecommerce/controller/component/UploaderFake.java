package com.zupacademy.gabrielbr.ecommerce.controller.component;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class UploaderFake {

    /*
        Fake upload de imagens
     */
    public Set<String> envia(List<MultipartFile> imagens) {
        return imagens
                .stream()
                .map(imagem -> "http://bucket.io/" + UUID.randomUUID().toString() + "-" + imagem.getOriginalFilename())
                .collect(Collectors.toSet());
    }
}
