package com.exemplo.usuariosapi.dto;

import com.exemplo.usuariosapi.enums.StatusPedido;
import jakarta.validation.constraints.NotNull;

public class PedidoResponseDTO {

    @NotNull
    private Long id;
    @NotNull
    private StatusPedido status;
    @NotNull
    private UsuarioResumoDTO usuario;

    public PedidoResponseDTO(Long id, StatusPedido status, UsuarioResumoDTO usuario) {
        this.id = id;
        this.status = status;
        this.usuario = usuario;
    }

    public Long getId() {
        return id;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public UsuarioResumoDTO getUsuario() {
        return usuario;
    }

}
