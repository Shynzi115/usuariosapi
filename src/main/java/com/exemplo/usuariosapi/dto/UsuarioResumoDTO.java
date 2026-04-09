package com.exemplo.usuariosapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UsuarioResumoDTO {
    @NotNull
    private Long id;
    @NotBlank(message = "Nome não pode ser vazio")
    private String nome;

    public UsuarioResumoDTO( Long id, String nome) {
        this.id = id;
        this.nome = nome;

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

}
